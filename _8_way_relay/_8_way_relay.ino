/**
 *  Arduino + 8-way relay board Device Type for SmartThings
 *
 *  Author: badgermanus@gmail.com
 *  Code: https://github.com/jwsf/device-type.arduino-8-way-relay
 *
 * Requires the following libraries:
 *  http://www.forward.com.au/pfod/ArduinoProgramming/elapsedMillis.zip
 *  http://www.pjrc.com/teensy/td_libs_Time.html
 *
 * Copyright (C) 2014 Jonathan Wilson  <badgermanus@gmail.com>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this 
 * software and associated documentation files (the "Software"), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject to the following 
 * conditions: The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR fANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
#include <SoftwareSerial.h> 
#include <SmartThings.h>
#include <Time.h>
#include <elapsedMillis.h>
 
#define PIN_THING_RX    3
#define PIN_THING_TX    2

#define RELAY_ON 0
#define RELAY_OFF 1

elapsedMillis timer0;
#define interval 60000 // For fail-safe timer - check for relays which need to be turned off every minute

#define DEBUG
#ifdef DEBUG
  #define DEBUG_PRINT(x)    Serial.print (x)
  #define DEBUG_PRINTDEC(x) Serial.print (x, DEC)
  #define DEBUG_PRINTLN(x)  Serial.println (x)
#else
  #define DEBUG_PRINT(x)
  #define DEBUG_PRINTDEC(x)
  #define DEBUG_PRINTLN(x) 
#endif
 
 
SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor


time_t relayOnTimes[8];
int relayAutoOffAfterMinutes[8];
 
void setup()
{
  #ifdef DEBUG
    // setup debug serial port
    Serial.begin(9600);        
  #endif
  
  DEBUG_PRINTLN("Setting everything up...");  
    
  // Initialize pins so relays are off at reset, then set as outputs, then tell SmartThings they are off
  for (int relay=1;relay<9;relay++) 
  {
    // Initialize pins so relays are off at reset
    digitalWrite(relay+3, RELAY_OFF);          // +3 because the relay pins start at pin 4

    // THEN set pins as outputs 
    pinMode(relay+3, OUTPUT);                  // +3 because the relay pins start at pin 4

    // Tell SmartThings all the relays are off
    smartthing.send("relayoff"+String(relay));
  }  
  
  setTime(8,29,0,1,1,11); // set time to Saturday 8:29:00am Jan 1 2011 so the time stuff works (relatively)
  delay(2000); 
  DEBUG_PRINTLN("Ready...");  
  
  timer0 = 0; // clear the elapsed timer at the end of startup
}
  
void loop()
{
  // run smartthing logic
  smartthing.run();
  
  if (timer0 > interval) 
  {
    timer0 -= interval; //reset the timer
    autoTurnOffRelays();
  }
}

void changeRelayState(int relay, int state)
{
  if ((relay<1) | (relay>8))
  {
    DEBUG_PRINTLN("Invalid relay number sent");
    return; 
  }
  
  // Switch the relay
  digitalWrite(3+relay, state);// set the Relay - +3 because the relay pins start at pin 4

  // Tell SmartThings what's going on 
  char buffer[32];
  snprintf(buffer, 32, "relay%s%i", (state==RELAY_ON) ? "on" : "off", relay);
  DEBUG_PRINT("Sending message '");
  DEBUG_PRINT(buffer);                         
  DEBUG_PRINTLN("' to SmartThings");  
  smartthing.send(buffer);    // send message to cloud
}

void messageCallout(String message)
{
  #ifdef DEBUG
    if (message != "")
    {
      DEBUG_PRINT("Received message: '");
      DEBUG_PRINT(message);
      DEBUG_PRINTLN("' ");
    }
  #endif

  if (message.startsWith ("push"))
  {
    // Get and check the relay number
    int relay = message.substring(4).toInt();
    if ((relay<1) | (relay>8))
    {
      DEBUG_PRINTLN("Invalid relay number sent");
      return; 
    }
    
    // Turn on the relay    
    digitalWrite(3+relay, RELAY_ON);// set the Relay - +3 because the relay pins start at pin 4
    delay(100);
    digitalWrite(3+relay, RELAY_OFF);// set the Relay - +3 because the relay pins start at pin 4
  } 
  else  if (message.startsWith ("relayon"))
  {
    // Turn on the relay
    int relay = message.substring(7,8).toInt();
    changeRelayState(relay, RELAY_ON);
    
    // Get the auto-off time
    int autoOffMinutes = message.substring(9).toInt();
    DEBUG_PRINT("Auto off after ");
    DEBUG_PRINT (autoOffMinutes);
    DEBUG_PRINTLN(" minutes");
    relayAutoOffAfterMinutes[relay-1] = autoOffMinutes;
    
    // Record when it was turned on so was can potentially do a fail-safe turn-off
    relayOnTimes[relay-1] = now();
  } 
  else if (message.startsWith ("relayoff"))
  {
    // Turn off the relay
    int relay = message.substring(8).toInt();
    changeRelayState(relay, RELAY_OFF);
  }  
  else if (message.startsWith ("relaystateall"))
  {
    // Tell SmartThings what's going on  
    for (int relay=1;relay<9;relay++) 
    {
      reportRelayStatusToSmartThings(relay);
    }
  }   
  else if (message.startsWith ("autorelayoff1"))
  {
     DEBUG_PRINTLN ("Received config param"); 
  }
}

void reportRelayStatusToSmartThings(int relay)
{
    int state = digitalRead(relay+3);  // +3 because the relay pins start at pin 4
    char buffer[32];
    snprintf(buffer, 32, "relay%s%i", (state==RELAY_ON) ? "on" : "off", relay);
    DEBUG_PRINT("Sending message '");
    DEBUG_PRINT(buffer);
    DEBUG_PRINTLN("' to SmartThings");
    smartthing.send(buffer);    // send message to cloud
}

void autoTurnOffRelays()
{
    DEBUG_PRINTLN("Checking to see if any relays need turning off automatically (fail safe)");
    
    // See if it needs turning off
    DEBUG_PRINT("Time now: ");    
    DEBUG_PRINTLN(String(now()));
    
    // Tell SmartThings what's going on  
    for (int relay=1;relay<9;relay++) 
    {
      DEBUG_PRINTLN("Checking relay: " + String(relay));
      int state = digitalRead(relay+3);  // +3 because the relay pins start at pin 4
      if (state==RELAY_ON)
      {
        // See if it needs turning off
        int mins = relayAutoOffAfterMinutes[relay-1];
        DEBUG_PRINT("Should be turned off after ");
        DEBUG_PRINT(mins);
        DEBUG_PRINTLN(" minutes");
        
        // Skip if auto-ff is set to zero minutes - this means it has not been set
        if (mins == 0 ) continue;
        
        DEBUG_PRINT("Time relay was turned on: ");
        DEBUG_PRINTLN(relayOnTimes[relay-1]);
        
        if (relayOnTimes[relay-1]+(mins*60) <  now())
        {
          DEBUG_PRINTLN("Fail-safe turn off!");
          changeRelayState(relay, RELAY_OFF);
           
          // Report auto-turn off event to ST
          char buffer[32];
          snprintf(buffer, 32, "relayautooff%i", relay);
          smartthing.send(buffer);    // send message to cloud
        }          
      } 
    }
  }

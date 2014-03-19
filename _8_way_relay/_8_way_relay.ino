/**
 *  Arduino + 8-way relay board Device Type for SmartThings
 *
 *  Author: badgermanus@gmail.com
 *  Code: https://github.com/jwsf/device-type.arduino-8-way-relay
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
 
#define PIN_THING_RX    3
#define PIN_THING_TX    2

#define RELAY_ON 0
#define RELAY_OFF 1

#define Relay_1  4  // Arduino Digital I/O pin number
#define Relay_2  5
#define Relay_3  6
#define Relay_4  7
#define Relay_5  8 
#define Relay_6  9
#define Relay_7  10
#define Relay_8  11

#define DEBUG
#ifdef DEBUG
  #define DEBUG_PRINT(x)     Serial.print (x)
  #define DEBUG_PRINTDEC(x)     Serial.print (x, DEC)
  #define DEBUG_PRINTLN(x)  Serial.println (x)
#else
  #define DEBUG_PRINT(x)
  #define DEBUG_PRINTDEC(x)
  #define DEBUG_PRINTLN(x) 
#endif
 
 
SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor
 
void setup()
{
  // Initialize Pins so relays are inactive at reset
  digitalWrite(Relay_1, RELAY_OFF);
  digitalWrite(Relay_2, RELAY_OFF);
  digitalWrite(Relay_3, RELAY_OFF);
  digitalWrite(Relay_4, RELAY_OFF);  
  digitalWrite(Relay_5, RELAY_OFF);
  digitalWrite(Relay_6, RELAY_OFF);
  digitalWrite(Relay_7, RELAY_OFF);
  digitalWrite(Relay_8, RELAY_OFF);    
  
  // THEN set pins as outputs 
  pinMode(Relay_1, OUTPUT);   
  pinMode(Relay_2, OUTPUT);  
  pinMode(Relay_3, OUTPUT);  
  pinMode(Relay_4, OUTPUT);    
  pinMode(Relay_5, OUTPUT);   
  pinMode(Relay_6, OUTPUT);  
  pinMode(Relay_7, OUTPUT);  
  pinMode(Relay_8, OUTPUT);    
  
  // Tell SmartThings all the relays are off
  smartthing.send("relayoff1");
  smartthing.send("relayoff2");
  smartthing.send("relayoff3");
  smartthing.send("relayoff4");
  smartthing.send("relayoff5");
  smartthing.send("relayoff6");
  smartthing.send("relayoff7");
  smartthing.send("relayoff8");
  
  // Check that all relays are inactive at Reset  
  delay(2000); 
 
  #ifdef DEBUG
    // setup debug serial port
    Serial.begin(9600);        
    DEBUG_PRINTLN("Ready...");  
  #endif
}
  
void loop()
{
  // run smartthing logic
  smartthing.run();
}

void changeRelayState(int relay, int state)
{
  // Switch the relay
  DEBUG_PRINT("Switching relay ");
  DEBUG_PRINT(relay);
  DEBUG_PRINTLN((state==RELAY_ON) ? " on" : " off");
  digitalWrite(3+relay, state);// set the Relay - +3 because the relay pins start at pin 4

  // Tell SmartThings what's going on  
  String newState = (state==RELAY_ON) ? "on" : "off";
  String msg = "relay" + newState + String(relay);
  DEBUG_PRINTLN("Sending message "+ msg + " to SmartThings");
  smartthing.send(msg);    // send message to cloud
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

  if (message.startsWith ("relayon"))
  {
    int relay = message.substring(7).toInt();
    changeRelayState(relay, RELAY_ON);
  } 
  else if (message.startsWith ("relayoff"))
  {
    int relay = message.substring(8).toInt();
    changeRelayState(relay, RELAY_OFF);
  }  
  
  
  
}

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
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */ 
 
metadata {
	// Automatically generated. Make future change here.
	definition (name: "Arduino Relay Board", author: "badgermanus@gmail.com") {
		capability "Polling"
        capability "Refresh"
        
        /*attribute "relay1", "enum", ["on","off"]
        attribute "relay2", "enum", ["on","off"]
        attribute "relay3", "enum", ["on","off"]
        attribute "relay4", "enum", ["on","off"]
        attribute "relay5", "enum", ["on","off"]
        attribute "relay6", "enum", ["on","off"]
        attribute "relay7", "enum", ["on","off"]
        attribute "relay8", "enum", ["on","off"]*/
        
         
		command "RelayOn1"
		command "RelayOff1"
		command "RelayOn2"
		command "RelayOff2"
		command "RelayOn3"
		command "RelayOff3"
		command "RelayOn4"
		command "RelayOff4"
		command "RelayOn5"
		command "RelayOff5"
		command "RelayOn6"
		command "RelayOff6"
		command "RelayOn7"
		command "RelayOff7"
		command "RelayOn8"
		command "RelayOff8"
        
	}

	// tile definitions
	tiles {
		standardTile("relay1", "device.relay1", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff1", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
            state "off", label: '${name}', action: "RelayOn1", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff1", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
            
		}
		standardTile("relay2", "device.relay2", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff2", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn2", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff2", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}
		standardTile("relay3", "device.relay3", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff3", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn3", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff3", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}
		standardTile("relay4", "device.relay4", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff4", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn4", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff4", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}
		standardTile("relay5", "device.relay5", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff5", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn5", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff5", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}
		standardTile("relay6", "device.relay6", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff6", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn6", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff6", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}
		standardTile("relay7", "device.relay7", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff7", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn7", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff7", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}
		standardTile("relay8", "device.relay8", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff8", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"switching"
			state "off", label: '${name}', action: "RelayOn8", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"switching"
            state "switching", label: '${name}', action: "RelayOff8", icon: "st.switches.switch.on", backgroundColor: "#ff8d00"
		}/*
        
        standardTile("relay1", "device.relay1", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff1", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
            state "off", label: '${name}', action: "RelayOn1", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
            
		}
		standardTile("relay2", "device.relay2", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff2", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn2", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}
		standardTile("relay3", "device.relay3", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff3", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn3", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}
		standardTile("relay4", "device.relay4", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff4", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn4", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}
		standardTile("relay5", "device.relay5", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff5", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn5", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}
		standardTile("relay6", "device.relay6", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff6", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn6", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}
		standardTile("relay7", "device.relay7", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff7", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn7", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}
		standardTile("relay8", "device.relay8", canChangeIcon: true, canChangeBackground: true) {
			state "on", label: '${name}', action: "RelayOff8", icon: "st.switches.switch.on", backgroundColor: "#79b821",  nextState:"off"
			state "off", label: '${name}', action: "RelayOn8", icon: "st.switches.switch.off", backgroundColor: "#ffffff",  nextState:"on"
            
		}*/
        
        
		main "relay1"
		details(["relay1", "relay2", "relay3", "relay4", "relay5", "relay6", "relay7", "relay8"])
	}
    simulator {
        status "on":  "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6E"
        status "off": "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6666"
    
        // reply messages
        reply "raw 0x0 { 00 00 0a 0a 6f 6e }": "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6E"
        reply "raw 0x0 { 00 00 0a 0a 6f 66 66 }": "catchall: 0104 0000 01 01 0040 00 0A21 00 00 0000 0A 00 0A6F6666"
    }
}
 

// Commands
// =====================


def RelayOn1() {
	zigbee.smartShield(text: "relayon1").format()
} 
def RelayOff1() {
	zigbee.smartShield(text: "relayoff1").format()
}
def RelayOn2() {
    zigbee.smartShield(text: "relayon2").format()
} 
def RelayOff2() {
	zigbee.smartShield(text: "relayoff2").format()
}
def RelayOn3() {
    zigbee.smartShield(text: "relayon3").format()
} 
def RelayOff3() {
	zigbee.smartShield(text: "relayoff3").format()
}
def RelayOn4() {
    zigbee.smartShield(text: "relayon4").format()
} 
def RelayOff4() {
	zigbee.smartShield(text: "relayoff4").format()
}
def RelayOn5() {
    zigbee.smartShield(text: "relayon5").format()
} 
def RelayOff5() {
	zigbee.smartShield(text: "relayoff5").format()
}
def RelayOn6() {
    zigbee.smartShield(text: "relayon6").format()
} 
def RelayOff6() {
	zigbee.smartShield(text: "relayoff6").format()
}
def RelayOn7() {
    zigbee.smartShield(text: "relayon7").format()
} 
def RelayOff7() {
	zigbee.smartShield(text: "relayoff7").format()
}
def RelayOn8() {
    zigbee.smartShield(text: "relayon8").format()
} 
def RelayOff8() {
	zigbee.smartShield(text: "relayoff8").format()
}
def poll()
{
	log.debug "Poll - getting state of all relays"
    zigbee.smartShield(text: "relaystateall").format()
}
def refresh()
{
	log.debug "Refresh - getting state of all relays"
    zigbee.smartShield(text: "relaystateall").format()
}

 
// Arduino event handlers
// =================================
 
def parse(String description) {

 	def value = zigbee.parse(description)?.text
    log.debug "Received: " + value
    
    if (value == "relayon1") { 
	   	createEvent (name:"relay1", value:"on", isStateChange:true)
    } else
    if (value == "relayoff1") {
	   	createEvent (name:"relay1", value:"off", isStateChange:true)
    } else 
    if (value == "relayon2") {
	   	createEvent (name:"relay2", value:"on", isStateChange:true)
    } else
    if (value == "relayoff2") {
	   	createEvent (name:"relay2", value:"off", isStateChange:true)
    } else 
    if (value == "relayon3") {
	   	createEvent (name:"relay3", value:"on", isStateChange:true)
    } else
    if (value == "relayoff3") {
	   	createEvent (name:"relay3", value:"off", isStateChange:true)
    } else 
    if (value == "relayon4") {
	   	createEvent (name:"relay4", value:"on", isStateChange:true)
    } else
    if (value == "relayoff4") {
	   	createEvent (name:"relay4", value:"off", isStateChange:true)
    } else 
    if (value == "relayon5") {
	   	createEvent (name:"relay5", value:"on", isStateChange:true)
    } else
    if (value == "relayoff5") {
	   	createEvent (name:"relay5", value:"off", isStateChange:true)
    } else 
    if (value == "relayon6") {
	   	createEvent (name:"relay6", value:"on", isStateChange:true)
    } else
    if (value == "relayoff6") {
	   	createEvent (name:"relay6", value:"off", isStateChange:true)
    } else 
    if (value == "relayon7") {
	   	createEvent (name:"relay7", value:"on", isStateChange:true)
    } else
    if (value == "relayoff7") {
	   	createEvent (name:"relay7", value:"off", isStateChange:true)
    } else 
    if (value == "relayon8") {
	   	createEvent (name:"relay8", value:"on", isStateChange:true)
    } else
    if (value == "relayoff8") {
	   	createEvent (name:"relay8", value:"off", isStateChange:true)
    } 
}





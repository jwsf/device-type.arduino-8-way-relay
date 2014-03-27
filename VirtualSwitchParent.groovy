/**
 *  VirtualSwitchParent
 *
 *  Author: badgermanus@gmail.com
 *  Date: 2014-03-26
 */
preferences {
	section("Connect these virtual switches to the Arduino's relays") {
		input "switch1", title: "Switch for relay 1", "capability.switch"
        input "switch2", title: "Switch for relay 2", "capability.switch", required: false
        input "switch3", title: "Switch for relay 3", "capability.switch", required: false
        input "switch4", title: "Switch for relay 4", "capability.switch", required: false 
        input "switch5", title: "Switch for relay 5", "capability.switch", required: false
        input "switch6", title: "Switch for relay 6", "capability.switch", required: false
        input "switch7", title: "Switch for relay 7", "capability.switch", required: false
        input "switch8", title: "Switch for relay 8", "capability.switch", required: false
	}
    section("Which Arduino relay board to control?") {
		input "arduino", "device.arduinoRelayBoard"
    }    
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	subscribe()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	subscribe()
}


def subscribe() {
	subscribe(switch1, "switch.on", switchOn1)
	subscribe(switch1, "switch.off", switchOff1)
    subscribe(switch2, "switch.on", switchOn2)
	subscribe(switch2, "switch.off", switchOff2)
    subscribe(switch3, "switch.on", switchOn3)
	subscribe(switch3, "switch.off", switchOff3)
    subscribe(switch4, "switch.on", switchOn4)
	subscribe(switch4, "switch.off", switchOff4)
    subscribe(switch5, "switch.on", switchOn5)
	subscribe(switch5, "switch.off", switchOff5)
    subscribe(switch6, "switch.on", switchOn6)
	subscribe(switch6, "switch.off", switchOff6)
    subscribe(switch7, "switch.on", switchOn7)
	subscribe(switch7, "switch.off", switchOff7)
    subscribe(switch8, "switch.on", switchOn8)
	subscribe(switch8, "switch.off", switchOff8)
}

def switchOn1(evt)
{
	log.debug "switchOn1($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn1()
}

def switchOff1(evt)
{
	log.debug "switchOff1($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff1()
}
def switchOn2(evt)
{
	log.debug "switchOn2($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn2()
}

def switchOff2(evt)
{
	log.debug "switchOff2($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff2()
}

def switchOn3(evt)
{
	log.debug "switchOn3($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn3()
}

def switchOff3(evt)
{
	log.debug "switchOff3($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff3()
}
def switchOn4(evt)
{
	log.debug "switchOn4($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn4()
}

def switchOff4(evt)
{
	log.debug "switchOff4($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff4()
}

def switchOn5(evt)
{
	log.debug "switchO5($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn5()
}

def switchOff5(evt)
{
	log.debug "switchOff5($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff5()
}
def switchOn6(evt)
{
	log.debug "switchOn6($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn6()
}

def switchOff6(evt)
{
	log.debug "switchOff6($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff6()
}

def switchOn7(evt)
{
	log.debug "switchOn7($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn7()
}

def switchOff7(evt)
{
	log.debug "switchOff7($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff7()
}
def switchOn8(evt)
{
	log.debug "switchOn8($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOn8()
}

def switchOff8(evt)
{
	log.debug "switchOff8($evt.name: $evt.value: $evt.deviceId)"
    arduino.RelayOff8()
}

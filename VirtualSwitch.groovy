/*metadata {
    definition (name: "VirtualSwitch", author: "badgermanus@gmail.com") {
        capability "Switch"
    }
}*/


preferences {
    input("num", "number", title: "Switch number", description: "The switch (relay) number to connect to (1 to 8)", required: true)
}


// simulator metadata
simulator {
    status "on":  "command: 2003, payload: FF"
    status "off": "command: 2003, payload: 00"

    // reply messages
    reply "2001FF,delay 100,2502": "command: 2503, payload: FF"
    reply "200100,delay 100,2502": "command: 2503, payload: 00"
}

// tile definitions
tiles {
    standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
        state "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
        state "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
    }

    standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
        state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
    }

    main "switch"
    details(["switch","refresh"])
}


// handle commands
def on() {
	log.debug "On"
    sendEvent (name: "switch", value: "on")
}

def off() {
	log.debug "Off"
    sendEvent (name: "switch", value: "off")
}
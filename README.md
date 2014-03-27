SmartThings Shield+Arduino+8-way Relay board Device-Type
===================

## INSTALLATION

1. Follow the instructions at [http://build.smartthings.com/arduino/](http://build.smartthings.com/arduino/) to get your shield paired with SmartThings


2. Create a new device type ([https://graph.api.smartthings.com/ide/devices](https://graph.api.smartthings.com/ide/devices))
  
  	 -  Name: Arduino Relay Board
	- Author: badgermanus@gmail.com
     -	Capabilities:
			Poll
	- Custom Capabilities:  RelayOn1, RelayOff1... through RelayOn8, RelayOff8 (see CustomCommands.png)   
	
			
 
3. Switch your ThingShield to use the new device type:device/list)  
  
 	- Go to [https://graph.api.smartthings.com/device/list](https://graph.api.smartthings.com/device/list)
 	- Click the Arduino/ThingShield device
	- Press Edit
	- Change the type to "Arduino Relay Board" - it will be near the bottom of the list
	- Press Update
 		

## CONTROLLING FROM REGULAR SMARTAPPS

One of the (current) issues with the SmartThings system is that it doesn't support single devices with multiple identical capabilities (such as multiple switches). The workaround for this is to create separate 'virtual' switch devices which CAN be controlled from regular SmartApps. Then use a custom SmartApp to map events on these virtual switches to the relay board.

There is a discussion of this problem [here](http://build.smartthings.com/forums/topic/multiple-devices-on-one-sheild/)

See VirtualSwitch.groovy and VirtualSwitchParent.groovy for examples.

  
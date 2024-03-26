import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int>{
    
    var fieldData = initialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int{
        return fieldData
    }
    
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int){
        if(value in minValue..maxValue){
            fieldData = value
        }
    }
    
}
open class SmartDevice protected constructor(val name: String, val category: String){
    var deviceStatus =  "off" 
    	protected set
    open val deviceType = "unknown"
    constructor(name: String, category: String, statusCode: Int):this(name,category){
        deviceStatus = when(statusCode){
            0 -> "off"
            1 -> "on"
            else -> "unknown"
        }
    }
    open fun turnOn(){
        deviceStatus = "on"
    }
    open fun turnOff(){
        deviceStatus = "off"
    }
    open fun printDeviceInfo(){
        println("Device name: $name, category: $category, type: $deviceType, deviceStatus: $deviceStatus")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) : 
	SmartDevice(name = deviceName, category = deviceCategory){
        override val deviceType = "Smart TV"
        private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0,maxValue = 100)
       	private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0,maxValue = 200)
        fun increaseSpeakerVolume(){
            speakerVolume++
            println("Speaker volume increased to $speakerVolume.")
        }
        fun decreaseVolume(){
            speakerVolume--
            println("Speaker volume decreased to $speakerVolume.")
        }
        fun nextChannel(){
            channelNumber++
            println("Channel number changed to $channelNumber.")
        }
        fun previousChannel(){
            channelNumber--
            println("Channel number changed to $channelNumber.")
        }
        override fun turnOn(){
            super.turnOn()
            println("$name is turned on. Speaker volume is set to $speakerVolume and channel number is set to $channelNumber")
        }
		override fun turnOff(){
            super.turnOff()
            println("$name turned off")
        }
        override fun printDeviceInfo(){
            super.printDeviceInfo()
        	println("speakerVolume: $speakerVolume, channelNumber: $channelNumber")
    	}
    }

class SmartLightDevice(deviceName: String, deviceCategory: String) : 
	SmartDevice(name = deviceName, category = deviceCategory){
        override val deviceType = "Smart Light"
        var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0,maxValue = 100)
        fun increaseBrightness(){
            brightnessLevel++
            println("Brightness increased to $brightnessLevel.")
        }
        fun decreaseBrightness(){
            brightnessLevel--
            println("Brightness decreased to $brightnessLevel.")
        }
        override fun turnOn(){
            super.turnOn()
            brightnessLevel = 2
            println("$name turned on. The brightness level is $brightnessLevel.")
        }
        override fun turnOff(){
            super.turnOff()
            brightnessLevel = 0
            println("$name turned off")
        }
        override fun printDeviceInfo(){
            super.printDeviceInfo()
        	println("brightnessLevel: $brightnessLevel")
    	}
    }
    
class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
){
    var deviceTurnOnCount = 0
    	private set
    fun turnOnTv(){
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    fun turnOffTv(){
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }
    fun increaseTvVolume(){
        if(smartTvDevice.deviceStatus == "on")
        	smartTvDevice.increaseSpeakerVolume()
    }
    fun decreaseTvVolume(){
        if(smartTvDevice.deviceStatus == "on")
        	smartTvDevice.decreaseVolume()
    }
    fun changeTVChannelToNext(){
        if(smartTvDevice.deviceStatus == "on")
        	smartTvDevice.nextChannel()
    }
    fun changeTVChannelToPrevious(){
        if(smartTvDevice.deviceStatus == "on")
        	smartTvDevice.previousChannel()
    }
    fun turnOnLight(){
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }
    fun turnOffLight(){
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseLightBrightness(){
        if(smartLightDevice.deviceStatus == "on")
        	smartLightDevice.increaseBrightness()
    }
    fun decreaseLightBrightness(){
        if(smartLightDevice.deviceStatus == "on")
        	smartLightDevice.decreaseBrightness()
    }
    fun turnOffAllDevices(){
        turnOffLight()
        turnOffTv()
    }
    fun printSmartTvInfo(){
        smartTvDevice.printDeviceInfo()
    }
    fun printSmartLightInfo(){
        smartLightDevice.printDeviceInfo()
    }
} 

fun main() {
    val smartHome = SmartHome(
        SmartTvDevice("Android Tv", "Entertainment"), 
        SmartLightDevice("Google Light", "Utility")
    )
    smartHome.turnOnTv()
    smartHome.printSmartTvInfo()
    smartHome.changeTVChannelToNext()
    smartHome.increaseTvVolume()
    smartHome.decreaseTvVolume()
    smartHome.changeTVChannelToPrevious()
    smartHome.turnOnLight()
    smartHome.printSmartLightInfo()
    smartHome.increaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.turnOffAllDevices()
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
}

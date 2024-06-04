package me.project.kmparticle


//nanti dibenerin pas pake mac
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform {
    actual val osName: String
        get() = "UIDevice.currentDevice.systemName"
    actual val osVersion: String
        get() = "UIDevice.currentDevice.systemVersion"
    actual val deviceModel: String
        get() = "UIDevice.currentDevice.model"
    actual val density: Int
        get() = 0 //UISCreen.mainScreen.scale.toInt()

    actual fun logSystemInfo() {
        //NSLog($osName, $osVersion, $deviceModel, $density)
    }

}
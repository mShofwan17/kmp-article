package me.project.kmparticle

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform actual constructor() {
    actual val osName: String
        get() = System.getProperty("os.name") ?: "Desktop"
    actual val osVersion: String
        get() = System.getProperty("os.version") ?: "1.0"
    actual val deviceModel: String
        get() = "Desktop"
    actual val density: Int
        get() = 0

    actual fun logSystemInfo() {
        println("$osName, $osVersion, $deviceModel, $density")
    }

}
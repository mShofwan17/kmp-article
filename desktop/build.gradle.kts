import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatformPlugin)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets{
        named("jvmMain"){
            dependencies{
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "me.project.kmparticle"
            packageVersion = "1.0.0"

            macOS {
                bundleID = "me.project.kmparticle"
            }

            windows {

            }

            linux {

            }
        }
    }
}
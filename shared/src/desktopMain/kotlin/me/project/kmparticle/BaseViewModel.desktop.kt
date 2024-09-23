package me.project.kmparticle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class BaseViewModel actual constructor() {
    actual val scope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)
}
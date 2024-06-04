package me.project.kmparticle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
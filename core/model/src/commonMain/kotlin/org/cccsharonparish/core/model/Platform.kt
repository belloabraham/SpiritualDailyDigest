package org.cccsharonparish.core.model

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
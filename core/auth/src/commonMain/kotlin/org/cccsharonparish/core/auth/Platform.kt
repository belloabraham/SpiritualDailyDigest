package org.cccsharonparish.core.auth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
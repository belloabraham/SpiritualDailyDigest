package org.cccsharonparish.core.common.helpers

import kotlinx.coroutines.flow.StateFlow

class Device(private val connection: Connection) {

    fun isConnected(): Boolean {
        return connection.isConnected()
    }

    fun connectionState(): StateFlow<Boolean> {
        return  connection.connectionState()
    }

}
package org.cccsharonparish.core.common.helpers

import com.plusmobileapps.konnectivity.Konnectivity
import kotlinx.coroutines.flow.StateFlow

class Connection {

    private val konnectivity = Konnectivity()

    fun isConnected(): Boolean {
        return  konnectivity.isConnected
    }

    fun connectionState(): StateFlow<Boolean> {
        return  konnectivity.isConnectedState
    }

}
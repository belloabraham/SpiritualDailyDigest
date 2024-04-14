package org.cccsharonparish.feature.permission

import cafe.adriel.voyager.core.lifecycle.JavaSerializable

data class PermissionUIState(val title: String, val rationale: String, val proceedText: String) :
    JavaSerializable

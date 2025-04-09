package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable

@Serializable
class RemoteLanguage (
    val countryCode: String,
    val code: String,
    val label: String
)
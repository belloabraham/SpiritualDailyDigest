package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.ILanguage

@Serializable
class RemoteLanguage (
    override val countryCode: String,
    override val code: String,
    override val label: String
) : ILanguage
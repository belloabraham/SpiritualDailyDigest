package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.local.Language

@Serializable
class RemoteLanguage(
    override val id: String,
    override val countryCode: String,
    override val code: String,
    override val label: String,
) : ILanguage

fun RemoteLanguage.toLanguage(): Language {
    val remoteLanguage = this
    return Language().apply {
        id = remoteLanguage.id
        countryCode = remoteLanguage.countryCode
        code = remoteLanguage.code
        label = remoteLanguage.label
    }
}
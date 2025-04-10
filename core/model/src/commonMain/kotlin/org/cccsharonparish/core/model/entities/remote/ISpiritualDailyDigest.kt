package org.cccsharonparish.core.model.entities.remote

interface ISpiritualDailyDigest {
    val id: String
    val year: Int
    val month: Int
    val day: Int
    val imagePath: String?
    val tags: List<String>
    val contents: List<IContent>
}
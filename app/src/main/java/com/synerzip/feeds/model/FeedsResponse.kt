package com.synerzip.feeds.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class FeedsResponse(val feed: Feed)
data class Feed(
    val author: Author,
    val entry: List<ImEntity>,
    val updated: Common,
    val rights: Common,
    val title: Common,
    val icon: Common,
    val link: List<Common>,
    val id: Common
)

data class Author(
    @SerializedName("name") val name: Common,
    @SerializedName("uri") val uri: Common
)

@Entity(tableName = "entity_table")
data class ImEntity(
    @SerializedName("im:name") val name: Common,
    @SerializedName("rights") val rights: Common,
    @SerializedName("im:price") val price: Common,
    @SerializedName("im:image") val image: List<Common>,
    @SerializedName("im:artist") val artist: Common,
    val title: Common,
    val link: Common,
    val id: Common,
    @SerializedName("im:contentType") val contentType: Common,
    val category: Common,
    @SerializedName("im:releaseDate") val releaseDate: Common
)

@Entity(tableName = "commons_table")
data class Common(val label: String, val attributes: Attributes)

@Entity(tableName = "attributes_table")
data class Attributes(
    val amount: String,
    val currency: String,
    val height: String,
    val rel: String,
    val type: String,
    val href: String,
    val term: String,
    val label: String,
    val scheme: String,
    @SerializedName("im:id") val id: String
)
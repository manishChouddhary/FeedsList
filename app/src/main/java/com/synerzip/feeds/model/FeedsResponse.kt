package com.synerzip.feeds.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "im_id") val im_id : Int,
    @SerializedName("im:name") @ColumnInfo(name = "name")val name: Common?,
    @SerializedName("rights") @ColumnInfo(name = "right")val rights: Common?,
    @SerializedName("im:price") @ColumnInfo(name = "price") val price: Common?,
    @SerializedName("im:image") @ColumnInfo(name = "image")val image: List<Common>?,
    @SerializedName("im:artist") @ColumnInfo(name = "artist")val artist: Common?,
    @ColumnInfo(name = "title") val title: Common?,
    @ColumnInfo(name = "link") val link: Common?,
    @ColumnInfo(name = "id")val id: Common?,
    @SerializedName("im:contentType") @ColumnInfo(name = "contentType") val contentType: Common?,
    @ColumnInfo(name = "category") val category: Common?,
    @SerializedName("im:releaseDate") @ColumnInfo(name = "releaseDate") val releaseDate: Common?
)

data class Common(val label: String, val attributes: Attributes)

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
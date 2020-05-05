package com.synerzip.feeds.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class EntityTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toEntity(entity: String): ImEntity? =
            Gson().fromJson(entity,ImEntity::class.java)

        @TypeConverter
        @JvmStatic
        fun fromEntity(imEntity: ImEntity) : String? =
            Gson().toJson(imEntity,ImEntity::class.java)
    }
}

open class CommonTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toCommon(common: String): Common? =
            Gson().fromJson(common,Common::class.java)

        @TypeConverter
        @JvmStatic
        fun fromCommon(common: Common) : String? =
            Gson().toJson(common,Common::class.java)

        @TypeConverter
        @JvmStatic
        fun toCommonList(commonList: String): List<Common> {
            val type = object :TypeToken<List<Common>>(){}.type
            return Gson().fromJson(commonList,type)
        }

        @TypeConverter
        @JvmStatic
        fun fromCommonList(commonList:  List<Common>): String {
            val type = object :TypeToken<List<Common>>(){}.type
            return Gson().toJson(commonList,type)
        }

    }
}

open class AttributeTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toAttribute(attributes: String): Attributes? =
            Gson().fromJson(attributes,Attributes::class.java)

        @TypeConverter
        @JvmStatic
        fun fromAttribute(attributes: Attributes) : String? =
            Gson().toJson(attributes,Attributes::class.java)
    }
}
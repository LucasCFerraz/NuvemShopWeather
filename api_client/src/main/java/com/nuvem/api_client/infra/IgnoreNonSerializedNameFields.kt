package com.nuvem.api_client.infra

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.annotations.SerializedName

internal class IgnoreNonSerializedNameFields : ExclusionStrategy {

    override fun shouldSkipField(f: FieldAttributes): Boolean =
        f.getAnnotation(SerializedName::class.java) == null

    override fun shouldSkipClass(clazz: Class<*>): Boolean = false

    companion object {

        fun create(): ExclusionStrategy {
            return IgnoreNonSerializedNameFields()
        }
    }
}

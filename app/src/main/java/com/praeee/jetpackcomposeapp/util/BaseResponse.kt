package com.praeee.jetpackcomposeapp.util

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseCoinResponse<T>(
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val `data`: T?,
)
package com.praeee.jetpackcomposeapp.data.entity.coin_detail_response

data class Supply(
    val circulating: String,
    val confirmed: Boolean,
    val max: String,
    val supplyAt: Int,
    val total: String
)
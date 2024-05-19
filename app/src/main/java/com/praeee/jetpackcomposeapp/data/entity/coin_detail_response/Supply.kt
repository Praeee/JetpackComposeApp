package com.praeee.jetpackcomposeapp.data.entity.coin_detail_response

data class Supply(
    val circulating: String?="",
    val confirmed: Boolean?=false,
    val max: String?="",
    val supplyAt: Int?=0,
    val total: String?=""
)
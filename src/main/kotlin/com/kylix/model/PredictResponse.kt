package com.kylix.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("result")
    val result: String?,
)

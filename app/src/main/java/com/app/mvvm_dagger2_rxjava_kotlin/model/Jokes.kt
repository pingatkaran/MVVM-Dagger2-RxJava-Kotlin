package com.app.mvvm_dagger2_rxjava_kotlin.model

import com.google.gson.annotations.SerializedName

data class Jokes(

    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("setup") val setup: String,
    @SerializedName("punchline") val punchline: String

)


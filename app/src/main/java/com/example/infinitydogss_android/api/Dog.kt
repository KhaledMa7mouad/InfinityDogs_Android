package com.example.infinitydogss_android.api

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("message")
    val imageLink: String
)
package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable //Cho phép class hỗ trợ chuyển đổi giữa JSON và Kotlin objects
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src") // Đổi tên thuộc tính từ img_src (JSON) thành imgSrc trong Kotlin
    val imgSrc: String
)

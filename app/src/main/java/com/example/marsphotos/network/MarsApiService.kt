package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

    private const val BASE_URL = // URL gốc của server API mà ứng dụng sẽ gọi để lấy dữ liệu.
        "https://android-kotlin-fun-mars-server.appspot.com"


    private val retrofit = Retrofit.Builder() // Tạo một đối tượng Retrofit để thực hiện các yêu cầu HTTP.
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) // Chỉ định cách chuyển đổi dữ liệu JSON thành các đối tượng Kotlin.
        .baseUrl(BASE_URL) // Chỉ định URL gốc của server API mà ứng dụng sẽ gọi để lấy dữ liệu.
        .build()

    // Khai báo một interface để định nghĩa các yêu cầu HTTP.
    interface MarsApiService {
        @GET("photos") // Định nghĩa một yêu cầu GET đến endpoint "photos" của server API.
        suspend fun getPhotos(): List<MarsPhoto>
    }

    // Đối tượng MarsApi chứa một thuộc tính retrofitService để truy cập vào dịch vụ API.
    object MarsApi {
        val retrofitService: MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
}

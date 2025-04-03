/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

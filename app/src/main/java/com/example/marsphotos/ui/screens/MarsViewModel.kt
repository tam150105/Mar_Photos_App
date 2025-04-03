package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


//Xác dịnh trạng UI của ứng dụng Mars
sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState //Xác định trạng thái thành công
    object Error : MarsUiState //Xác định trạng thái lỗi
    object Loading : MarsUiState //Xác định trạng thái đang tải
}

class MarsViewModel : ViewModel() {
    // Lưu trữ trạng thái UI của ứng dụng Mars
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    // Tu khởi tạo ViewModel và gọi hàm getMarsPhotos() để lấy dữ liệu từ API
    init {
        getMarsPhotos()
    }

    // Hàm lấy dữ liệu từ API và cập nhật trạng thái UI
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading // Cập nhật trạng thái thành Loading khi bắt đầu tải dữ liệu
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos() // Gọi API để lấy danh sách ảnh Mars
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved" // Cập nhật trạng thái thành công với số lượng ảnh
                )
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
}

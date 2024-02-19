package com.example.belajarbottomnavigation.ui.notifications

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belajarbottomnavigation.data.response.CustomerReviewsItem
import com.example.belajarbottomnavigation.data.response.PostViewResponse
import com.example.belajarbottomnavigation.data.response.Restaurant
import com.example.belajarbottomnavigation.data.response.RestaurantResponse
import com.example.belajarbottomnavigation.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {

    init {
        findRestaurant()
    }

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restaurant

    private val _listReview = MutableLiveData<List<CustomerReviewsItem>>()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "VIEW MODEL"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    private fun findRestaurant() {
        _isLoading.value = true
        val client = ApiConfig.getApiServices().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _restaurant.value = response.body()?.restaurant
                    _listReview.value =
                        response.body()?.restaurant?.customerReviews as List<CustomerReviewsItem>?
                } else {
                    Log.e(TAG, "OnFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                Log.e(TAG, "OnFailure ${t.message}")
            }
        })
    }

    private fun postReview(review: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiServices().postReview(RESTAURANT_ID, "Royhan", review)
        client.enqueue(object : Callback<PostViewResponse> {
            override fun onResponse(
                call: Call<PostViewResponse>,
                response: Response<PostViewResponse>
            ) {
                if (response.isSuccessful) {
                    _listReview.value = response.body()?.customerReviewsItem
                } else {
                    Log.e(TAG, "OnFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostViewResponse>, t: Throwable) {
                Log.e(TAG, "OnFailure ${t.message}")
            }
        })
    }
}
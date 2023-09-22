package com.example.githubuserapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _user = MutableLiveData<DetailUserResponse>()
    val user: LiveData<DetailUserResponse> = _user

    private val _userFollowers = MutableLiveData<List<FollowItem>>()
    val userFollowers: LiveData<List<FollowItem>> = _userFollowers

    private val _userFollowing = MutableLiveData<List<FollowItem>>()
    val userFollowing: LiveData<List<FollowItem>> = _userFollowing

    private val _isLoadingFolls = MutableLiveData<Boolean>()
    val isLoadingFolls: LiveData<Boolean> = _isLoadingFolls

    companion object{
        private const val TAG = "DetailViewModel"
    }

    init {
        findDetailUser()
        findUserFollowers()
        findUserFollowings()
    }

    fun findDetailUser(user: String = "Arif"){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username = user)

        client.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>,
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun findUserFollowings(user: String = "Arif"){
        _isLoadingFolls.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username = user)

        client.enqueue(object : Callback<List<FollowItem>> {
            override fun onResponse(
                call: Call<List<FollowItem>>,
                response: Response<List<FollowItem>>,
            ) {
                _isLoadingFolls.value = false
                if (response.isSuccessful) {
                    _userFollowing.value = response.body()
                    Log.d("USER FOLLOWING", "FOLLOWING : ${userFollowing.value}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowItem>>, t: Throwable) {
                _isLoadingFolls.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun findUserFollowers(user: String = "Arif"){
        _isLoadingFolls.value = true
        val client = ApiConfig.getApiService().getUserFollower(username = user)

        client.enqueue(object : Callback<List<FollowItem>> {
            override fun onResponse(
                call: Call<List<FollowItem>>,
                response: Response<List<FollowItem>>,
            ) {
                _isLoadingFolls.value = false
                if (response.isSuccessful) {
                    _userFollowers.value = response.body()
                    Log.d("USER FOLLOWER", "FOLLOWER : ${userFollowers.value}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowItem>>, t: Throwable) {
                _isLoadingFolls.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }



}
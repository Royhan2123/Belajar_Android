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

    private val _userFollowers = MutableLiveData<ArrayList<FollowItem>>()
    val userFollowers: LiveData<ArrayList<FollowItem>> = _userFollowers

    private val _userFollowing = MutableLiveData<ArrayList<FollowItem>>()
    val userFollowing: LiveData<ArrayList<FollowItem>> = _userFollowing

    private val _isLoadingFolls = MutableLiveData<Boolean>()
    val isLoadingFolls: LiveData<Boolean> = _isLoadingFolls


    companion object{
        private const val TAG = "DetailViewModel"
    }


    fun findDetailUser(user: String){
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

    fun findUserFollowings(user: String){
        _isLoadingFolls.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username = user)
        client.enqueue(object : Callback<ArrayList<FollowItem>> {
            override fun onResponse(
                call: Call<ArrayList<FollowItem>>,
                response: Response<ArrayList<FollowItem>>,
            ) {
                _isLoadingFolls.value = false
                if (response.isSuccessful) {
                    _userFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowItem>>, t: Throwable) {
                _isLoadingFolls.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun findUserFollowers(user: String){
        _isLoadingFolls.value = true
        val client = ApiConfig.getApiService().getUserFollower(username = user)
        client.enqueue(object : Callback<ArrayList<FollowItem>> {
            override fun onResponse(
                call: Call<ArrayList<FollowItem>>,
                response: Response<ArrayList<FollowItem>>,
            ) {
                _isLoadingFolls.value = false
                if (response.isSuccessful) {
                    _userFollowers.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowItem>>, t: Throwable) {
                _isLoadingFolls.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }



}
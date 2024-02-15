package com.example.belajarbottomnavigation.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.belajarbottomnavigation.adapter.ReviewAdapter
import com.example.belajarbottomnavigation.data.response.CustomerReviewsItem
import com.example.belajarbottomnavigation.data.response.Restaurant
import com.example.belajarbottomnavigation.data.response.RestaurantResponse
import com.example.belajarbottomnavigation.data.retrofit.ApiConfig
import com.example.belajarbottomnavigation.databinding.FragmentNotificationsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "Notification Fragment"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
        private const val BASE_URL = "https://restaurant-api.dicoding.dev/images/large/"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvReview.layoutManager = layoutManager


        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        findRestaurant()
    }

    private fun findRestaurant(){
        showLoading(true)
        val client = ApiConfig.getApiServices().getRestaurant(RESTAURANT_ID)

        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        responseBody.restaurant?.let { setRestaurant(it) }
                        reviewData(responseBody.restaurant?.customerReviews)
                    } else {
                        Log.e(TAG,"OnFailure ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
               Log.e(TAG,"OnFailure ${t.message}")
            }
        })
    }

    private fun setRestaurant(restaurant: Restaurant) {
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(requireContext())
            .load("$BASE_URL/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }

    private fun reviewData(consumer: List<CustomerReviewsItem?>?) {
        val adapter = ReviewAdapter()
        binding.rvReview.adapter = adapter
        adapter.submitList(consumer)
        binding.edReview.setText("")
    }
    private fun showLoading(isLoading:Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
package com.example.belajarbottomnavigation.ui.notifications

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.belajarbottomnavigation.adapter.ReviewAdapter
import com.example.belajarbottomnavigation.data.response.CustomerReviewsItem
import com.example.belajarbottomnavigation.data.response.PostViewResponse
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
    private lateinit var notificationViewModel: NotificationsViewModel

    companion object {
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

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvReview.layoutManager = layoutManager


        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        binding.btnSend.setOnClickListener {
         notificationViewModel.postReview(binding.edReview.text.toString())
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        notificationViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[NotificationsViewModel::class.java]

        notificationViewModel.listReview.observe(this){consumerReviews ->
            setReviewData(consumerReviews)
        }

        notificationViewModel.isLoading.observe(this){
            showLoading(it)
        }

        notificationViewModel.restaurant.observe(this){ restaurant ->
            setRestaurant(restaurant)
        }
    }

    private fun setRestaurant(restaurant: Restaurant) {
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(requireContext())
            .load("$BASE_URL/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }

    private fun setReviewData(consumer: List<CustomerReviewsItem?>?) {
        val adapter = ReviewAdapter()
        binding.rvReview.adapter = adapter
        adapter.submitList(consumer)
        binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
package com.example.belajarbottomnavigation.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.belajarbottomnavigation.R
import com.example.belajarbottomnavigation.databinding.FragmentDashboardBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG = DashboardFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomQoute()

        binding.btnAllQuotes.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_navigation_dashboard_to_listQuotesActivity)
        }
    }

    private fun getRandomQoute() {
        val url = "https://quote-api.dicoding.dev/random"
        val client = AsyncHttpClient()
        binding.progressBar.visibility = View.VISIBLE

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {

                binding.progressBar.visibility = View.VISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)

                try {

                    val responseObject = JSONObject(result)
                    val qoute = responseObject.getString("en")
                    val author = responseObject.getString("author")

                    binding.tvQuote.text = qoute
                    binding.tvAuthor.text = author

                } catch (e: Exception) {

                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()

                }
            }


            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
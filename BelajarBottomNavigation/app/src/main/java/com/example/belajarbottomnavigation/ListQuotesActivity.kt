package com.example.belajarbottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belajarbottomnavigation.databinding.ActivityListQuotesBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class ListQuotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListQuotesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listQuotes.setHasFixedSize(true)
        binding.progressBar.visibility = View.VISIBLE

        getListQoute()
    }

    private fun getListQoute(){
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/list"

        client.get(url,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
               val errorMessage = when(statusCode){
                   401 -> "$statusCode : Bad Request "
                   403 -> "$statusCode : Forbidden "
                   404 -> "$statusCode : Not Found "
                   else -> "$statusCode : ${error.message}"
               }
                Toast.makeText(this@ListQuotesActivity,errorMessage,Toast.LENGTH_SHORT).show()
            }

        })
    }
}
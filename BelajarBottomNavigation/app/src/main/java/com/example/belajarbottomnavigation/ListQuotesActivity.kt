package com.example.belajarbottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belajarbottomnavigation.adapter.ListQouteAdapter
import com.example.belajarbottomnavigation.databinding.ActivityListQuotesBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class ListQuotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListQuotesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listQuotes.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listQuotes.addItemDecoration(itemDecoration)

        binding.listQuotes.layoutManager = layoutManager

        getList()
    }

    private fun getList() {
        val url = "https://quote-api.dicoding.dev/list"
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
                val list = ArrayList<String>()

                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()){
                        val responseObject = jsonArray.getJSONObject(i)

                        val en = responseObject.getString("en")
                        val author = responseObject.getString("author")

                        list.add("\n$en\n - $author\n")
                    }

                    binding.listQuotes.adapter = ListQouteAdapter(list)
                } catch (e: Exception) {
                    throw e
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                binding.progressBar.visibility = View.VISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request "
                    403 -> "$statusCode : Forbidden "
                    404 -> "$statusCode : Not Found "
                    else -> "$statusCode : ${error.message}"
                }

                Toast.makeText(this@ListQuotesActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
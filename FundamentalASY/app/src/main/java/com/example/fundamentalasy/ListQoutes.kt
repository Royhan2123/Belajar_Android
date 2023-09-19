package com.example.fundamentalasy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamentalasy.databinding.ActivityListQoutesBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class ListQoutes : AppCompatActivity() {
    private lateinit var binding:ActivityListQoutesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_qoutes)
        binding = ActivityListQoutesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.listQuotes.setLayoutManager(layoutManager)
        val itemDecoration = DividerItemDecoration(this,layoutManager.orientation)
        binding.listQuotes.addItemDecoration(itemDecoration)

        getData()
    }
    private fun getData(){
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/list"

        client.get(url,object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val listQoute = ArrayList<String>()
                binding.progressBar.visibility = View.VISIBLE

                val result = responseBody?.let { String(it) }
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val qoute = jsonObject.getString("en")
                        val author = jsonObject.getString("author")
                        listQoute.add("\n$qoute\n - $author\n")
                    }
                    val adapter = QouteAdapter(listQoute)
                    binding.listQuotes.adapter = adapter
                }catch (e:Exception){
                    Toast.makeText(this@ListQoutes,e.message,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when(statusCode){
                    401 -> "$statusCode : Not Respond"
                    403 -> "$statusCode : Bad Request"
                    404 -> "$statusCode : Error"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@ListQoutes,errorMessage,Toast.LENGTH_SHORT).show()
            }
        })
    }
}
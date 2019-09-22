package com.example.listapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.content_main3.*
import android.widget.Toast
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray


class MainActivity3() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ListOfLists()
        
    }

    private fun ListOfLists() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.3:40000/showListArray"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val arrayListObject = ArrayList<Product>()
                for(item in 0 until response.length()){
                  arrayListObject.add(Product(response.getJSONObject(item).getString("name")))
                }
                recyclerViewList.layoutManager = LinearLayoutManager(this)
                recyclerViewList.adapter = MyAdapter(arrayListObject, this){
                    val intent = Intent(this, Main2Activity::class.java)
                    intent.putExtra("name",it.name)
                    startActivity(intent)
                    Toast.makeText(this, "${it.name}", Toast.LENGTH_SHORT).show()
                }
    },
            Response.ErrorListener { error ->
                // TODO: Handle error
                print(error)
            }
        )
        queue.add(jsonArrayRequest)
    }
}

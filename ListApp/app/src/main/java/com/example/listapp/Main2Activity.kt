package com.example.listapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import kotlinx.android.synthetic.main.content_main3.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ListOfProducts()
    }

    private fun ListOfProducts() {
        val queue = Volley.newRequestQueue(this)
        val list = intent.getStringExtra("name")
        val url = "http://192.168.1.3:40000/showListArray/${list}"
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val arrayListObject = ArrayList<Product>()
                    for(item in (0 until response.length())){
                        arrayListObject.add(Product(response.getJSONObject(item).getString("name")))
                    }
                recyclerViewProducts.layoutManager = LinearLayoutManager(this)
                recyclerViewProducts.adapter = MyAdapter(arrayListObject, this){
                    Toast.makeText(this, "${it.name}", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                print(error)
            }
        )
        queue.add(jsonObjectRequest)
    }

}

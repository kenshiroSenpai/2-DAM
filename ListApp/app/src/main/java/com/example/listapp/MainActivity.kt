package com.example.listapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main2.*
import org.json.JSONObject



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        buttonPostList.setOnClickListener{
            val vali = """[0-9]*[a-zA-Z]+[0-9]*""".toRegex()
            if(vali.matches(textHere.text.toString())){
                requestToServer()
            }else{
                Toast.makeText(getApplicationContext(),
                    "bad name, please rewrite", Toast.LENGTH_LONG).show()
            }
        }

        ButtonPage2.setOnClickListener{
            var intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
    private fun requestToServer() {
        val queue = Volley.newRequestQueue(this)
        val params = JSONObject()
        params.put("name", textHere.text.toString())
        val key = params.keys().next()
        val url = "http://192.168.1.3:40000/searchListObject"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url + "?"+ key +"=" + params.getString(key), null,
             Response.Listener{ response ->
                 val arrayListObject = ArrayList<Product>()
                 for(item in 0 until response.getJSONArray("products").length()){
                     arrayListObject.add(Product(response.getJSONArray("products").getJSONObject(item).getString("name")))
                 }
                 recyclerViewSearch.layoutManager = LinearLayoutManager(this)
                 recyclerViewSearch.adapter = MyAdapter(arrayListObject, this){}
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                print(error)
            }
        )
        queue.add(jsonObjectRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

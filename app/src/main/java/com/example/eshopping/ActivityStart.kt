package com.example.eshopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopping.Adapter.*
import com.google.android.material.navigation.NavigationBarItemView

class ActivityStart : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var adapter: h_RecycleView
    private lateinit var adapter2: v_RecycleView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = h_RecycleView()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        recyclerView2 = findViewById(R.id.recyclerView2)
        adapter2 = v_RecycleView()




        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView2.adapter = adapter2




       val btn: NavigationBarItemView = findViewById(R.id.page_3)
        btn.setOnClickListener {
            val intent = Intent(this, itemselected::class.java)
            startActivity(intent)
        }

        val btn2: NavigationBarItemView = findViewById(R.id.page_1)
        btn2.setOnClickListener {
            val intent = Intent(this, ActivityStart::class.java)
            startActivity(intent)
        }

        val btn3: NavigationBarItemView = findViewById(R.id.page_4)
        btn3.setOnClickListener {
            val intent = Intent(this, cart::class.java)
            startActivity(intent)
        }




    }



}
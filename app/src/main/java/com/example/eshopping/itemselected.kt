package com.example.eshopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.example.eshopping.Adapter.image2
import com.example.eshopping.Adapter.priceItem
import com.example.eshopping.Adapter.titleImage

class itemselected : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemselected)

        var img: ImageView = findViewById(R.id.itmImage)
        var t: TextView = findViewById(R.id.itemstitle)
        var p: TextView = findViewById(R.id.itemsPrice)

        t.setText(titleImage)
        p.setText(priceItem)
        img.setImageResource(image2)

        val button: Button =findViewById(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this, cart::class.java)
            intent.putExtra(titleImage,"key")
            startActivity(intent)
        }

    }





}
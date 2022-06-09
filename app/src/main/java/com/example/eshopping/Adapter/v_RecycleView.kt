package com.example.eshopping.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopping.R
import com.example.eshopping.itemselected


var titleImage: String = ""
var priceItem: String = ""
var image2: Int = 0




class v_RecycleView: RecyclerView.Adapter<v_RecycleView.MyViewHolder>() {



    private var title = arrayOf("Adidas Men's basic sport T-shirt", "Fashion short sleeve dress")
    private var title2 = arrayOf("Elegant work tops", "Apple watch series 7")
    private var images = intArrayOf(R.drawable.mens, R.drawable.ckids)
    private var images2 = intArrayOf(R.drawable.womans, R.drawable.watch)

    private var price = arrayOf("10.99","7.99")
    private var price2 = arrayOf("21.99", "430.99")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.col, parent, false)
        return  MyViewHolder(view)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemTitle.text = title[position]
        holder.itemTitle2.text = title2[position]
        holder.itemImage.setImageResource(images[position])
        holder.itemImage2.setImageResource(images2[position])

        holder.itemprice.text = price[position]
        holder.itemprice2.text = price2[position]



        holder.cartImage.setOnClickListener {
            titleImage = title[position]
            priceItem = price[position]
            image2 = images[position]

        }

        holder.cartImage2.setOnClickListener {
            titleImage = title2[position]
            priceItem = price2[position]
            image2 = images2[position]

        }





    }



    override fun getItemCount(): Int {
        return 2
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemImage2: ImageView
        var itemTitle: TextView
        var itemTitle2: TextView

        var cartImage: ImageView
        var cartImage2: ImageView

        var itemprice: TextView
        var itemprice2: TextView


        init {
            itemImage = itemView.findViewById(R.id.imageView3)
            itemImage2 = itemView.findViewById(R.id.imageView4)
            itemTitle = itemView.findViewById(R.id.title1)
            itemTitle2 = itemView.findViewById(R.id.title2)

            itemprice = itemView.findViewById(R.id.price1)
            itemprice2 = itemView.findViewById(R.id.price2)

            cartImage = itemView.findViewById(R.id.cartImg)
            cartImage2 = itemView.findViewById(R.id.cartImg2)


        }





    }

}
package com.example.eshopping.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopping.R
import com.example.eshopping.itemselected

class cart_RecycleView: RecyclerView.Adapter<cart_RecycleView.MyViewHolder>() {


    private var title = arrayOf("Men", "Women", "Kids", "Accessories")
    private var images = intArrayOf(R.drawable.men, R.drawable.woman, R.drawable.kids, R.drawable.accessories)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return  MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemTitle.text = title[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return 4
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView


        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.categtxt)
        }

    }
}
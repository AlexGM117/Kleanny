package com.creamoslab.kleanny.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.creamoslab.kleanny.R

class MyVisitsAdapter(private val items: List<String>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val place = items[position]
        holder.placeName(place)
    }
}

class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.item_my_visits,
        parent,
        false
    )
) {
    private var mTitleTextView: TextView? = null

    init {
        mTitleTextView = itemView.findViewById(R.id.textView_place_title)
    }

    fun placeName(place: String) {
        mTitleTextView?.text = place
    }
}



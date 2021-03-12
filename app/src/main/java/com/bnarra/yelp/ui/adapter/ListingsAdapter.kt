package com.bnarra.yelp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bnarra.yelp.R
import com.bnarra.yelp.remote.model.BusinessModel
import com.bumptech.glide.Glide

class ListingsAdapter(private val context: Context): BaseAdapter() {

    companion object{
        const val INVALID_INDEX = -1
    }

    var items: List<BusinessModel>? = null

    fun addItems(items: List<BusinessModel>) {
       this.items = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = items?.get(position)

        val view = if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context);
            layoutInflater.inflate(R.layout.listings_item, parent, false);
        } else {
            convertView
        }

        val imageView = view.findViewById<ImageView>(R.id.image)
        val url = item?.imgUrl
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView)

        val textView = view.findViewById<TextView>(R.id.name)
        textView.text = item?.name

        return view
    }

    override fun getItem(position: Int): BusinessModel? = if(position != INVALID_INDEX) items?.get(position) else null

    override fun getItemId(position: Int):Long = 0

    override fun getCount(): Int = items?.size ?: 0
}
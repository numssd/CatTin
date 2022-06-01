package com.fakhrulasa.realmdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

interface CatActionListener {
    fun onCatLikes()
}

class CatListAdapter(
    private val actionListener: CatActionListener,
    private var catList: ArrayList<Cat>
) : RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemTitle: TextView = itemView.findViewById(R.id.textViewId)
        private val itemWidth: TextView = itemView.findViewById(R.id.textViewWidth)
        private val itemHeight: TextView = itemView.findViewById(R.id.textViewHeight)
        private val itemPicture: ImageView = itemView.findViewById(R.id.imageViewItemCat)

        fun bind(cat: Cat) {

            itemTitle.text = cat.id
            itemHeight.text = cat.height.toString()
            itemWidth.text = cat.width.toString()
            Glide
                .with(itemView.context)
                .load(cat.url)
                .into(itemPicture)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cat_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(catList[position])
        holder.itemView.setOnClickListener {
            actionListener.onCatLikes()
        }
    }
}

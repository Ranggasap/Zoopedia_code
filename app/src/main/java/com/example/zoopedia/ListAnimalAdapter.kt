package com.example.zoopedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.security.auth.callback.Callback

class ListAnimalAdapter(private val listAnimal: ArrayList<Animal>): RecyclerView.Adapter<ListAnimalAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_animal, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listAnimal.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name, description, photo) = listAnimal[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listAnimal[holder.adapterPosition])}
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Animal)
    }

}
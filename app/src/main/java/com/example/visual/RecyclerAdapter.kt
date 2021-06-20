package com.example.visual

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.RcItemBinding


class RecyclerAdapter(var recList: ArrayList<RecyclerClass>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.RecHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    //Класс оптимизации ресурсов
    inner class RecHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if (listener != null) {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
        val binding = RcItemBinding.bind(item)
        fun bind(recycler: RecyclerClass) = with(binding) {
            image.setImageResource(recycler.imageId)
            rcTitle.text = recycler.title
        }
    }
    //Идентификация отдельного айтема
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)
        return RecHolder(view)
    }
    //Связываем данные
    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        holder.bind(recList[position])
    }
    override fun getItemCount(): Int {
        return recList.size
    }
    fun filterList(filteredList: ArrayList<RecyclerClass>) {
        recList = filteredList
        notifyDataSetChanged()
    }
}

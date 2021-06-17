package com.example.visual

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.Controllers.SecondActivityItem
import com.example.visual.databinding.RcItemBinding


class RecyclerAdapter( var recList: ArrayList<RecyclerClass>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.RecHolder>() {
    private lateinit var fullList:Array<RecyclerClass>
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    inner class RecHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if(listener!=null) {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
        val binding = RcItemBinding.bind(item)
        fun bind(recycler: RecyclerClass) = with(binding) {
            im.setImageResource(recycler.imageid)
            rcTitle.text = recycler.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)
        return RecHolder(view)
    }
    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        holder.bind(recList[position])
    }

    override fun getItemCount(): Int {
        return recList.size
    }
    public fun filterList(filteredList:ArrayList<RecyclerClass>){
        recList=filteredList
        notifyDataSetChanged()

    }
}

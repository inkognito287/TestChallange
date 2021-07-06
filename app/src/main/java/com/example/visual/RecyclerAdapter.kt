package com.example.visual

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.RcItemBinding
import com.example.visual.model.RecyclerClass

/**
 * @param recList лист с объектами класса [RecyclerClass]
 * @param listener слушатель какого-либо активити
 * */
class RecyclerAdapter(var recList: ArrayList<RecyclerClass>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.RecHolder>() {
    /**Реализация интерфейса [OnItemClickListener]*/
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class RecHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        init {
            /**устанавливыем слушатель нажатий на Item*/
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                /**вызываем событие клика в текущем активити*/
                listener.onItemClick(adapterPosition)
            }
        }
        /**биндим [item]*/
        val binding = RcItemBinding.bind(item)
        fun bind(recycler: RecyclerClass) = with(binding) {

            /**присваем значения элементам внутри [item]*/
            image.setImageResource(recycler.imageId)
            rcTitle.text = recycler.title
        }
    }
    /**Идентификация отдельного айтема*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {

        /**заполняем view элементами*/
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)

        /**возвращаем*/
        return RecHolder(view)
    }
    /**Связываем данные*/
    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        holder.bind(recList[position])
    }
    /**получаем размер [recList]*/
    override fun getItemCount(): Int {
        return recList.size
    }
    /**сюда передаётся отфильтрованный список для его присваивания в [recList]*/
    fun filterList(filteredList: ArrayList<RecyclerClass>) {
        recList = filteredList

        /**обновляем дату*/
        notifyDataSetChanged()
    }
}

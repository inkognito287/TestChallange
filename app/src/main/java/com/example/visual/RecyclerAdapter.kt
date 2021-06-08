package com.example.visual

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.RcItemBinding
import java.security.AccessController.getContext

class Lol:AppCompatActivity() {


    class RecyclerAdapter (): RecyclerView.Adapter<RecyclerAdapter.RecHolder>() {

        var recList = ArrayList<RecyclerClass>()


        inner class RecHolder(item: View) : RecyclerView.ViewHolder(item) {

            val informationList = listOf(
                listOf(
                    "Видеонаблюдение",
                    "Видеонаблюдение",
                    "Видеонаблюдение",
                    "Видеонаблюдение",
                    "Видеонаблюдение",
                    "Видеонаблюдение"
                ),
                listOf(
                    "Управление доступом",
                    "Управление доступом",
                    "Управление доступом",
                    "Управление доступом",
                    "Управление доступом",
                    "Управление доступом"
                ),
                listOf("3", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("4", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("5", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("6", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("7", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("8", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("9", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("10", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
                listOf("3", "3", "3", "3", "3", "3", "3", "3", "3", "3")

            )
            private val imageList = listOf(
                R.drawable.rc_1,
                R.drawable.rc_2,
                R.drawable.rc_3,
                R.drawable.rc_4,
                R.drawable.rc_5,
                R.drawable.rc_6,
                R.drawable.rc_7,
                R.drawable.rc_8,
                R.drawable.rc_9,
                R.drawable.rc_10
            )
            private val adapter = RecyclerAdapter()
            // val context: Context? = null
            val binding = RcItemBinding.bind(item)
            fun bind(recycler: RecyclerClass) = with(binding) {
                im.setImageResource(recycler.imageid)
                rcTitle.text = recycler.title


                itemView.setOnClickListener() {
                    pos.posit = position
                    var intent = Intent(itemView.getContext(), ThirdActivity::class.java)
                    startActivity(itemView.getContext(),intent,null)
                    intent.putExtra("position",position)
                  //  gege(position, this@RecyclerAdapter)


//                binding.apply {
//
//                    rcView.layoutManager= LinearLayoutManager(SecondActivity())
//                    rcView.adapter =    adapter
//                    for (x in 0..informationList[position].size-1){
//                        val item = RecyclerClass(imageList[position],informationList[position][x])
//                        adapter.addItem(item)
//
//                    }
//                }


                    var MyLog = "MyLog"
                    Log.d(MyLog, position.toString())

                }


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

        fun addItem(recycler: RecyclerClass) {
            recList.add(recycler)
        }
    }

}
//}public fun gege(int:Int, context: Lol.RecyclerAdapter){
//    val intent=Intent(context,ThirdActivity::class.java)
//    intent.putExtra("positionint",int)
//}
package com.example.visual

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.RcItemBinding


class RecyclerAdapter()  : RecyclerView.Adapter<RecyclerAdapter.RecHolder>() {
        var recList = ArrayList<RecyclerClass>()
        inner  class RecHolder(item: View) : RecyclerView.ViewHolder(item),View.OnClickListener {

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
                itemView.setOnClickListener(listener)

              //  var listener1 = SecondActivity()
              //  var listener2 = ThirdActivity()

               // itemView.setOnClickListener (View.OnClickListener () {
               //   pos.posit=position
              //      itemView.setOnClickListener(listener1)

               // })
//
//
//                 }

//                {
//                            if(it.context.toString().replaceAfter("Activity","").replaceBefore("visual.","").replaceBefore('.',"")==".SecondActivity") {
//                                Log.d("MyLog",
//                                    it.context.toString().replaceAfter("Activity", "")
//                                        .replaceBefore("visual.", "").replaceBefore('.', "")
//                                )
//                                pos.posit = position
//                                var intent = Intent(itemView.context, ThirdActivity::class.java)
//                                intent.putExtra("position", position)
//
//                                it.context.startActivity(intent)
//                            }

                   // startActivity(itemView.context, intent, null)



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


//                    var MyLog = "MyLog"
//                   // Log.d(MyLog, position.toString())
//
//                }


            }

            override fun onClick(v: View?) {

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

interface OnClickListener {
    fun onClick(v: View?)
}
//}public fun gege(int:Int, context: Lol.RecyclerAdapter){
//    val intent=Intent(context,ThirdActivity::class.java)
//    intent.putExtra("positionint",int)
//}
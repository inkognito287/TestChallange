package com.example.visual.activity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.visual.R
import com.example.visual.data.ItemUrl
import com.squareup.picasso.Picasso

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        displayList()
    }

    private fun displayList() {
        val imageList = ArrayList<ItemUrl>()
        var recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        imageList.clear()
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
        imageList.add(ItemUrl("https://i.stack.imgur.com/v9y5v.png"))
       recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)

       var adapter = ViewAdapter(imageList)
        recyclerView.adapter=adapter

    }
  inner  class ViewAdapter(private val imageDataModelList: ArrayList<ItemUrl>) : RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item_image_activity, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(imageDataModelList[position])
        }

        override fun getItemCount(): Int {
            return imageDataModelList.size
        }

       inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItems(imageDataModel: ItemUrl) {
                val imageView = itemView.findViewById<SubsamplingScaleImageView>(R.id.imageView)
               Thread(Runnable {
                   var x=ImageSource.bitmap(Picasso.get().load(imageDataModel.getUrl()).get())
                   runOnUiThread { imageView.setImage(x) }
               }).start()


            }
        }
    }
}
package com.example.visual .fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.visual.R
import com.squareup.picasso.Picasso


const val ARG_OBJECT = "object"

class ImageFragment : Fragment() {

lateinit var progressBar: ProgressBar
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        progressBar= activity?.findViewById(R.id.progressBar)!!
        progressBar.visibility =View.VISIBLE
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val imageView:SubsamplingScaleImageView=view.findViewById(R.id.subscale)


            Thread(Runnable {
                val image=ImageSource.bitmap(Picasso.get().load(getString(ARG_OBJECT)).get())
            activity?.runOnUiThread { imageView.setImage(image)
                progressBar.visibility =View.GONE}

            }).start()



        }
    }
}
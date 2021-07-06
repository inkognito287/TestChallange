package com.example.visual.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.visual.R
import com.example.visual.databinding.ActivityImageBinding
import com.example.visual.databinding.FragmentImageBinding
import com.squareup.picasso.Picasso


const val ARG_OBJECT = "object"

class ImageFragment(bindingActivityImageBinding: ActivityImageBinding) : Fragment() {
    lateinit var binding: FragmentImageBinding
    private var bindingImageActivity: ActivityImageBinding? = bindingActivityImageBinding
    lateinit var progressBar: ProgressBar
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        /**находим [progressBar] и делаем его видимым*/
        /**инфлейтим fragment_image в [container]*/

       binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            val imageView: SubsamplingScaleImageView = view.findViewById(R.id.subscale)

            Thread {
                val image = ImageSource.bitmap(Picasso.get().load(getString(ARG_OBJECT)).get())
                activity?.runOnUiThread {
                    binding.subscale.setImage(image)
                   bindingImageActivity?.progressBar?.visibility = View.GONE
                }
            }.start()
        }
    }
}
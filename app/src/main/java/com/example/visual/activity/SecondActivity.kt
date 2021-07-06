package com.example.visual.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.visual.R
import com.example.visual.RecyclerAdapter
import com.example.visual.databinding.ActivitySecondBinding
import com.example.visual.fragments.BlankFragment1
import com.example.visual.fragments.fragmentnum
import com.example.visual.model.RecyclerClass

/**
 * [filteredList] глобальная переменная хранящая в себе отфильтрованный список, после
 * использования поиска по ключевым словам
 */
var filteredList: ArrayList<RecyclerClass> = ArrayList()

/**
 *[RecyclerAdapter.OnItemClickListener] наследуемся от него для того чтобы осуществить свою
 * реализацию в данном активити
 */
class SecondActivity :
    AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**создание объекта класса [ActivitySecondBinding]*/
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment1()
    }

    /**завершение активити на нажатию на кнопку стрелочка*/
    fun back(v: View) {
        if (fragmentnum == 1)
            finish()
        else {
            setFragment1()
        }
    }

    override fun onBackPressed() {
        if (fragmentnum == 1)
        super.onBackPressed()
        else {
         setFragment1()
        }
    }

    private fun setFragment1(){
        val fragment1=BlankFragment1()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment1)
        fragmentTransaction.commit()
    }
    fun notImplemented(v:View){
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
    }
}
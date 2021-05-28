package com.alirnp.babalhavaej.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alirnp.babalhavaej.R
import com.alirnp.babalhavaej.databinding.ActivityMainBinding
import com.alirnp.babalhavaej.ui.adapter.HomeItemsAdapter
import com.alirnp.babalhavaej.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


}
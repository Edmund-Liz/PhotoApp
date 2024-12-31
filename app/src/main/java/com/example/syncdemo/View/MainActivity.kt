package com.example.syncdemo.View

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.syncdemo.Adapter.ImageAdapter
import com.example.syncdemo.Model.ImageRepository
import com.example.syncdemo.Model.Pixabay.PixabayClient
import com.example.syncdemo.R
import com.example.syncdemo.ViewModel.MainViewModel
import com.example.syncdemo.ViewModel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(application,ImageRepository(PixabayClient.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main);

        recyclerView=findViewById(R.id.main_recycler)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter = ImageAdapter(emptyList())
        recyclerView.adapter = adapter

        val params = mutableMapOf<String,String>()
        params["key"] = "47950141-01b851b2499626dbb6fde32ec" // 必填
        params["q"] = "nature" // 搜索关键词
        params["image_type"] = "photo" // 图片类型
        params["orientation"] = "horizontal" // 图片方向

        viewModel.images.observe(this, Observer {images ->
            adapter.updateData(images.map { it.previewURL })
        })

        viewModel.error.observe(this, Observer { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loadImages(params)
    }
}

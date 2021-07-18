package com.example.flickrcl.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu


import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.example.flickrcl.R
import com.example.flickrcl.adapter.PhotosListAdapter
import com.example.flickrcl.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PhotosListAdapter()
        viewModel.photos.observe(this) {
            adapter.submitData(this.lifecycle, it)
        }

        binding.photoList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    binding.photoList.scrollToPosition(0)
                    viewModel.searchPhotos(query)
                    searchView.clearFocus()
                }
                return true
            }
        })
        return true
    }

}

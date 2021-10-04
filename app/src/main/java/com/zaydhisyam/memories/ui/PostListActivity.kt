package com.zaydhisyam.memories.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.databinding.ActivityPostListBinding
import com.zaydhisyam.memories.ui.adapter.PostListAdapter
import com.zaydhisyam.memories.ui.view_model.PostListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("TAG", "PostListActivity onCreate: called")

        val rvAdapter = PostListAdapter()

        rvAdapter.onViewHolderClick = { selectedPost ->
            Intent(this, DetailPostActivity::class.java).apply {
                putExtra(DetailPostActivity.EXTRA_POST, selectedPost)
                startActivity(this)
            }
        }

        with(binding.rvPostList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = rvAdapter
        }

        val postListViewModel: PostListViewModel by viewModel()

        postListViewModel.postList
            .observe(this, { postListResponse ->
                when (postListResponse) {
                    is Resource.Loading -> {
                        Log.d("TAG", "PostListActivity onCreate: resource.Loading class called")
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        rvAdapter.setAdapterListData(postListResponse.data)
                    }
                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Snackbar.make(
                            binding.root,
                            postListResponse.errorMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }
}
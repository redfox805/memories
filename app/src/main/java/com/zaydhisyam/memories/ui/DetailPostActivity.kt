package com.zaydhisyam.memories.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zaydhisyam.memories.R
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.databinding.ActivityDetailPostBinding
import com.zaydhisyam.memories.domain.model.Post
import com.zaydhisyam.memories.ui.adapter.CommentListAdapter
import com.zaydhisyam.memories.ui.view_model.DetailPostViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraPost: Post = intent.getParcelableExtra(EXTRA_POST)!!

        supportActionBar?.title = "Post Detail"

        with(binding) {
            tvPostUserName.text = extraPost.user.name
            tvPostTitle.text = extraPost.title.uppercase()
            tvPostBody.text = extraPost.body
        }

        val rvAdapter = CommentListAdapter()

        with(binding.rvCommentList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = rvAdapter
        }

        val viewModel: DetailPostViewModel by viewModel()

        viewModel.getCommentListPerPost(postId = extraPost.id)
            .observe(this, { commentListResponse ->
                when (commentListResponse) {
                    is Resource.Loading -> binding.progressCircular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        rvAdapter.setAdapterListData(commentListResponse.data)
                    }
                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Snackbar.make(
                            binding.root,
                            commentListResponse.errorMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })

        binding.tvPostUserName.setOnClickListener {
            Intent(this, DetailUserActivity::class.java).apply {
                putExtra(DetailUserActivity.EXTRA_USER, extraPost.user)
                startActivity(this)
            }
        }
    }

    companion object {
        const val EXTRA_POST = "extra_post"
    }
}
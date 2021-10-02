package com.zaydhisyam.memories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.databinding.ActivityPhotoListBinding
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.ui.adapter.PhotoListAdapter
import com.zaydhisyam.memories.ui.view_model.PhotoListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPhotoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraAlbum: Album = intent.getParcelableExtra(EXTRA_ALBUM)!!

        supportActionBar?.title = extraAlbum.title

        val rvAdapter = PhotoListAdapter()

        rvAdapter.onViewHolderClick = { selectedPhoto ->
            Toast.makeText(this, selectedPhoto.toString(), Toast.LENGTH_SHORT).show()
        }

        with(binding.rvPhotoList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = rvAdapter
        }

        val viewModel: PhotoListViewModel by viewModel()

        viewModel.getPhotoListPerAlbum(albumId = extraAlbum.id)
            .observe(this, { photoListResponse ->
                when (photoListResponse) {
                    is Resource.Loading -> binding.progressCircular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        rvAdapter.setAdapterListData(photoListResponse.data)
                    }
                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Snackbar.make(
                            binding.root,
                            photoListResponse.errorMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }

    companion object {
        const val EXTRA_ALBUM = "extra_album"
    }
}
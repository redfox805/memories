package com.zaydhisyam.memories.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zaydhisyam.memories.R
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.databinding.ActivityDetailUserBinding
import com.zaydhisyam.memories.domain.model.User
import com.zaydhisyam.memories.ui.adapter.AlbumListAdapter
import com.zaydhisyam.memories.ui.view_model.DetailUserViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraUser: User = intent.getParcelableExtra(EXTRA_USER)!!

        supportActionBar?.title = "User Info"

        with(binding) {
            tvUserName.text = extraUser.name
            tvUserEmail.text = extraUser.email

            var address = "Street : ${extraUser.address.street}\n"
            address += "Suite : ${extraUser.address.suite}\n"
            address += "City : ${extraUser.address.city}\n"
            address += "Zipcode : ${extraUser.address.zipcode}"
            tvUserAddress.text = address

            tvUserCompany.text = extraUser.company.name
        }

        val rvAdapter = AlbumListAdapter()

        rvAdapter.onViewHolderClick = { selectedAlbum ->
            Intent(this, PhotoListActivity::class.java).apply {
                putExtra(PhotoListActivity.EXTRA_ALBUM, selectedAlbum)
                startActivity(this)
            }
        }

        with(binding.rvAlbumList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = rvAdapter
        }

        val viewModel: DetailUserViewModel by viewModel()

        viewModel.getAlbumListPerUser(userId = extraUser.id)
            .observe(this, { albumListResponse ->
                when (albumListResponse) {
                    is Resource.Loading -> binding.progressCircular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        rvAdapter.setAdapterListData(albumListResponse.data)
                    }
                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Snackbar.make(
                            binding.root,
                            albumListResponse.errorMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
package com.zaydhisyam.memories.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.zaydhisyam.memories.R
import com.zaydhisyam.memories.databinding.ActivityPhotoViewBinding
import com.zaydhisyam.memories.domain.model.Photo

class PhotoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPhotoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraPhoto: Photo = intent.getParcelableExtra(EXTRA_PHOTO)!!

        supportActionBar?.title = extraPhoto.title

        val url = GlideUrl(
            extraPhoto.url,
            LazyHeaders.Builder()
                .addHeader("User-Agent", "random_user_agent")
                .build()
        )

        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_glide_placeholder)
            .into(binding.photoView)
    }

    companion object {
        const val EXTRA_PHOTO = "extra_photo"
    }
}
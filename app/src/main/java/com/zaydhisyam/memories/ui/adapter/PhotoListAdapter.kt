package com.zaydhisyam.memories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.zaydhisyam.memories.R
import com.zaydhisyam.memories.databinding.RvItemPhotoListBinding
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.model.Photo

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    private val photoList = arrayListOf<Photo>()
    lateinit var onViewHolderClick: ((Photo) -> Unit)

    fun setAdapterListData(photoList: List<Photo>) {
        this.photoList.clear()
        this.photoList.addAll(photoList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: RvItemPhotoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivPhoto.setOnClickListener {
                onViewHolderClick.invoke(photoList[absoluteAdapterPosition])
            }
        }

        fun bind(data: Photo) {
            val url = GlideUrl(
                data.thumbnailUrl,
                LazyHeaders.Builder()
                    .addHeader("User-Agent", "random_user_agent")
                    .build()
            )

            Glide.with(binding.root.context)
                .load(url)
                .placeholder(R.drawable.ic_glide_placeholder)
                .fitCenter()
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RvItemPhotoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(photoList[position])

    override fun getItemCount(): Int = photoList.size
}
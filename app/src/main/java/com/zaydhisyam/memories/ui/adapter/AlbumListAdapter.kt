package com.zaydhisyam.memories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.zaydhisyam.memories.R
import com.zaydhisyam.memories.databinding.RvItemAlbumListBinding
import com.zaydhisyam.memories.domain.model.Album

class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    private val albumList = arrayListOf<Album>()
    lateinit var onViewHolderClick: ((Album) -> Unit)

    fun setAdapterListData(albumList: List<Album>) {
        this.albumList.clear()
        this.albumList.addAll(albumList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: RvItemAlbumListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewContainer.setOnClickListener {
                onViewHolderClick.invoke(albumList[absoluteAdapterPosition])
            }
        }

        fun bind(data: Album) {
            with(binding) {
                val url = GlideUrl(
                    data.thumbnailUrl,
                    LazyHeaders.Builder()
                        .addHeader("User-Agent", "random_user_agent")
                        .build()
                )

                Glide.with(root.context)
                    .load(url)
                    .placeholder(R.drawable.ic_glide_placeholder)
                    .centerCrop()
                    .into(ivAlbumThumbnail)

                tvAlbumName.text = data.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RvItemAlbumListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(albumList[position])

    override fun getItemCount(): Int = albumList.size
}
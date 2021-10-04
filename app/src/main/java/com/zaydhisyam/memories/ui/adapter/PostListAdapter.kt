package com.zaydhisyam.memories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaydhisyam.memories.databinding.RvItemPostListBinding
import com.zaydhisyam.memories.domain.model.Post

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private val postList = arrayListOf<Post>()
    lateinit var onViewHolderClick: ((Post) -> Unit)

    fun setAdapterListData(postList: List<Post>) {
        this.postList.clear()
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: RvItemPostListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.containerConstraint.setOnClickListener {
                onViewHolderClick.invoke(postList[absoluteAdapterPosition])
            }
        }

        fun bind(data: Post) {
            with(binding) {
                tvUserName.text = data.user.name
                tvUserCompanyName.text = data.user.company.name
                tvPostTitle.text = data.title
                tvPostBody.text = data.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RvItemPostListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(postList[position])

    override fun getItemCount(): Int = postList.size
}
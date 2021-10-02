package com.zaydhisyam.memories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaydhisyam.memories.databinding.RvItemCommentListBinding
import com.zaydhisyam.memories.domain.model.Comment

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    private val commentList = arrayListOf<Comment>()

    fun setAdapterListData(commentList: List<Comment>) {
        this.commentList.clear()
        this.commentList.addAll(commentList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: RvItemCommentListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Comment) {
            with(binding) {
                tvCommentAuthorName.text = data.name.uppercase()
                tvCommentBody.text = data.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RvItemCommentListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(commentList[position])

    override fun getItemCount(): Int = commentList.size
}
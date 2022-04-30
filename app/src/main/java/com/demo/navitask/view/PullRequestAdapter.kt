package com.demo.navitask.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.navitask.data.model.PullResponse
import com.demo.navitask.databinding.ItemListBinding
import com.demo.navitask.util.getAbbreviatedFromDateTime
import com.demo.navitask.util.getProgressDrawable
import com.demo.navitask.util.loadImage

class PullRequestAdapter(private var pullRequest: ArrayList<PullResponse>) :
    RecyclerView.Adapter<PullRequestAdapter.PullListRequestViewHolder>() {

    fun updatePullRequestList(newPullRequest: List<PullResponse>) {
        pullRequest.clear()
        pullRequest.addAll(newPullRequest)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PullListRequestViewHolder {
        val itemBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullListRequestViewHolder(itemBinding)
    }

    override fun getItemCount() = pullRequest.size

    override fun onBindViewHolder(holder: PullListRequestViewHolder, position: Int) {
        holder.bind(pullRequest[holder.adapterPosition])
    }

    class PullListRequestViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val progressDrawable = getProgressDrawable(binding.root.context)
        fun bind(pullRequest: PullResponse) {
            binding.apply {
                txtTitle.text = pullRequest.title
                pullRequest.createdDate?.let {
                    txtCreatedDate.text =
                        getAbbreviatedFromDateTime(it.split("T")[0])?.let { createdDate ->
                            "Created Date : $createdDate"
                        }
                }
                pullRequest.closedDate?.let {
                    getAbbreviatedFromDateTime(it.split("T")[0])?.let { closedDate ->
                        txtClosedDate.text =
                            "Closed Date : $closedDate"
                    }
                }
                pullRequest.user?.name?.let {
                    txtUserName.text = "User Name : $it"
                }
                pullRequest.user?.avatarUrl?.let {
                    imgUser.loadImage(it, progressDrawable)
                }
            }
        }
    }
}



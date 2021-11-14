package com.wasilyk.app.testing2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wasilyk.app.testing2.databinding.ItemViewBinding

class ListAdapter(
    private val data: List<String>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewBinding.bind(itemView)
        private val textView = binding.itemTv

        fun bind(position: Int) {
            textView.text = data[position]
        }

        fun setOnItemClickListener(pos: Int) {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).root
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.setOnItemClickListener(position)
    }

    override fun getItemCount() = data.size

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }

}
package id.deritarigan.codexassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.deritarigan.codexassignment.R
import id.deritarigan.codexassignment.model.StoryData
import id.deritarigan.codexassignment.model.StoryResponse
import kotlinx.android.synthetic.main.item_list_story.view.*

class StoryAdapter(val callback: StoryCallback) :
    RecyclerView.Adapter<StoryAdapter.mViewHolder>() {
    var listData = mutableListOf<StoryResponse?>()

    interface StoryCallback {
        fun onClickStory(data:StoryResponse?)
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.textViewTitle
        var container = itemView.linearLayoutContainer
        var comment = itemView.textViewComment
        var score = itemView.textViewScore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {

        return mViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_story,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.title.text = listData[position]?.title
        holder.comment.text = listData[position]?.descendants.toString()
        holder.score.text = listData[position]?.score.toString()
        holder.container.setOnClickListener {
            callback.onClickStory(listData[position])
        }
    }
}
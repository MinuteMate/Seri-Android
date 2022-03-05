package com.hackaton.seriandroid.ui.game.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackaton.seriandroid.databinding.GameChatMineBinding
import com.hackaton.seriandroid.databinding.GameChatParterBinding
import com.hackaton.seriandroid.ui.game.model.GameMessage
import com.hackaton.seriandroid.utils.loadFromUrl

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<GameMessage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == BubbleViewType.LEFT) {
            return BubbleLeftViewHolder(
                GameChatParterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return BubbleRightViewHolder(
            GameChatMineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position].viewType) {
            BubbleViewType.LEFT -> {
                (holder as BubbleLeftViewHolder).bind(items[position])
            }
            BubbleViewType.RIGHT -> {
                (holder as BubbleRightViewHolder).bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<GameMessage>) {
        items = list
        notifyDataSetChanged()
    }
}

object BubbleViewType {
    const val LEFT = 1
    const val RIGHT = 2
}

class BubbleRightViewHolder(binding: GameChatMineBinding) : RecyclerView.ViewHolder(binding.root) {

    private val chatText = binding.tvMessage

    fun bind(item: GameMessage) {
        chatText.text = item.message
    }
}

class BubbleLeftViewHolder(binding: GameChatParterBinding) : RecyclerView.ViewHolder(binding.root) {

    private val chatText = binding.tvMessage
    private val chatProfile = binding.tvProfile

    fun bind(item: GameMessage) {
        chatText.text = item.message
        chatProfile.loadFromUrl(item.profileUrl)
    }
}
package com.example.finalprojectlockroomcy.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectlockroomcy.R
import com.example.finalprojectlockroomcy.data.room.HistoryItem

class HistoryRoomAdapter(private val historyList: List<HistoryItem>) : RecyclerView.Adapter<HistoryRoomAdapter.HistoryRoomViewHolder> () {

    class HistoryRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameText: TextView = itemView.findViewById(R.id.txt_receipt_history_username)
        val roomTypeText: TextView = itemView.findViewById(R.id.txt_history_receipt_room_type)
        val dateText: TextView = itemView.findViewById(R.id.txt_history_receipt_date)
        val sessionText: TextView = itemView.findViewById(R.id.txt_history_receipt_session)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRoomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history_room, parent, false)
        return HistoryRoomViewHolder(itemView)    }

    override fun onBindViewHolder(holder: HistoryRoomViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.usernameText.text = currentItem.username
        holder.roomTypeText.text = currentItem.roomType
        holder.dateText.text = currentItem.date
        holder.sessionText.text = currentItem.session
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}
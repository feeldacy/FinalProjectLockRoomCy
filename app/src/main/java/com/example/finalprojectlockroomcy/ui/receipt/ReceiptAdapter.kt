package com.example.finalprojectlockroomcy.ui.receipt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectlockroomcy.R
import com.example.finalprojectlockroomcy.data.response.ReceiptReservationItem
import org.w3c.dom.Text

class ReceiptAdapter (private val receiptList: List<ReceiptReservationItem>) : RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    class ReceiptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiptName: TextView = itemView.findViewById(R.id.txt_receipt_username)
        val receiptDate: TextView = itemView.findViewById(R.id.txt_receipt_date)
        val receiptRoomType: TextView = itemView.findViewById(R.id.txt_receipt_room_type)
        val receiptSession: TextView = itemView.findViewById(R.id.txt_receipt_session)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receipt, parent, false)
        return ReceiptViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val currentReceipt = receiptList[position]
        holder.receiptName.text = currentReceipt.username
        holder.receiptSession.text = currentReceipt.session
        holder.receiptDate.text = currentReceipt.date
        holder.receiptRoomType.text = currentReceipt.roomType
    }

    override fun getItemCount(): Int {
        return receiptList.size
    }
}
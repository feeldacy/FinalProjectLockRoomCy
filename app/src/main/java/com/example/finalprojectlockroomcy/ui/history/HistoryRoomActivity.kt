package com.example.finalprojectlockroomcy.ui.history

import android.os.Bundle
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectlockroomcy.R
import com.example.finalprojectlockroomcy.data.room.HistoryDao
import com.example.finalprojectlockroomcy.data.room.HistoryItem
import com.example.finalprojectlockroomcy.data.room.HistoryRoomDatabase
import com.example.finalprojectlockroomcy.databinding.ActivityHistoryRoomBinding
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryRoomBinding
    private lateinit var recyclerView: RecyclerView

    private lateinit var mHistoryDao: HistoryDao
    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = HistoryRoomDatabase.getDatabase(this)
        mHistoryDao = db!!.historyDao()!!

        recyclerView = findViewById(R.id.rv_my_receipts_history)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val receiptHistoryList = mHistoryDao.getAllHistory()
            recyclerView.adapter = HistoryRoomAdapter(receiptHistoryList)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

}
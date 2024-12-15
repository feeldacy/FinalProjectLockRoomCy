package com.example.finalprojectlockroomcy.ui.receipt

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.collection.mutableIntListOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectlockroomcy.R
import com.example.finalprojectlockroomcy.data.response.ReceiptReservationItem
import com.example.finalprojectlockroomcy.data.room.HistoryDao
import com.example.finalprojectlockroomcy.data.room.HistoryItem
import com.example.finalprojectlockroomcy.data.room.HistoryRoomDatabase
import com.example.finalprojectlockroomcy.databinding.FragmentReceiptBinding
import com.example.finalprojectlockroomcy.network.ApiClient
import com.example.finalprojectlockroomcy.ui.history.HistoryRoomActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ReceiptFragment : Fragment() {

    private var _binding: FragmentReceiptBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mHistoryDao: HistoryDao
    private lateinit var executorService: ExecutorService




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceiptBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        executorService = Executors.newSingleThreadExecutor()
        val db = HistoryRoomDatabase.getDatabase(requireContext())
        mHistoryDao = db!!.historyDao()!!

        val client = ApiClient.getInstance()
        val responseReceipt = client.getAllReceipt()


        responseReceipt.enqueue(object : Callback<List<ReceiptReservationItem>> {
            override fun onResponse(
                p0: Call<List<ReceiptReservationItem>>,
                p1: Response<List<ReceiptReservationItem>>
            ) {
                if (p1.isSuccessful){
                    p1.body()?.let { receipts ->
                        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        val upcomingReceipts = mutableListOf<ReceiptReservationItem>()

                        receipts.forEach{receipt ->
                            if (receipt.date < currentDate){
                                val historyItem = HistoryItem(
                                    username = receipt.username,
                                    roomType = receipt.roomType,
                                    date = receipt.date,
                                    session = receipt.session
                                )
                                GlobalScope.launch {
                                    mHistoryDao.insert(historyItem)
                                }
                            } else{
                                upcomingReceipts.add(receipt)
                            }
                        }
                        // pembuatan recyclerview
                        showUpcomingReceipts(upcomingReceipts)
                    }
                }
            }

            override fun onFailure(p0: Call<List<ReceiptReservationItem>>, p1: Throwable) {
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
            }


        })

        binding.btnHistory.setOnClickListener {
            val intentToHistory = Intent(activity, HistoryRoomActivity::class.java)
            startActivity(intentToHistory)
        }
    }

    private fun showUpcomingReceipts(upcomingReceipts: List<ReceiptReservationItem>) {
        binding.rvMyReceipts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ReceiptAdapter(upcomingReceipts)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
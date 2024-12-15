package com.example.finalprojectlockroomcy.ui.receipt

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectlockroomcy.MainActivity
import com.example.finalprojectlockroomcy.R
import com.example.finalprojectlockroomcy.data.request.ReceiptReservationRequest
import com.example.finalprojectlockroomcy.data.response.GeneralResponse
import com.example.finalprojectlockroomcy.data.response.ReceiptReservationItem
import com.example.finalprojectlockroomcy.databinding.ActivityReceiptReservationBinding
import com.example.finalprojectlockroomcy.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ReceiptReservationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityReceiptReservationBinding
    private val calendar = Calendar.getInstance()
    private var selectedDate: String = ""
    private var selectedRoomType: String = ""
    private var selectedSession: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomTypeItem = resources.getStringArray(R.array.room_type)
        val adapterSpinnerRoomType = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomTypeItem)
        binding.spinnerRoomType.adapter = adapterSpinnerRoomType


        val sessionItem = resources.getStringArray(R.array.session)
        val adapterSession = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sessionItem)
        binding.spinnerSession.adapter = adapterSession

        with(binding){
            spinnerRoomType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedRoomType = roomTypeItem[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

            spinnerSession.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedSession = sessionItem[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

            btnBack.setOnClickListener {
                finish()
            }

            btnBookRoom.setOnClickListener {
                val username: String = fieldName.text.toString()
                val date: String = selectedDate
                val roomType: String = selectedRoomType
                val session: String = selectedSession
                if (date.isNotEmpty() && roomType.isNotEmpty() && session.isNotEmpty() && username.isNotEmpty()) {
                    postData(username, roomType, session, date)
                } else {
                    Toast.makeText(
                        this@ReceiptReservationActivity,
                        "Please fill all the fields",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

    fun showCalendar(view: View) {
        val datePickerDialog = DatePickerDialog(
            this, this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val dateFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        selectedDate = dateFormat.format(calendar.time)

        binding.fieldDate.text = selectedDate
    }

    private fun postData(username: String,  roomType: String, session: String, date: String) {
        // Create an instance of the request model
        val reservationRequest = ReceiptReservationRequest(
            username = username,
            roomType = roomType,
            session = session,
            date = date
        )

        // Create an instance of the API service
        val apiService = ApiClient.getInstance()

        // Make the API call
        apiService.createReceipt(reservationRequest).enqueue(object : Callback<GeneralResponse> {
            override fun onResponse(call: Call<GeneralResponse>, response: Response<GeneralResponse>) {
                if (response.isSuccessful) {
                    val intentToMainAct = Intent(this@ReceiptReservationActivity, MainActivity::class.java)
                    startActivity(intentToMainAct)
                    finish()
                } else {
                    Toast.makeText(
                        this@ReceiptReservationActivity,
                        "Error: ${response.code()} - ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Toast.makeText(
                    this@ReceiptReservationActivity,
                    "Failure: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}
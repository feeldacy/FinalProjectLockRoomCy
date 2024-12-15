package com.example.finalprojectlockroomcy.ui.receipt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiptViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is receipt fragment"
    }
    val text: LiveData<String> = _text
}
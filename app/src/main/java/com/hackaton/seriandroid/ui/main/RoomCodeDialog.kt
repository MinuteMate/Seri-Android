package com.hackaton.seriandroid.ui.main

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackaton.seriandroid.R

class RoomCodeDialog(context: Context) {
    private val dlg = Dialog(context)

    private lateinit var etClassCode: EditText
    private lateinit var ivNext: ImageView

    val _classCode: MutableLiveData<String> = MutableLiveData()
    val classCode: LiveData<String> get() = _classCode

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.class_code_dialog)
        dlg.setCancelable(false)

        etClassCode = dlg.findViewById(R.id.etClassCode)
        ivNext = dlg.findViewById(R.id.ivNext)

        ivNext.setOnClickListener {
            dlg.dismiss()

            _classCode.value = etClassCode.text.toString()
        }

        dlg.show()
    }
}
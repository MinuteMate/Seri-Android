package com.hackaton.seriandroid.ui.main

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackaton.seriandroid.R

class CreateGameDialog(context: Context) {
    private val dlg = Dialog(context)

    private lateinit var etGameName: EditText
    private lateinit var ivNext: ImageView
    private lateinit var tvCancel: TextView

    val _gameName: MutableLiveData<String> = MutableLiveData()
    val gameName: LiveData<String> get() = _gameName

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.create_game_dialog)
        dlg.setCancelable(false)

        etGameName = dlg.findViewById(R.id.etGameName)
        ivNext = dlg.findViewById(R.id.ivNext)
        tvCancel = dlg.findViewById(R.id.tvCancel)

        ivNext.setOnClickListener {
            _gameName.value = etGameName.text.toString()

            dlg.dismiss()
        }

        tvCancel.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }
}
package com.hackaton.seriandroid.ui.main

import android.app.Dialog
import android.content.Context
import android.media.Image
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackaton.seriandroid.R
import org.w3c.dom.Text

class FixUserProfileDialog(context: Context) {
    private val dlg = Dialog(context)

    private lateinit var ivProfile: ImageView
    private lateinit var etNickName: EditText
    private lateinit var tvNowNickName: TextView
    private lateinit var tvFix: TextView
    private lateinit var tvCancel: TextView

    val _nickname: MutableLiveData<String> = MutableLiveData()
    val nickname: LiveData<String> get() = _nickname

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.fix_user_profile_dialog)
        dlg.setCancelable(false)

        ivProfile = dlg.findViewById(R.id.ivProfile)
        etNickName = dlg.findViewById(R.id.etNickName)
        tvNowNickName = dlg.findViewById(R.id.tvNowNickName)
        tvFix = dlg.findViewById(R.id.tvFix)
        tvCancel = dlg.findViewById(R.id.tvCancel)

        tvFix.setOnClickListener {
            _nickname.value = etNickName.text.toString()

            dlg.dismiss()
        }

        tvCancel.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }
}
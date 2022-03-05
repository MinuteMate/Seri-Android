package com.hackaton.seriandroid.ui.main

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.hackaton.seriandroid.R

class GuideDialog(context: Context) {
    private val dlg = Dialog(context)

    private lateinit var tvPageCount: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var lnNext: LinearLayout
    private lateinit var tvNextText: TextView

    private var page = 0

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.help_dialog)
        dlg.setCancelable(false)

        tvPageCount = dlg.findViewById(R.id.tvPageCount)
        tvTitle = dlg.findViewById(R.id.tvTitle)
        tvDescription = dlg.findViewById(R.id.tvDescription)
        lnNext = dlg.findViewById(R.id.lnNext)
        tvNextText = dlg.findViewById(R.id.tvNextText)

        movePage(++page)

        lnNext.setOnClickListener {
            movePage(++page)
        }

        dlg.show()
    }

    private fun movePage(page: Int) {
        when(page) {
            1 -> page1()
            2 -> page2()
            3 -> page3()
            4 -> page4()
            5 -> page5()
            6 -> dlg.dismiss()
        }
    }

    private fun page1() {
        tvPageCount.setText("게임 방법 [${page}/5]")
        tvTitle.text = "등록단계"
        tvDescription.text = "사회자가 키워드와 라이어를 정하고, 라이어를\n" +
                "제외한 모든 인원에게 키워드를 전달해요.\n" +
                "이때 다른 사람이 키워드를 받았는지는 알 수\n" +
                "없어야해요."
    }

    private fun page2() {
        tvPageCount.setText("게임 방법 [$page/5]")
        tvTitle.text = "묘사단계"
        tvDescription.text = "게임 참여자(시민, 라이어)가 키워드에 대해서\n" +
                "순서대로 묘사를 해요."
    }

    private fun page3() {
        tvPageCount.setText("게임 방법 [$page/5]")
        tvTitle.text = "질문단계"
        tvDescription.text = "게임 참여자가 순서대로 자신이 지목한 다른\n" +
                "참여자에게 질문을 할 수 있어요. 이때 질문\n" +
                "에 대한 대답은 꼭 사실만 말하지 않아도 돼요."
    }

    private fun page4() {
        tvPageCount.setText("게임 방법 [$page/5]")
        tvTitle.text = "지목단계"
        tvDescription.text = "게임 참여자가 라이어로 의심되는 사람을 지\n" +
                "목을 해요. 그 사람이 라이어가 아니면 라이\n" +
                "어의 승리고, 그 사람이 라이어면 구명단계로\n" +
                "넘어가요."
    }

    private fun page5() {
        tvPageCount.setText("게임 방법 [$page/5]")
        tvTitle.text = "구명단계"
        tvDescription.text = "라이어가 사회자가 시민들에게 준 키워드를\n" +
                "맞추면 라이어가 이겨요."
        tvNextText.text = "완료하기"
    }
}
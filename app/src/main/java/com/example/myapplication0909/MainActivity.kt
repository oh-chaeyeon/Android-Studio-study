package com.example.myapplication0909

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    private lateinit var radioRect: RadioButton
    private lateinit var radioCircle: RadioButton
    private lateinit var myView: MyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // XML에서 정의한 라디오 버튼과 커스텀 뷰를 연결합니다.
        radioRect = findViewById(R.id.radioRect)
        radioCircle = findViewById(R.id.radioCircle)
        myView = findViewById(R.id.view)

        // 라디오 버튼의 변경 이벤트를 처리합니다.
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioRect -> myView.setSelectedShape(MyView.Shape.RECTANGLE)
                R.id.radioCircle -> myView.setSelectedShape(MyView.Shape.CIRCLE)
            }
        }
    }
}

package com.piyush025.coursehelpbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_course_list.*

class CourseList : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        subjectA.setOnClickListener (this)
        subjectB.setOnClickListener (this)
        subjectC.setOnClickListener (this)
        subjectD.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when (v){
            subjectA, subjectB, subjectC, subjectD -> {
                val intent = Intent(this, BotActivity::class.java)
                startActivity(intent)
            }
            else -> {

            }

        }
    }
}
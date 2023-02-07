package com.example.kotlinquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.name)
        val score: TextView = findViewById(R.id.score)
        val finishButton: Button = findViewById(R.id.btn_finish)

        tvName.text = intent.getStringExtra(Constants.USERNAME)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        score.text = "Your Score: $correctAnswers out of $totalQuestions"

        finishButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}
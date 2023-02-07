package com.example.kotlinquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnStart: Button = findViewById(R.id.btn_start)
        val editName : EditText = findViewById(R.id.editNameText)

        btnStart.setOnClickListener {
            if(editName.text.isEmpty())
            {
                //if the text is empty display a message to the user
                Toast.makeText(this, "Please enter your name,", Toast.LENGTH_LONG).show()
            }
            else
            {
                //going from this activity over to the other
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                // putExtra(Key, value)
                intent.putExtra(Constants.USERNAME, editName.text.toString())
                startActivity(intent) //move to the new screen but does not close the current screen.
                finish() //finishes the current activity so that it is closed.
            }
        }
    }
}
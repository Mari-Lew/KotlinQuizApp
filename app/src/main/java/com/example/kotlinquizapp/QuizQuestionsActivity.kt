package com.example.kotlinquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
        private var curPosition: Int = 1
        private var mQuestionsList: ArrayList<Question>? = null
        private var mSelectedPos: Int = 0

    //instantiate variables
        private var progressBar : ProgressBar? = null
        private var textProgress : TextView? = null
        private var tvQuestion: TextView? = null
        private var tvImage : ImageView? = null

        private var optionOne: TextView? = null
        private var optionTwo: TextView? = null
        private var optionThree: TextView? = null
        private var optionFour: TextView? = null

        private var subButton : Button? = null

        private var userName : String? = null
        private var correctAnswers : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBar)
        textProgress = findViewById(R.id.progressTotal)
        tvQuestion = findViewById(R.id.questionView)
        tvImage = findViewById(R.id.questionImage)

        userName = intent.getStringExtra(Constants.USERNAME)

        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)

        subButton = findViewById(R.id.submitButton)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)

        subButton?.setOnClickListener(this)


        mQuestionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {
        //VARIABLES
        defaultOptionsView()

        val question: Question = mQuestionsList!![curPosition - 1]
        tvImage?.setImageResource(question.image)
        progressBar?.progress = curPosition
        textProgress?.text = "$curPosition/${progressBar?.max}"
        tvQuestion?.text = question.questions

        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

        if(curPosition == mQuestionsList!!.size)
        {
            subButton?.text = "FINISH"
        }
        else
        {
            subButton?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView()
    {

        val options = ArrayList<TextView>()
        optionOne?.let {
            options.add(0, it)
        }
        optionTwo?.let {
            options.add(1, it)
        }
        optionThree?.let {
            options.add(2, it)
        }
        optionFour?.let {
            options.add(3, it)
        }

        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int)
    {
        defaultOptionsView()

        mSelectedPos = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }

    override fun onClick(view: View?) {
       when(view?.id)
       {
           R.id.optionOne -> {
               optionOne?.let{
                   selectedOptionView(it, 1)
               }
           }

           R.id.optionTwo -> {
               optionTwo?.let{
                   selectedOptionView(it, 2)
               }
           }

           R.id.optionThree -> {
               optionThree?.let{
                   selectedOptionView(it, 3)
               }
           }

           R.id.optionFour -> {
               optionFour?.let{
                   selectedOptionView(it, 4)
               }
           }

           R.id.submitButton -> {
               if(mSelectedPos == 0)
               {
                   curPosition++

                   when{
                       curPosition <= mQuestionsList!!.size ->
                       {
                           setQuestion()
                       }
                       else ->
                       {
                           val intent = Intent(this, ResultActivity::class.java)
                           intent.putExtra(Constants.USERNAME, userName)
                           intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                           intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                           startActivity(intent)
                           finish()

                       }
                   }
               }
               else
               {
                   val question = mQuestionsList?.get(curPosition - 1)
                   if(question!!.correct != mSelectedPos)
                   {
                       answerView(mSelectedPos, R.drawable.incorrect_option_border)
                   }
                   else
                   {
                       correctAnswers++
                   }

                   answerView(question.correct, R.drawable.correct_option_border_bg)

                   if(curPosition == mQuestionsList!!.size)
                   {
                       subButton?.text = "FINISH"
                   }
                   else{
                       subButton?.text = "NEXT QUESTION"
                   }

                   mSelectedPos = 0
               }
           }
       }
    }

    private fun answerView(answer:Int, drawableView: Int)
    {
        when(answer)
        {
            1 ->
            {
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 ->
            {
                optionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 ->
            {
                optionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 ->
            {
                optionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }


}
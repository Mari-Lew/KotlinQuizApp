package com.example.kotlinquizapp

object Constants {

    const val USERNAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"


    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val qOne = Question(
            id = 1,
            questions = "What breed of dog is this?",
            image = R.drawable.rottweiler,
            optionOne = "Doberman",
            optionTwo = "Akita",
            optionThree = "Rottweiler",
            optionFour = "Belgian Malinois",
            3
        )
        val qTwo = Question(
            id = 2,
            questions = "What breed of dog is this?",
            image = R.drawable.doberman,
            optionOne = "Doberman",
            optionTwo = "Akita",
            optionThree = "Rottweiler",
            optionFour = "Belgian Malinois",
            1
        )
        val qThree = Question(
            id = 3,
            questions = "What breed of dog is this?",
            image = R.drawable.malinois,
            optionOne = "Doberman",
            optionTwo = "Akita",
            optionThree = "Rottweiler",
            optionFour = "Belgian Malinois",
            4
        )
        val qFour = Question(
            id = 4,
            questions = "What breed of dog is this?",
            image = R.drawable.akita,
            optionOne = "Doberman",
            optionTwo = "Akita",
            optionThree = "Rottweiler",
            optionFour = "Belgian Malinois",
            2
        )

        questionsList.add(qOne)
        questionsList.add(qTwo)
        questionsList.add(qThree)
        questionsList.add(qFour)

        return questionsList
    }
}
package eu.man.quiz_application

// we are creating different types of questions in this file..

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTION : String = "total_questions"
    const val CORRECT_ANSWER : String = "correct_answer"


    fun getQuestions() : ArrayList<Question>{
        val questionList = ArrayList<Question>()  // creating a arraylist of type Question..

        val ques1 = Question(
            1, "What country does this flag belong to??",
            R.drawable.india_flag,
            "Argentina",
            "India",
            "Spain",
            "Fiji",
            2
        )
        questionList.add(ques1) // now we are adding the ques1 to our QuestionList..


        val ques2 = Question(
            1, "What country does this flag belong to??",
            R.drawable.brazil_flag,
            "Argentina",
            "India",
            "Brazil",
            "Fiji",
            3
        )
        questionList.add(ques2)

        val ques3 = Question(
            1, "What country does this flag belong to??",
            R.drawable.japan_flag,
            "Japan",
            "India",
            "Indonesia",
            "Fiji",
            1
        )
        questionList.add(ques3)


        val ques4 = Question(
            1, "What country does this flag belong to??",
            R.drawable.bangladesh_flag,
            "Argentina",
            "Pakistan",
            "Brazil",
            "Bangladesh",
            4
        )
        questionList.add(ques4)


        return questionList
    }
}
package eu.man.quiz_application

// this is data class..

// this is how a question will look like..or what a question will contain..
data class Question(
    val id: Int,  // a good practice to have id for each question..
    val question : String,
    val Image: Int,  // image is of type int..
    val optionOne : String,
    val optionTwo : String,
    val optionThree : String,
    val optionFour : String,
    val correctAnswer : Int  // we need to know which answer is correct.. it is basically holding the index of correct answer..
    )

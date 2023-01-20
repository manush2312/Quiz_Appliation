package eu.man.quiz_application

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import org.w3c.dom.Text


// this is second screen of our activity..
// by View.OnClickListener every single thing in this method can be clicked and what should be executed ehrn it is clicked is mentioned in "onClick" method
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentposition = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0

    private var progressBar : ProgressBar? = null  // this is the progress bar
    private var tvProgress : TextView? = null  // this is the text view in the side of progress bar eg 1/4..
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_pogress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.option_one)
        tvOptionTwo = findViewById(R.id.option_two)
        tvOptionThree = findViewById(R.id.option_three)
        tvOptionFour = findViewById(R.id.option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        // to make this option clickable we need to pass this into onclicklistener..
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)


        // so once we go to QuizQuestionsActivity i want to load the questions..
        mQuestionList = Constants.getQuestions() //this will return bunch of questions

        setQuestion()


    }

    private fun setQuestion() {
        defaultOptionsView()
        var currentposition = 1
        //we are using !! mark beacuse it is nullable
        val question: Question = mQuestionList!![mCurrentposition - 1]  // here we are setting the question..
        ivImage?.setImageResource(question.Image)
        progressBar?.progress = mCurrentposition
        tvProgress?.text = "$mCurrentposition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        //if we are at the last question i want to set the text of the button as finish..
        if(mCurrentposition == mQuestionList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }


    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()  // this arraylist will contain the options, moreover created the arraylist of type textview because options are of textview

        // now we are adding the options in the aaraylist
        tvOptionOne?.let{
            options.add(0, it)  // at index 0 i am adding this and "it" signifies tvOptionOne
        }
        tvOptionTwo?.let{
            options.add(1, it)
        }
        tvOptionThree?.let{
            options.add(2, it)
        }
        tvOptionFour?.let{
            options.add(3, it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))  // all of the options will have this colour
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }

    }

    // i want to do that if i select any of the view that should have a purple border and rest of the views should be grey..
    private fun selectedOptionView(tv : TextView, selectedOptionNum : Int){
        //first we will call defaultOptionsView() so it will set all options in default mode..
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        //setting the colour of selected option..
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)  // making the selected option as Bold..
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.option_one -> {
                tvOptionOne?.let{
                    selectedOptionView(it, 1)
                }
            }

            R.id.option_two -> {
                tvOptionTwo?.let{
                    selectedOptionView(it, 2)
                }
            }

            R.id.option_three -> {
                tvOptionThree?.let{
                    selectedOptionView(it, 3)
                }
            }

            R.id.option_four -> {
                tvOptionFour?.let{
                    selectedOptionView(it, 4)
                }
            }

            // for submit button..
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0){
                    mCurrentposition++

                    when{
                        mCurrentposition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(this, "You made to the end", Toast.LENGTH_SHORT).show()

                            // moving to the next or result activity..
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentposition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentposition == mQuestionList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }

                    // we all always need to go back to the currentSelectedOption = 0 else we will always be at that position..
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    // this is to indicate that which answer was right and wrong
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}
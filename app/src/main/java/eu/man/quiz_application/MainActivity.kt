package eu.man.quiz_application

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

        val btn_start : Button = findViewById(R.id.Start_btn)
        val et_name : EditText = findViewById(R.id.et_name)

        btn_start.setOnClickListener{
            // checking that name field is bot empty..so we need to get hte data from the AppCompatEditText
            if(et_name.text.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }else{
                // if it is not empty then we need to move on a different screen..


                // we are setting up intent to switch from one activity to another..
                val intent = Intent(this, QuizQuestionsActivity::class.java)  //this means switching from the "this" activity to "QuizQuestionsActivity" activity and
                // this is in java class so we need to add class.java

                intent.putExtra(Constants.USER_NAME, et_name.text.toString())

                startActivity(intent)  // we also need to start this intent, so this is the way how we can start this..

                // but this will not stop or close the current activity, to finish the current activity use:
                finish()

            }
        }
    }
}
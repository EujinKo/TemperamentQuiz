/**
 * @Author Eujin Ko
 *
 * TemperamentQuiz
 *
 * How to play(also included in README.txt)
 *
 * 1. press the button "BEGIN QUIZ"
 * 2. Answer the question choosing between "Strongly Agree",
 * 	 "Partially Agree", "Neutral", "Partially Disagree", "Strongly Disagree"
 * 3. Press the button "NEXT QUESTION" to submit your answer
 * 4. Continue doing 2,3 until you meet the summary page
 * 5. Press backward button, right top on your screen, to restart
 *    Or close the app window to finish
 */

package com.example.temperamentquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void StartQuiz(View view){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

}

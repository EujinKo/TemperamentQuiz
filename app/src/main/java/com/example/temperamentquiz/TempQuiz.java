package com.example.temperamentquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class TempQuiz extends AppCompatActivity {

    //id of each question
    private int ID_CHOLERIC = R.raw.choleric;
    private int ID_MELANCHOLIC = R.raw.melancholic;
    private int ID_PHLEGMATIC = R.raw.phlegmatic;
    private int ID_SANGUINE = R.raw.sanguine;

    //array of strings which stores 4 question for each trait
    private ArrayList<String> list_choleric = new ArrayList<String>(4);
    private ArrayList<String> list_melancholic = new ArrayList<String>(4);
    private ArrayList<String> list_phlegmatic = new ArrayList<String>(4);
    private ArrayList<String> list_sanguine = new ArrayList<String>(4);


    //
    private int percent_choleric;
    private int percent_melancholic;
    private int percent_phlegmatic;
    private int percent_sanguine;

    //
    private int radioWeight = -1;
    private String list_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_quiz);

        saveFourTempQuizToArray();
        switchQuestion();
    }

    //Go to summary screen when called & save the percent values into SharedPreferences
    public void goToSummary(View view){
        SharedPreferences sharedPrefs = this.getSharedPreferences("temp_percent",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.putInt("choleric",percent_choleric);
        editor.putInt("melancholic",percent_melancholic);
        editor.putInt("phlegmatic",percent_phlegmatic);
        editor.putInt("sanguine",percent_sanguine);
        editor.commit();
//
//        System.out.println("percent_choleric: "+percent_choleric);
//        System.out.println("percent_melancholic: "+percent_melancholic);
//        System.out.println("percent_phlegmatic: "+percent_phlegmatic);
//        System.out.println("percent_sanguine: "+percent_sanguine);

        Intent intent = new Intent(this,TempSummary.class);
        startActivity(intent);
    }

    //Checks which button is clicked and add up weight
    public void checkButtonClicked(View view){
        if(list_id.equals("choleric") && radioWeight>-1){
            percent_choleric+=radioWeight;
            radioWeight = -1;
            switchQuestion();
        }else if(list_id.equals("melancholic") && radioWeight>-1){
            percent_melancholic+=radioWeight;
            radioWeight = -1;
            switchQuestion();
        }else if(list_id.equals("phlegmatic") && radioWeight>-1){
            percent_phlegmatic+=radioWeight;
            radioWeight = -1;
            switchQuestion();
        }else if(list_id.equals("sanguine") && radioWeight>-1){
            percent_sanguine+=radioWeight;
            radioWeight = -1;
            switchQuestion();
        }
//        System.out.println("percent_choleric: "+percent_choleric);
//        System.out.println("percent_melancholic: "+percent_melancholic);
//        System.out.println("percent_phlegmatic: "+percent_phlegmatic);
//        System.out.println("percent_sanguine: "+percent_sanguine);
    }

    //This function switches the question of the TextVIew
    public void switchQuestion(){
        String temp;

        setContentView(R.layout.activity_temp_quiz);
        TextView textView = (TextView) findViewById(R.id.temp_question);

        if(!list_choleric.isEmpty()){
            list_id = "choleric";
            temp = list_choleric.remove(0);
            textView.setText(temp);
        }else if(!list_melancholic.isEmpty()){
            list_id = "melancholic";
            temp = list_melancholic.remove(0);
            textView.setText(temp);
        }else if(!list_phlegmatic.isEmpty()){
            list_id = "phlegmatic";
            temp = list_phlegmatic.remove(0);
            textView.setText(temp);
        }else if(!list_sanguine.isEmpty()){
            list_id = "sanguine";
            temp = list_sanguine.remove(0);
            textView.setText(temp);
        }else{
            //TODO: GO to Result Page
            list_id = "end";
            goToSummary(findViewById(R.id.question_button));
        }
    }


    //This function checks if the radio button was clicked and set weight
    public void checkRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        if(checked){
            int id = view.getId();
            if(id ==  R.id.radio_s_agree){
                radioWeight = 4;
            }else if(id == R.id.radio_p_agree){
                radioWeight = 3;
            }else if(id == R.id.radio_neutral){
                radioWeight = 2;
            }else if(id == R.id.radio_p_disagree){
                radioWeight = 1;
            }else if(id == R.id.radio_s_disagree){
                radioWeight = 0;
            }
            //System.out.println(radioWeight);
        }

    }

    //Save four random question to the matching temperament list
    private void saveFourTempQuizToArray(){
        getFourRandomQuiz(ID_CHOLERIC,list_choleric);
        getFourRandomQuiz(ID_MELANCHOLIC,list_melancholic);
        getFourRandomQuiz(ID_PHLEGMATIC,list_phlegmatic);
        getFourRandomQuiz(ID_SANGUINE,list_sanguine);
    }


    //Add four question to the temp_list from raw resources(id=text_id)
    private void getFourRandomQuiz(int text_id, ArrayList<String> temp_list){
        try{
            InputStream inputStream = getResources().openRawResource(text_id);

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine().trim();

            //Add quizlets into list
            ArrayList<String> list = new ArrayList<>();
            while(line!=null){
                list.add(line.trim());
                //System.out.println(line);
                line = reader.readLine();
            }
            //System.out.println("SHUFFLE");
            //shuffle the list
            Collections.shuffle(list);
            for(int i=0;i<4;i++){
                temp_list.add(list.get(i));
                //System.out.println(list.get(i));
            }
        }catch(Resources.NotFoundException e){
            System.out.println("Raw file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

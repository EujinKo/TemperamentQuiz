package com.example.temperamentquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class TempQuiz extends AppCompatActivity {

    //id of each quizlet
    private int ID_CHOLERIC = R.raw.choleric;
    private int ID_MELANCHOLIC = R.raw.melancholic;
    private int ID_PHLEGMATIC = R.raw.phlegmatic;
    private int ID_SANGUINE = R.raw.sanguine;

    //array of strings which stores 4 quizlet for each trait
    private static ArrayList<String> list_choleric = new ArrayList<String>(4);
    private static ArrayList<String> list_melancholic = new ArrayList<String>(4);
    private static ArrayList<String> list_phlegmatic = new ArrayList<String>(4);
    private static ArrayList<String> list_sanguine = new ArrayList<String>(4);


    //
    private static int percent_choleric;
    private static int percent_melancholic;
    private static int percent_phlegmatic;
    private static int percent_sanguine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_quiz);

        saveFourTempQuizToArray();
    }

    public void checkRadioButtons(View view){

    }



    //Save four random quiz to the matching temperament list
    private void saveFourTempQuizToArray(){
        getFourRandomQuiz(ID_CHOLERIC,list_choleric);
        getFourRandomQuiz(ID_MELANCHOLIC,list_melancholic);
        getFourRandomQuiz(ID_PHLEGMATIC,list_phlegmatic);
        getFourRandomQuiz(ID_SANGUINE,list_sanguine);
    }


    //Add four quiz to the temp_list from raw resources(id=text_id)
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

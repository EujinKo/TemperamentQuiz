package com.example.temperamentquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TempSummary extends AppCompatActivity {
    private int percent_choleric;
    private int percent_melancholic;
    private int percent_phlegmatic;
    private int percent_sanguine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_summary);

        retrieveBundle();
        String userTemp = largestTemperament();
        if(userTemp!=null){
            setUserTempText(userTemp);
            setUserTempDescription(userTemp);
            String scores = getScoresString();
            setScore(scores);
        }
    }
    private void setScore(String scores){
        TextView textView = findViewById(R.id.temp_score);
        textView.setText(scores);
    }

    //Generate string which shows how much score you got for each temperament
    private String getScoresString(){
        String score="";
        score += "Choleric: "+percent_choleric+"\n";
        score += "Melancholic: "+percent_melancholic+"\n";
        score += "Phlegmatic: "+percent_phlegmatic+"\n";
        score += "Sanguine: "+percent_sanguine;
        return score;
    }

    //This function sets proper temperament description to the TextView
    private void setUserTempDescription(String userTemp){
        String description="";
        if(userTemp.equals("choleric")){
            description = getResources().getString(R.string.temp_choleric);
        }else if(userTemp.equals("melancholic")){
            description = getResources().getString(R.string.temp_melancholic);
        }else if(userTemp.equals("phlegmatic")){
            description = getResources().getString(R.string.temp_phlegmatic);
        }else if(userTemp.equals("sanguine")){
            description = getResources().getString(R.string.temp_sanguine);
        }
        TextView textView = findViewById(R.id.temp_description);
        textView.setText(description);
    }

    //This function sets userTemp into TextView
    private void setUserTempText(String userTemp){
        TextView textView = findViewById(R.id.your_temp);
        textView.setText(userTemp);
    }

    // This function retrieves percents of each temperament, loading from bundle
    private void retrieveBundle(){
        Bundle bundle = this.getIntent().getExtras();
        percent_choleric = bundle.getInt("choleric",0);
        percent_melancholic = bundle.getInt("melancholic",0);
        percent_phlegmatic = bundle.getInt("phlegmatic",0);
        percent_sanguine = bundle.getInt("sanguine",0);
    }

    // Returns the largest temperament feature
    private String largestTemperament(){
        String temp;

        List<Integer> integerList = new ArrayList<Integer>(Arrays.asList(percent_choleric,percent_melancholic,percent_phlegmatic,percent_sanguine));
        Collections.sort(integerList);

        int largestNumber=integerList.get(integerList.size()-1);
        if(largestNumber==percent_choleric){
            return "choleric";
        } else if(largestNumber==percent_melancholic){
            return "melancholic";
        } else if(largestNumber==percent_phlegmatic){
            return "phlegmatic";
        } else if(largestNumber==percent_sanguine){
            return "sanguine";
        }
        return null;
    }
}

package com.example.whichnumberisbigger;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables for the widgets we need to access programmatically
    private Button buttonLeft;
    private Button buttonRight;
    private TextView textViewScore;

    //variables for keeping track of stuff
    private int score=0;
    private int leftNum;
    private int rightNum;

    public static final int MAX_NUM=1000;
    public static final int MIN_NUM=1;
    ConstraintLayout bgelement = findViewById(R.id.layout);
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        randomizeNumbers();
        updateDisplay();
    }

    private void updateDisplay() {
        String scoreString = getResources().getString(R.string.main_score);
        textViewScore.setText(scoreString + score);
        buttonLeft.setText(String.valueOf(leftNum));
        buttonRight.setText(String.valueOf(rightNum));
    }

    private void randomizeNumbers() {
        //generate random number for left
        leftNum = genNumber();
        //generate random number for the right, but make sure it doesn't match the left.
        rightNum = genNumber();
        while(rightNum==leftNum){
            rightNum = genNumber();
        }
    }

    private int genNumber(){
        int range = MAX_NUM-MIN_NUM+1;
        return (int)(Math.random()*(range))+MIN_NUM;
    }

    public void checkAnswer(boolean leftPressed){
        if((leftNum>rightNum && leftPressed)||(rightNum>leftNum && !leftPressed)){
            score++;
            message="Correct!";
            bgelement.setBackgroundColor(Color.argb(255,0,255,255));
        }else{
            score--;
            message="Incorrect";
            bgelement.setBackgroundColor(Color.argb(255,255,0,0));
        }
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        randomizeNumbers();
        updateDisplay();
    }

    private void wireWidgets() {
        buttonLeft = findViewById(R.id.button_main_left);
        buttonRight = findViewById(R.id.button_main_right);
        textViewScore = findViewById(R.id.textview_main_score);
    }

    public void onClickLeft(View view) {
        checkAnswer(true);
    }

    public void onClickRight(View view) {
        checkAnswer(false);
    }
}

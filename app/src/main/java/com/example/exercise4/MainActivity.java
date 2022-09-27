package com.example.exercise4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random ran = new Random();
    int num1 = ran.nextInt(100);
    int num2 = ran.nextInt(100);
    int point =0 ;
    int answer =0;
    int ans = 0;
    CountDownTimer countDownTimer;
    TextView textViewTime;
    int totalQuestions = 0;
    private TextView mScore;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button mPlayAgain;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        answer = num1+num2;
        ans = ran.nextInt(answer);
        mScore = (TextView) findViewById(R.id.textView1) ;
        mQuestionTextView.setText(num1+"+"+num2+"="+ans);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans!= (num1+num2)) {
                    Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    checkAnswer(false);
                    point += 0;
                    mScore.setText("Score:" + point);
                    countDownTimer.start();
                }else{
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    checkAnswer(true);
                    point += 1;
                    mScore.setText("Score:" + point);
                    countDownTimer.start();
                }


            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans!= (num1+num2)) {
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    checkAnswer(true);
                    point += 1;
                    mScore.setText("Score:" + point);
                    countDownTimer.start();
                }else{
                    Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    checkAnswer(false);
                    point += 0;
                    mScore.setText("Score:" + point);
                    countDownTimer.start();
                }
            }
        });
        start();


    }
    public void start () {
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);
                mPlayAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playAgain();
                    }
                });
            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time Up!");
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
                mPlayAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playAgain();
                    }
                });
            }
        }.start();
    }
    public void playAgain (){
        point =0;
        countDownTimer.start();
        mScore.setText("Score:"+point);

    }

    private void updateQuestion() {
        num1 = ran.nextInt(100);
        num2 = ran.nextInt(100);
        answer = num1+num2;
        int ans = ran.nextInt(answer);
        mQuestionTextView.setText(num1+"+"+num2+"="+ans);
        countDownTimer.start();


    }

        private void checkAnswer(boolean userPressedTrue){

        int messageResId= 0;
        if (userPressedTrue==true){
            messageResId = R.string.correct_toast;
            updateQuestion();

        }else {
            messageResId = R.string.incorrect_toast;
            updateQuestion();
            }
        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();

    }


}

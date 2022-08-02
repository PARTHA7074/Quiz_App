package com.panjacreation.quiz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.panjacreation.quiz.Fragment.MCQFragment;
import com.panjacreation.quiz.JavaClass.QuestionModel;
import com.panjacreation.quiz.R;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    LinearProgressIndicator progressIndicator;
    MaterialButton quitBtn,nextBtn,startQuiz;
    TextView questionCounter,instructionTxtView;
    FragmentContainerView fragmentContainerView;

    int counter = 0;
    public static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setUpUi();

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_in,R.anim.fade_out);
                ft.replace(R.id.fragmentContainerView,new MCQFragment(counter)).commit();

                progressIndicator.setProgress(1);
                fragmentContainerView.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Next");
                quitBtn.setVisibility(View.VISIBLE);
                startQuiz.setVisibility(View.GONE);
                questionCounter.setText("Question 1/5");
                instructionTxtView.setVisibility(View.GONE);
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                questionCounter.setText("Home");
                progressIndicator.setProgress(0);
                fragmentContainerView.setVisibility(View.GONE);
                nextBtn.setVisibility(View.GONE);
                quitBtn.setVisibility(View.GONE);
                startQuiz.setVisibility(View.VISIBLE);
                instructionTxtView.setVisibility(View.VISIBLE);
                instructionTxtView.setText(R.string.instruction);
                score=0;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter==3){
                    nextBtn.setText("Submit");
                }
                if (counter<4){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_in,R.anim.fade_out);
                    ft.replace(R.id.fragmentContainerView,new MCQFragment(++counter)).commit();

                    int questionNumber = counter+1;
                    progressIndicator.setProgress(questionNumber);
                    questionCounter.setText("Question "+questionNumber+"/5");
                }
                else {
                    Toast.makeText(MainActivity.this, "Your Score: "+score, Toast.LENGTH_SHORT).show();
                    counter = 0;
                    questionCounter.setText("Home");
                    progressIndicator.setProgress(0);
                    fragmentContainerView.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.GONE);
                    quitBtn.setVisibility(View.GONE);
                    startQuiz.setVisibility(View.VISIBLE);
                    instructionTxtView.setVisibility(View.VISIBLE);
                    float percentage = (float) (score*100)/5;
                    instructionTxtView.setText("Your last score: "+score+"\nAccuracy: "+percentage+"%");
                    score=0;
                }
            }
        });

    }

    private void setUpUi(){
        progressIndicator = findViewById(R.id.progressIndicator);
        quitBtn = findViewById(R.id.quitBtn);
        nextBtn = findViewById(R.id.nextBtn);
        startQuiz = findViewById(R.id.startQuiz);
        questionCounter = findViewById(R.id.questionCounter);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);
        instructionTxtView = findViewById(R.id.instruction);
    }
}
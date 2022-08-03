package com.panjacreation.quiz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panjacreation.quiz.Fragment.MCQFragment;
import com.panjacreation.quiz.JavaClass.QuestionModel;
import com.panjacreation.quiz.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    LinearProgressIndicator progressIndicator;
    MaterialButton quitBtn,nextBtn,startQuiz;
    TextView questionCounter,instructionTxtView;
    FragmentContainerView fragmentContainerView;
    ArrayList<QuestionModel> questionModelsArray;

    int counter = 0;
    public static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setUpUi();
        saveData();

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionModelsArray = loadData();


                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_in,R.anim.fade_out);
                ft.replace(R.id.fragmentContainerView,new MCQFragment(counter,questionModelsArray)).commit();

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
                    ft.replace(R.id.fragmentContainerView,new MCQFragment(++counter,questionModelsArray)).commit();

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

    private void saveData(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        ArrayList<QuestionModel> questionModelsArray = new ArrayList<>();
        addDataToArray(questionModelsArray);
        String json = gson.toJson(questionModelsArray);
        editor.putString("questionModels", json).apply();
    }

    private ArrayList<QuestionModel> loadData(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("questionModels", null);
        Type type = new TypeToken<ArrayList<QuestionModel>>() {}.getType();
        ArrayList<QuestionModel> list = gson.fromJson(json, type);
        Collections.shuffle(list);
        return list;
    }

    private void addDataToArray(ArrayList<QuestionModel> questionModelsArray){
        questionModelsArray.add(new QuestionModel("Which one is not a nickname of a version of Android?",
                "A) cupcake","B) gingerbread","C) honeycomb","D) muffin",4));
        questionModelsArray.add(new QuestionModel("Which among these are NOT a part of Android’s native libraries?",
                "A) webkit","B) dalvik","C) opengl","D) sqlite",2));
        questionModelsArray.add(new QuestionModel("What operating system is used as the base of the Android stack?",
                "A) linux","B) windows","C) java","D) xml",1));
        questionModelsArray.add(new QuestionModel("When developing for the Android OS, Java byte code is compiled into what?",
                "A) java source code","B) dalvik application code","C) dalvik byte code","D) c source code",3));
        questionModelsArray.add(new QuestionModel("Android is licensed under which open source licensing license?",
                "A) gnu’s gpl","B) apache/mit","C) oss","D) sourceforge",2));
        questionModelsArray.add(new QuestionModel("APK stands for -",
                "A) Android Phone Kit","B) Android Page Kit","C) Android Package Kit","D) None of the above",3));
        questionModelsArray.add(new QuestionModel("Which of the following is the first callback method that is invoked by the system during an activity life-cycle?",
                "A) onClick() method","B) onCreate() method","C) onStart() method","D) onRestart() method",2));
        questionModelsArray.add(new QuestionModel("We require an AVD to create an emulator. What does AVD stand for?",
                "A) Android Virtual device","B) Android Virtual display","C) Active Virtual display","D) Active Virtual device",1));
        questionModelsArray.add(new QuestionModel("Does android support other languages than java?",
                "A) Yes","B) No","C) May be","D) Can't say",1));
        questionModelsArray.add(new QuestionModel("Which of the following is contained in the src folder?",
                "A) XML","B) Java source code","C) Manifest","D) None of the above",2));
    }
}
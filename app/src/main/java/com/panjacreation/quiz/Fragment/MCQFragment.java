package com.panjacreation.quiz.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textview.MaterialTextView;
import com.panjacreation.quiz.Activity.MainActivity;
import com.panjacreation.quiz.JavaClass.QuestionModel;
import com.panjacreation.quiz.R;

import java.util.ArrayList;


public class MCQFragment extends Fragment {
    int counter = 0;
    ArrayList<QuestionModel> questionModelsArray = new ArrayList<>();
    MaterialTextView option1,option2,option3,option4;
    TextView question;
    int answer;

    public MCQFragment() {
        // Required empty public constructor
    }
    public MCQFragment(int Counter){
        this.counter = Counter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_m_c_q, container, false);
        addDataToArray();

        question = view.findViewById(R.id.question);
        option1 = view.findViewById(R.id.answerA);
        option2 = view.findViewById(R.id.answerB);
        option3 = view.findViewById(R.id.answerC);
        option4 = view.findViewById(R.id.answerD);

        QuestionModel questionModel = questionModelsArray.get(counter);
        question.setText(questionModel.getQuestion());
        option1.setText(questionModel.getOption1());
        option2.setText(questionModel.getOption2());
        option3.setText(questionModel.getOption3());
        option4.setText(questionModel.getOption4());
        answer = questionModel.getAnswer();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer==1){
                    MainActivity.score++;
                    option1.setTextColor(Color.parseColor("#51F67F"));
                    option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24_green, 0);
                } else {
                    option1.setTextColor(Color.parseColor("#BF0414"));
                    option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_highlight_off_24_red, 0);
                }
                setTextViewUnClickable();
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer==2){
                    MainActivity.score++;
                    option2.setTextColor(Color.parseColor("#51F67F"));
                    option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24_green, 0);
                } else {
                    option2.setTextColor(Color.parseColor("#BF0414"));
                    option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_highlight_off_24_red, 0);
                }
                setTextViewUnClickable();
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer==3){
                    MainActivity.score++;
                    option3.setTextColor(Color.parseColor("#51F67F"));
                    option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24_green, 0);
                } else {
                    option3.setTextColor(Color.parseColor("#BF0414"));
                    option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_highlight_off_24_red, 0);
                }
                setTextViewUnClickable();
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer==4){
                    MainActivity.score++;
                    option4.setTextColor(Color.parseColor("#51F67F"));
                    option4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24_green, 0);
                } else {
                    option4.setTextColor(Color.parseColor("#BF0414"));
                    option4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_highlight_off_24_red, 0);
                }
                setTextViewUnClickable();
            }
        });

        return view;
    }

    private void addDataToArray(){
        questionModelsArray.add(new QuestionModel("Which one is not a nickname of a version of Andriod?",
                "A) cupcake","B) gingerbread","C) honeycomb","D) muffin",4));
        questionModelsArray.add(new QuestionModel("Which among these are NOT a part of Android’s native libraries?",
                "A) webkit","B) dalvik","C) opengl","D) sqlite",2));
        questionModelsArray.add(new QuestionModel("What operating system is used as the base of the Android stack?",
                "A) linux","B) windows","C) java","D) xml",1));
        questionModelsArray.add(new QuestionModel("When developing for the Android OS, Java byte code is compiled into what?",
                "A) java source code","B) dalvik application code","C) dalvik byte code","D) c source code",3));
        questionModelsArray.add(new QuestionModel("Android is licensed under which open source licensing license?",
                "A) gnu’s gpl","B) apache/mit","C) oss","D) sourceforge",2));
    }

    private void setTextViewUnClickable(){
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
    }
}
package com.panjacreation.quiz.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panjacreation.quiz.Activity.MainActivity;
import com.panjacreation.quiz.JavaClass.QuestionModel;
import com.panjacreation.quiz.R;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MCQFragment extends Fragment {
    int counter = 0;
    ArrayList<QuestionModel> questionModelsArray;
    MaterialTextView option1,option2,option3,option4;
    TextView question;
    int answer;

    public MCQFragment() {
        // Required empty public constructor
    }
    public MCQFragment(int Counter,ArrayList<QuestionModel> questionModelsArray){
        this.counter = Counter;
        this.questionModelsArray = questionModelsArray;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_m_c_q, container, false);


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


    private void setTextViewUnClickable(){
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
    }

}
package com.example.financialliteracy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.financialliteracy.Databases.QuestionDatabase;
import com.example.financialliteracy.Fragments.DailyQuizFragment;
import com.example.financialliteracy.Models.Question;
import com.example.financialliteracy.R;

import org.w3c.dom.Node;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements DailyQuizFragment.OnFragmentInteractionListener {

    private Button temp;
    private Button temp2;
    private EditText difficulty;
    private View popupView;

    private DailyQuizFragment quizPopup;
    private QuestionDatabase db;

    //for the question num
    private Random random;
    private int randomNum;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Calendar calendar;

    private int today;
    private int lastDay;
    private int streak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = this.getSharedPreferences("My Pref", 0);
        editor = pref.edit();
        calendar = Calendar.getInstance();
        random = new Random();

        today = calendar.get(Calendar.DAY_OF_YEAR); // today in year
        lastDay = pref.getInt("LAST_DATE", 0);
        streak = pref.getInt("QUIZ_STREAK", 0);


        temp = findViewById(R.id.button_temp);
        difficulty = findViewById(R.id.edit_difficulty);
        popupView = findViewById(R.id.container_popup);
        //remove later
        temp2 = findViewById(R.id.button_popup);


        quizPopup = new DailyQuizFragment();

        if (pref.getInt("ANSWERED", 0) == 0) {
            if (lastDay != today - 1) {
                streak = 0;
                editor.putInt("QUIZ_STREAK", streak);
            }
            Bundle bundle = new Bundle();
            randomNum = random.nextInt(10);
            bundle.putInt("question", randomNum);
            quizPopup.setArguments(bundle);
            swapFragment(quizPopup);
            editor.putInt("LAST_DATE", today);
            editor.commit();

        }


        //quiz button
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for the quiz category
                try {
                    int value = Integer.parseInt(difficulty.getText().toString());


                    if (value == 1 || value == 2 || value == 3) {
                        Context c = view.getContext();
                        Intent intent = new Intent(c, QuizActivity.class);
                        intent.putExtra("Difficulty", value);
                        c.startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Place a value from 1 to 3!", Toast.LENGTH_LONG).show();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Place a value from 1 to 3!", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void launchInvestmentCalculatorActivity(View view) {

        Intent intent = new Intent(this, InvestmentCalculatorActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(int num) {
        // Question question = db.questionDao().getQuestion(num);
        // quizPopup.setQuestion(question);
    }


    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_popup, fragment);
        fragmentTransaction.commit();
    }


}

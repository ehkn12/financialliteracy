package com.example.financialliteracy.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialliteracy.Activities.MainActivity;
import com.example.financialliteracy.Activities.QuizActivity;
import com.example.financialliteracy.Databases.QuestionDatabase;
import com.example.financialliteracy.Models.Question;
import com.example.financialliteracy.R;


public class DailyQuizFragment extends DialogFragment {

    private OnFragmentInteractionListener mListener;
    private View view;

    private TextView question;
    private RadioGroup options;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private Button finish;
    private QuestionDatabase db;
    private Context context;

    private RadioGroup radioGroup;
    private RadioButton answer;

    private Question questionObject;

    private int questionNum;


    public DailyQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_daily_quiz, container, false);
        question = view.findViewById(R.id.text_question);
        options = view.findViewById(R.id.group_options);
        optionA = view.findViewById(R.id.radio_answer_1);
        optionB = view.findViewById(R.id.radio_answer_2);
        optionC = view.findViewById(R.id.radio_answer_3);
        finish = view.findViewById(R.id.button_submit);
        radioGroup = view.findViewById(R.id.group_options);

        context = getContext();

        db = db.getInstance(getContext());
        db.questionDao().insertAll(QuizActivity.getQuestionList());

        Bundle bundle = this.getArguments();
        questionObject = db.questionDao().getQuestion(bundle.getInt("question"));
        setQuestion(questionObject);

        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                answer = view.findViewById(radioGroup.getCheckedRadioButtonId());
                if (questionObject.getAnswer().equals(answer.getText())) {
                    Toast.makeText(getContext(), "Keep the streak going!", Toast.LENGTH_LONG).show();

                }

                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(DailyQuizFragment.this).commit();
                



               /* try {
                    RadioButton answer = view.findViewById(radioGroup.getCheckedRadioButtonId());
                    if (questionObject.getAnswer().equals(answer.getText())){
                        Toast.makeText(getContext(), "Keep the streak going!", Toast.LENGTH_LONG).show();
                        onDetach();


                    } else {
                        onDetach();
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(getContext(), "Choose an answer!", Toast.LENGTH_LONG).show();
                } */
            }
        });


        //receive message from handler?
        //use the arguments to populate the daily quiz pop up?
        //RECURSION??

        //in theory, if time == 12am, change question, and done question to false
        //if done question = false, open up fragment everytime app open, until skip or submit (skip will change streak to zero)
        //submit if answer is right, add to score? answer is wrong, end streak to zero . in both questions answer is done so dont open up question for that seesion?

        /*the streak could  be on a linked list, nodes are added and counted to see how the streak is going. (vs why not an arraylist), destroying it every time.
        streak could be stored as int streak, as number of nodes, however if linked list doesnt exist, that means theres no streak?
         */

        //picking the question can come from a randomiser -> how many whatever dummy questions we have for now

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int num) {
        if (mListener != null) {
            mListener.onFragmentInteraction(num);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int num);
    }

    public void setQuestion (Question questionData){
        question.setText(questionData.getQuestion());
        optionA.setText(questionData.getOptionA());
        optionB.setText(questionData.getOptionB());
        optionC.setText(questionData.getOptionC());

    }
}

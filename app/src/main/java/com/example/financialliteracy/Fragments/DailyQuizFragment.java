package com.example.financialliteracy.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.financialliteracy.R;


public class DailyQuizFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public DailyQuizFragment() {
        // Required empty public constructor
    }

    //receive message from handler?
    //use the arguments to populate the daily quiz pop up?
    //RECURSION??

    //in theory, if time == 12am, change question, and done question to false
    //if done question = false, open up fragment everytime app open, until skip or submit (skip will change streak to zero)
    //submit if answer is right, add to score? answer is wrong, end streak to zero . in both questions answer is done so dont open up question for that seesion?    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_quiz, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

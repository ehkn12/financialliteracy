package com.example.financialliteracy.AsyncTasks;

import android.os.AsyncTask;

import com.example.financialliteracy.Databases.QuestionDatabase;
import com.example.financialliteracy.Models.Question;

public class QuestionRetrieveAsyncTask extends AsyncTask<Integer, Integer, Question> {

    private QuestionAsyncTaskDelegate delegate;
    private QuestionDatabase questionDatabase;

    public void setDelegate(QuestionAsyncTaskDelegate delegate){this.delegate = delegate;}
    public void setQuestionDatabase (QuestionDatabase questionDatabase){this.questionDatabase = questionDatabase;}

    @Override
    protected Question doInBackground(Integer... integers) {
        return questionDatabase.questionDao().getQuestion(integers[0]);
    }

    protected void onPostExecute(Question question){
        delegate.handleQuestionReturned(question);
    }
}

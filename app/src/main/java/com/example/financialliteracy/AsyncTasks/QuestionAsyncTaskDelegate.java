package com.example.financialliteracy.AsyncTasks;

import com.example.financialliteracy.Models.Question;

public interface QuestionAsyncTaskDelegate {

    void handleQuestionReturned(Question question);
}

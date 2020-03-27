package com.example.financialliteracy.AsyncTasks;

import com.example.financialliteracy.Models.Question;

import java.util.List;

public interface QuestionCategoryAsyncTaskDelegate {
    void handleQuestionListReturned(List<Question> questionList);
}

package com.example.financialliteracy.Databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.financialliteracy.DataAccessObjects.QuestionDao;
import com.example.financialliteracy.Models.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();

    private static QuestionDatabase instance;
    public static QuestionDatabase getInstance(Context context){

        //TODO: change to asynctask
        if(instance == null){
            instance = Room.databaseBuilder(context, QuestionDatabase.class, "questDB").build();
        }
        return instance;
    }

}


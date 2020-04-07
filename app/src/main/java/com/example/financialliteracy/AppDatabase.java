package com.example.financialliteracy;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.math.BigDecimal;

@Database(entities = {Expense.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExpenseDao expenseDao();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context){
        if(instance != null) return instance;
        instance = Room.databaseBuilder(context, AppDatabase.class, "appdb").allowMainThreadQueries().build();
        return instance;
    }
}

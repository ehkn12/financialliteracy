package com.example.financialliteracy;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.math.BigDecimal;
import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM expense")
    List<Expense> getAll();

    @Insert
    void insert(Expense... expenses);

    @Query("DELETE FROM expense")
    void delete();
}

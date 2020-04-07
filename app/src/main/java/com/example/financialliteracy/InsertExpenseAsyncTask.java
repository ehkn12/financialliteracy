package com.example.financialliteracy;

import android.os.AsyncTask;

public class InsertExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {
    private ExpenseDao expenseDao;

    public InsertExpenseAsyncTask(AppDatabase db){
        expenseDao = db.expenseDao();
    }

    @Override
    protected Void doInBackground(Expense... expenses) {
        Expense e = expenses[0];
        expenseDao.insert(e);
        return null;
    }
}

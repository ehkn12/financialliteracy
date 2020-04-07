package com.example.financialliteracy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.financialliteracy.AppDatabase;
import com.example.financialliteracy.Constants;
import com.example.financialliteracy.Expense;
import com.example.financialliteracy.Fragments.ExpensePieChartFragment;
import com.example.financialliteracy.Fragments.PieChartFragment;
import com.example.financialliteracy.R;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseTrackerActivity extends AppCompatActivity {

    private String[] categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expense_tracker);
        this.categories = Constants.categories;

        Map<String, Double> valueMap = getValues();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle arguments = new Bundle();
        for(String category : categories){
            arguments.putDouble(category, valueMap.get(category));
        }
        ExpensePieChartFragment fragment = new ExpensePieChartFragment();
        fragment.setArguments(arguments);
        transaction.replace(R.id.pieFragment,fragment);
        transaction.commit();
    }

    public Map<String, Double> getValues(){
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        List<Expense> expenses = db.expenseDao().getAll();
        Date sevenDaysAgo = getSevenDaysAgo();
        List<Expense> toRemove = new ArrayList<>();
        for(Expense e : expenses){
            Date expenseDate = new Date(e.date);
            if(!expenseDate.equals(sevenDaysAgo) && !expenseDate.after(sevenDaysAgo)){
                toRemove.add(e);
            }
        }
        expenses.removeAll(toRemove);

        Map<String, Double> valueMap = new HashMap<>();
        for(String category : categories){
            valueMap.put(category, Double.valueOf(0));
        }
        for(Expense e : expenses){
            valueMap.put(e.category, Double.valueOf(e.value) + valueMap.get(e.category));
        }
        return valueMap;
    }

    public Date getSevenDaysAgo(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public void launchAddExpenseActivity(View view) {
        Intent intent = new Intent(this, AddExpenseActivity.class);
        startActivity(intent);
    }
}

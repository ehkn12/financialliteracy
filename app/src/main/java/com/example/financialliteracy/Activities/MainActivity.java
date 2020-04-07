package com.example.financialliteracy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.financialliteracy.AppDatabase;
import com.example.financialliteracy.Expense;
import com.example.financialliteracy.InsertExpenseAsyncTask;
import com.example.financialliteracy.R;

import java.math.BigDecimal;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = findViewById(R.id.button_temp);

//        temp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context c = view.getContext();
//                Intent intent = new Intent(c, QuizActivity.class);
//                c.startActivity(intent);
//
//            }
//        } );
    }

    public void launchInvestmentCalculatorActivity(View view) {

        Intent intent = new Intent(this, InvestmentCalculatorActivity.class);
        startActivity(intent);
    }

    public void launchExpenseTrackerActivity(View view){
        Intent intent = new Intent(this, ExpenseTrackerActivity.class);
        startActivity(intent);
    }

   
  

}


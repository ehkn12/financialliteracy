package com.example.financialliteracy.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financialliteracy.R;

public class InvestmentCalculatorActivity extends AppCompatActivity {

    private EditText tvInvestmentAmount;
    private EditText tvAnnualInterestRate;
    private EditText tvNumberOfYears;
    private TextView tvFutureValue;
    private Button btCompute;
    private Button btReset;

    private static final String TAG = "InvestmentCalculatorActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_calculator);

        tvInvestmentAmount = findViewById(R.id.tv_StartingAmount);
        tvNumberOfYears = findViewById(R.id.tv_Years);
        tvAnnualInterestRate = findViewById(R.id.tv_ReturnRate);
        tvFutureValue = findViewById(R.id.tv_FutureValue);


        btCompute = findViewById(R.id.bt_calculate);
        btReset = findViewById(R.id.bt_refresh);

        btCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeValue();
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalc();
            }
        });

    }

    @SuppressLint("DefaultLocale")
    private void computeValue() {
        try {
            double annualInterestRate = Double.parseDouble(String.valueOf(tvAnnualInterestRate.getText()));
            double monthlyInterestRate = annualInterestRate / 1200.0;
            int NumberOfYears = Integer.parseInt(String.valueOf(tvNumberOfYears.getText()));
            double investmentAmount = Double.parseDouble(String.valueOf(tvInvestmentAmount.getText()));
            double futureValue = investmentAmount * Math.pow(1.0 + monthlyInterestRate, NumberOfYears * 12);
            tvFutureValue.setText(String.format("%.2f", futureValue));
        } catch (Exception e) {
            Toast.makeText(this, "Please enter numeric values", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetCalc() {
        tvInvestmentAmount.setText("");
        tvAnnualInterestRate.setText("");
        tvNumberOfYears.setText("");
        tvFutureValue.setText("");
    }
}


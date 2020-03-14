package com.example.financialliteracy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.financialliteracy.R;

import java.text.DecimalFormat;
import java.util.Scanner;

public class taxcalc extends AppCompatActivity {

    EditText EditText1;
    TextView textViewR2;
    TextView textViewR3;
    String Result1, Result2;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxcalc);

        EditText1 = findViewById(R.id.EditText1);
        textViewR2 = findViewById(R.id.textViewR2);
        textViewR3 = findViewById(R.id.textViewR3);
        button = findViewById(R.id.button);


    }

    public void calculateTax (View view) {

        String s1 = EditText1.getText().toString();

        double grossIncome = Double.parseDouble(s1);
        double[] max = {0, 18200, 37000, 90000, 180000};
        double[] rate = {0, 0.19, 0.325, 0.37, 0.45};

        double left = grossIncome;
        double tax = 0.0d;

        for (int i = 1; i < max.length && left > 0; i++) {
            double df = Math.min(max[i] - max[i - 1], left);
            tax += rate[i] * df;
            left -= df;
            //System.out.println(df*rate[i]);
        }

        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        //System.out.println("Your inccome is $"+formatter.format(grossIncome)+", your tax is: $"+formatter.format(tax)+". Your income after tax is: $"+formatter.format(grossIncome-tax));
        Result1 = formatter.format(tax);
        Result2 = formatter.format(grossIncome - tax);

        textViewR2.setText(Result1);
        textViewR3.setText(Result2);

    }

}

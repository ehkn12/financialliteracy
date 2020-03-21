package com.example.financialliteracy.Activities;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream

import android.content.Context;
=======
>>>>>>> Stashed changes
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< Updated upstream
=======
import android.widget.EditText;
import android.widget.TextView;
>>>>>>> Stashed changes

import com.example.financialliteracy.R;

public class MainActivity extends AppCompatActivity {
    Button tax;

    private Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
        temp = findViewById(R.id.button_temp);

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent intent = new Intent(c, QuizActivity.class);
                c.startActivity(intent);

            }
        } );
    }

    public void launchInvestmentCalculatorActivity(View view) {

        Intent intent = new Intent(this, InvestmentCalculatorActivity.class);
=======

        tax = (Button) findViewById(R.id.tax);
        tax.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTaxTalc();
            }
        });
    }

    public void openTaxTalc (){
        Intent intent = new Intent(this, taxcalc.class);
>>>>>>> Stashed changes
        startActivity(intent);
    }

   
  

}


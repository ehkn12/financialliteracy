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

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class InvestmentCalculatorActivity extends AppCompatActivity {

    private EditText tvInvestmentAmount;
    private EditText tvAnnualInterestRate;
    private TextView tvTotalContributions;
    private TextView tvAdditionalContributions;
    private TextView tvTimesCompounded;
    private EditText tvNumberOfYears;
    private TextView tvFutureValue;
    private TextView tvInterestPrinciple;
    private TextView tvInterestContribution;
    private TextView tvTotalInterest;
    private LineChartView mChart;
    private Line mLine;


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
        tvTotalContributions = findViewById(R.id.tv_TotalContributions);
        tvAdditionalContributions = findViewById(R.id.tv_AdditionalContributions);
        tvTimesCompounded = findViewById(R.id.tv_TimesCompounded);
        tvInterestPrinciple = findViewById(R.id.tv_InterestPrincipal);
        tvInterestContribution = findViewById(R.id.tv_InterestContributions);
        tvTotalInterest = findViewById(R.id.tv_TotalInterest);
        mChart = findViewById(R.id.chart);

        String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"};
        int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};


        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();

        mLine = new Line(yAxisValues);

        for(int i = 0; i < axisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(mLine);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        mChart.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);

        axis.setTextSize(16);
        yAxis.setName("Amount");


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
            double r = Double.parseDouble(String.valueOf(tvAnnualInterestRate.getText()));
            int t = Integer.parseInt(String.valueOf(tvNumberOfYears.getText()));
            double P = Double.parseDouble(String.valueOf(tvInvestmentAmount.getText()));
            double PMT = Double.parseDouble(String.valueOf(tvAdditionalContributions.getText()));
            double n = Double.parseDouble(String.valueOf(tvTimesCompounded.getText()));

            double pow = Math.pow(1+((r/100)/n),n*t);
            double A = P*pow;
            double B = PMT*((pow-1)/((r/100)))*12;
            double totalContributions = PMT*t*12;
            double futureValue = A+B;
            double interestPrincipal = A-P;
            double interestContributions = B-totalContributions;
            double totalInterest = (((interestPrincipal+interestContributions)/futureValue)*100);

            tvFutureValue.setText(String.format("%.2f", futureValue));
            tvTotalContributions.setText(String.format("%.2f", totalContributions));
            tvInterestPrinciple.setText(String.format("%.2f", interestPrincipal));
            tvInterestContribution.setText(String.format("%.2f", interestContributions));
            tvTotalInterest.setText(String.format("%.2f", totalInterest));
        } catch (Exception e) {
            Toast.makeText(this, "Please enter numeric values", Toast.LENGTH_SHORT).show();

            //add no zero exception
        }
    }

    private void resetCalc() {
        tvInvestmentAmount.setText("");
        tvAnnualInterestRate.setText("");
        tvNumberOfYears.setText("");
        tvFutureValue.setText("");
        tvTimesCompounded.setText("");
        tvAdditionalContributions.setText("");
        tvTotalContributions.setText("");
        tvInterestContribution.setText("");
        tvInterestPrinciple.setText("");
        tvTotalInterest.setText("");
    }
}

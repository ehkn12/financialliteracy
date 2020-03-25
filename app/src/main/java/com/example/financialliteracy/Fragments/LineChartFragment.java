package com.example.financialliteracy.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.financialliteracy.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class LineChartFragment extends Fragment {

    private LineChartViewModel mLineChartViewModel;
    private static final String TAG = "LineChartFragment";


    public LineChartFragment() {

    }

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mLineChartViewModel =
                ViewModelProviders.of(this).get(LineChartViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_line_chart, container, false);

        final TextView textView = root.findViewById(R.id.text_lineChart);

        LineChart mLineChart = root.findViewById(R.id.lineView);

        double r = getArguments().getDouble("Interest Rate", 0);
        double year = getArguments().getDouble("Year", 0);
        System.out.println("testing2 : " + year);
        double P = getArguments().getDouble("Initial Investment", 0);
        double PMT = getArguments().getDouble("Additional Contributions", 0);
        double n = getArguments().getDouble("Times Compounded", 0);

        int year2 = (int) year;


        System.out.println("test"+ r);
        System.out.println(year);
        System.out.println(P);
        System.out.println(PMT);
        System.out.println(n);

//
//        mLineChart.setOnChartGestureListener((OnChartGestureListener) LineChartFragment.this.getActivity());
//        mLineChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) LineChartFragment.this.getActivity());
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(false);

        ArrayList<Entry> entries = new ArrayList();

        for (int t = 0; t <= year2; t++) {
            System.out.println("testing3 : " + t);

            entries.add(new Entry((float)t,(float)(P*(Math.pow(1+((r/100)/n),n*t))+ (PMT*(((Math.pow(1+((r/100)/n),n*t))-1)/((r/100)))*12))));
        }

            LineDataSet set1 = new LineDataSet(entries, "Total Contributions over Time");
            set1.setFillAlpha(110);
            set1.setLineWidth(2f);
            set1.setValueTextSize(10f);
            set1.setCircleRadius(4f);
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);

            YAxis leftAxis = mLineChart.getAxisLeft();
            leftAxis.setDrawAxisLine(false);
            mLineChart.getAxisRight().setEnabled(false);
            leftAxis.setAxisMinimum(0f);

            Legend leg1 = mLineChart.getLegend();
            leg1.setEnabled(false);

            Description des1 = mLineChart.getDescription();
            des1.setTextSize(14f);
            des1.setEnabled(false);


            XAxis xAxis = mLineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            mLineChart.setData(data);

            mLineChartViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
        }
}

package com.example.financialliteracy.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.Line;

public class LineChartFragment extends Fragment {

    private LineChartViewModel mLineChartViewModel;
    private LineChart mLineChart;

//    public static LineChartFragment newInstance() {
//        return new LineChartFragment();
//    }
    public LineChartFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mLineChartViewModel =
                ViewModelProviders.of(this).get(LineChartViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        final TextView textView = root.findViewById(R.id.text_pieChart);
        mLineChart = (LineChart) root.findViewById(R.id.lineView);
        mLineChart.setOnChartGestureListener((OnChartGestureListener) LineChartFragment.this.getActivity());
        mLineChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) LineChartFragment.this.getActivity());
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList();
        ArrayList<Entry> xValues = new ArrayList();

        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
        LineDataSet set2 = new LineDataSet(xValues, "Data Set 2");




        mLineChartViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLineChartViewModel = ViewModelProviders.of(this).get(LineChartViewModel.class);
        // TODO: Use the ViewModel
    }

}

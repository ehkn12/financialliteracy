package com.example.financialliteracy.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.financialliteracy.Constants;
import com.example.financialliteracy.R;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.HashMap;
import java.util.Map;

public class ExpensePieChartFragment extends Fragment {

    private PieChartViewModel mPieChartViewModel;
    private String[] categories;

    public ExpensePieChartFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mPieChartViewModel =
                ViewModelProviders.of(this).get(PieChartViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        final TextView textView = root.findViewById(R.id.text_pieChart);
        AnimatedPieView animatedPieView = root.findViewById(R.id.pieView);

        categories = Constants.categories;
        Map<String, Float> map = new HashMap<>();
        for(String category : categories){
            map.put(category, new Float(getArguments().getDouble(category, 0)));
        }

        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        Map<String, String> colorMap = Constants.colorMap;
        for(String category : categories){
            if(map.get(category).doubleValue() < 0.0001) continue;
            config.addData(new SimplePieInfo(map.get(category).doubleValue(), Color.parseColor(colorMap.get(category)), category));
        }
        config.drawText(true);
        config.strokeMode(false);
        config.textSize(24);
        config.duration(1000);
        config.selectListener(new OnPieSelectListener<IPieInfo>() {
            @Override
            public void onSelectPie(@NonNull IPieInfo pieInfo, boolean isFloatUp) {
                Toast.makeText(ExpensePieChartFragment.this.getActivity(),String.format("%s : %.2f", pieInfo.getDesc(), pieInfo.getValue()), Toast.LENGTH_SHORT).show();
            }
        });


        animatedPieView.applyConfig(config);
        animatedPieView.start();

        mPieChartViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}

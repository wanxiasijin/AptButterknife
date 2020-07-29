package com.example.aptbutterknife;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.BindView;
import com.example.butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.yeye)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setText("测试AptButterknife");
    }
}
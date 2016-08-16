package com.example.khwajabilal.structural_a8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Analyze extends AppCompatActivity {

    TextView resultTv;
    String resultString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
        resultTv=(TextView)findViewById(R.id.analyzeTxt);
        Intent i= getIntent();
        resultString=i.getStringExtra("resultString");
        resultTv.setText(resultString);
    }
}

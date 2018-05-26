package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle arguments = getIntent().getExtras();
        TextView tw = (TextView)findViewById(R.id.result_text);
        if(arguments!=null){
            String operation_result = arguments.get("operation_result").toString();
            tw.setText("Latest result based on what you saved in the file is: " + operation_result );
        }
    }


}

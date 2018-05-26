package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        TextView selection = (TextView) findViewById(R.id.selection);
        switch(view.getId()) {
            case R.id.Plus:
                if (checked){
                    selection.setText("Add numbers");
                }
                break;
            case R.id.Minus:
                if (checked){
                    selection.setText("Subtract numbers");
                }
                break;
            case R.id.Multiply:
                if (checked){
                    selection.setText("Multiply numbers");
                }
                break;
            case R.id.Divide:
                if (checked){
                    selection.setText("Divide numbers");
                }
                break;
        }
    }
    public void calculate(View view){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radios);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        TextView selection = (TextView) findViewById(R.id.selection);
        if (checkedRadioButtonId==-1) {
            selection.setText("can't calculate, fill in both fields and select operation");
            return;
        }
        RadioButton checkedRadioButton = (RadioButton) findViewById(checkedRadioButtonId);
        boolean checked = ((RadioButton) checkedRadioButton).isChecked();
        String convertedToString = String.valueOf(checkedRadioButtonId);
        selection.setText(convertedToString);
        EditText et1 = (EditText)findViewById(R.id.operand1);
        String sTextFromET1 = et1.getText().toString();
        if(sTextFromET1==null || sTextFromET1.isEmpty())
        {
            selection.setText("can't calculate, fill in both fields and select operation");
            return;
        }
        int operand1 = new Integer(sTextFromET1).intValue();
        EditText et2 = (EditText)findViewById(R.id.operand2);
        String sTextFromET2 = et2.getText().toString();
        if(sTextFromET2==null || sTextFromET2.isEmpty())
        {
            selection.setText("can't calculate, fill in both fields and select operation");
            return;
        }
        int operand2 = new Integer(sTextFromET2).intValue();
        switch(checkedRadioButtonId) {
            case R.id.Plus:
                if (checked){
                    if(String.valueOf(operand1+operand2)!= null && !String.valueOf(operand1+operand2).isEmpty())
                        selection.setText(String.valueOf(operand1+operand2));
                    else
                        selection.setText("can't calculate");
                }
                break;
            case R.id.Minus:
                if (checked){
                    selection.setText(String.valueOf(operand1-operand2));
                }
                break;
            case R.id.Multiply:
                if (checked){
                    selection.setText(String.valueOf(operand1*operand2));
                }
                break;
            case R.id.Divide:
                if (checked){
                    selection.setText(String.valueOf(operand1/operand2));
                }
                break;
        }
    }
}

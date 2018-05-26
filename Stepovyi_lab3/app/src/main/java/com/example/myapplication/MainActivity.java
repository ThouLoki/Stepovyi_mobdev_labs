package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";


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
        String result = "";
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
                    selection.setText(String.valueOf(operand1+operand2));
                    result=(String.valueOf(operand1+operand2));
                }
                break;
            case R.id.Minus:
                if (checked){
                    selection.setText(String.valueOf(operand1-operand2));
                    result=(String.valueOf(operand1-operand2));
                }
                break;
            case R.id.Multiply:
                if (checked){
                    selection.setText(String.valueOf(operand1*operand2));
                    result=(String.valueOf(operand1*operand2));
                }
                break;
            case R.id.Divide:
                if (checked){
                    selection.setText(String.valueOf(operand1/operand2));
                    result=(String.valueOf(operand1/operand2));
                }
                break;
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(result.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void open_result(View view)
    {
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("operation_result",text);
            startActivity(intent);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

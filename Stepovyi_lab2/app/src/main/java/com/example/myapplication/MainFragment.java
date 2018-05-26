package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Date;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;


public class MainFragment extends Fragment implements OnClickListener {
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) view.findViewById(R.id.calcbutton);
        // задаем обработчик кнопки
        button.setOnClickListener(this);
        return view;
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calcbutton:
                RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.radios);
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId==-1)
                {
                    mListener.onFragmentInteraction("can't calculate, fill in both fields and select operation");
                    return;
                }
                RadioButton checkedRadioButton = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                boolean checked = ((RadioButton) checkedRadioButton).isChecked();
                String convertedToString = String.valueOf(checkedRadioButtonId);
                EditText et1 = (EditText)getActivity().findViewById(R.id.operand1);
                String sTextFromET1 = et1.getText().toString();
                if(sTextFromET1==null || sTextFromET1.isEmpty())
                {
                    mListener.onFragmentInteraction("can't calculate, fill in both fields and select operation");
                    return;
                }
                int operand1 = new Integer(sTextFromET1).intValue();
                EditText et2 = (EditText)getActivity().findViewById(R.id.operand2);
                String sTextFromET2 = et2.getText().toString();
                if(sTextFromET2==null || sTextFromET2.isEmpty())
                {
                    mListener.onFragmentInteraction("can't calculate, fill in both fields and select operation");
                    return;
                }
                int operand2 = new Integer(sTextFromET2).intValue();
                switch(checkedRadioButtonId)
                {
                    case R.id.Plus:
                        if (checked){
                            mListener.onFragmentInteraction("result: "+String.valueOf(operand1+operand2));
                        }
                        break;
                    case R.id.Minus:
                        if (checked){
                            mListener.onFragmentInteraction("result: "+String.valueOf(operand1-operand2));
                        }
                        break;
                    case R.id.Multiply:
                        if (checked){
                            mListener.onFragmentInteraction("result: "+String.valueOf(operand1*operand2));
                        }
                        break;
                    case R.id.Divide:
                        if (checked){
                            mListener.onFragmentInteraction("result: "+String.valueOf(operand1/operand2));
                        }
                        break;
                }
                break;
        }
    }

}

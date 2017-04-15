package com.example.multiplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.main.R;

import java.util.Locale;

/**
 * Created by JacobKapp on 4/8/2017.
 */

public class MultiplicationFragment extends Fragment {

    public MultiplicationFragment(){}
    EditText firstNumber;
    EditText secondNumber;
    TextView multiplyResult;
    Button btnMultiply;
    double num1,num2,value;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_multiplication, container, false);

        firstNumber = (EditText)view.findViewById(R.id.txtMultNumber1);
        secondNumber = (EditText)view.findViewById(R.id.txtMultNumber2);
        multiplyResult = (TextView)view.findViewById(R.id.txtMultResult);
        btnMultiply = (Button)view.findViewById(R.id.btnMultiply);

        btnMultiply.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num1 = Double.parseDouble(firstNumber.getText().toString());
                num2 = Double.parseDouble(secondNumber.getText().toString());
                value = num1 * num2;
                multiplyResult.setText(String.format(Locale.ENGLISH, "(%f)", value) );
            }
        });
        return view;
    }
}

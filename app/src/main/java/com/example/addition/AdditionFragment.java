package com.example.addition;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import com.example.division.DivisionFragment;
import com.example.main.R;

import static android.R.attr.value;

/**
 * Created by JacobKapp on 4/8/2017.
 */

public class AdditionFragment extends Fragment {
    EditText firstNumber;
    EditText secondNumber;
    TextView addResult;
    Button btnAdd;
    double num1,num2,sum;

    public AdditionFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_addition, container, false);


        firstNumber = (EditText)view.findViewById(R.id.txtNumber1);
        secondNumber = (EditText)view.findViewById(R.id.txtNumber2);
        addResult = (TextView)view.findViewById(R.id.txtResult);
        btnAdd = (Button)view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                /**
                 * Modified by Samrat Adhikari on 4/13/2017.
                 */

                String text1 = firstNumber.getText().toString();
                String text2 = secondNumber.getText().toString();

                // Check to see inputted numbers are not null
                if(text1.matches("")|| text2.matches("")){
                    Toast.makeText(AdditionFragment.this.getActivity(), R.string.addition_enter_value, Toast.LENGTH_SHORT).show();
                } else {
                    num1 = Double.parseDouble(text1);
                    num2 = Double.parseDouble(text2);
                    sum = num1 + num2;
                    addResult.setText(String.format(Locale.ENGLISH, "(%f)", sum));
                }
            }
        });
        return view;
    }

}

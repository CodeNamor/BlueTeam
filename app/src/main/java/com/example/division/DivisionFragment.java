/**
 * Created by Samrat S Adhikari
 */

package com.example.division;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.main.R;
import java.util.Locale;

public class DivisionFragment extends Fragment {

    //constructor
    public DivisionFragment(){}

    // Variables
    EditText firstNumber;
    EditText secondNumber;
    TextView divisionResult;
    Button btnDivision;
    double num1,num2,value;
    // End Variables

    //Public Methods

    //OnCreateView Override
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_division, container, false);

        firstNumber = (EditText)view.findViewById(R.id.txtDivNumber1);
        secondNumber = (EditText)view.findViewById(R.id.txtDivNumber2);
        divisionResult = (TextView)view.findViewById(R.id.txtDivResult);
        btnDivision = (Button)view.findViewById(R.id.btnDivide);

        btnDivision.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String text1 = firstNumber.getText().toString();
                String text2 = secondNumber.getText().toString();

                // Check to see inputted numbers are not null
                if(text1.matches("")|| text2.matches("")){
                    Toast.makeText(DivisionFragment.this.getActivity(), R.string.division_enter_value, Toast.LENGTH_SHORT).show();
                } else {
                    num1 = Double.parseDouble(text1);
                    num2 = Double.parseDouble(text2);
                    // Avoid dividing a number by zero
                    if (num2 != 0) {
                        value = num1 / num2;
                        divisionResult.setText(String.format(Locale.ENGLISH, "(%f)", value) );
                    } else {
                        Toast.makeText(DivisionFragment.this.getActivity(), R.string.division_divide_by_zero, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    //End Public Methods
}
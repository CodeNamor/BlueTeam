package com.example.subtraction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addition.AdditionFragment;
import com.example.main.R;

import java.util.Locale;

/**
 * Created by derek on 4/15/2017.
 */

public class SubtractionFragment extends Fragment {
        EditText firstNumber;
        EditText secondNumber;
        TextView addResult;
        Button btnSubtract;
        double num1,num2,dif;


        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View view =  inflater.inflate(R.layout.activity_addition, container, false);


            firstNumber = (EditText)view.findViewById(R.id.txtNumber1);
            secondNumber = (EditText)view.findViewById(R.id.txtNumber2);
            addResult = (TextView)view.findViewById(R.id.txtResult);
            btnSubtract = (Button)view.findViewById(R.id.btnSubtract);

            btnSubtract.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    /**
                     * Modified by F. Derek Roman on 4/15/2017.
                     */

                    String text1 = firstNumber.getText().toString();
                    String text2 = secondNumber.getText().toString();

                    // Check to see inputted numbers are not null
                    if(text1.matches("")|| text2.matches("")){
                        Toast.makeText(SubtractionFragment.this.getActivity(), R.string.subtraction_enter_value, Toast.LENGTH_SHORT).show();
                    } else {
                        num1 = Double.parseDouble(text1);
                        num2 = Double.parseDouble(text2);
                        dif = num1 - num2;
                        addResult.setText(String.format(Locale.ENGLISH, "(%f)", dif));
                    }
                }
            });
            return view;
        }
    }

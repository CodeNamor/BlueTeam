package com.example.temperature;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.main.R;

import org.w3c.dom.Text;

import java.util.Locale;

/**
 * Created by JacobKapp on 4/8/2017.
 */

public class TemperatureFragment extends Fragment {

    TextView switchText;
    TextView resultText;
    Button btnTemp;
    Switch onOffSwitch;
    EditText tempToConvert;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_temperature, container, false);

        switchText = (TextView) view.findViewById(R.id.textTempView4);
        resultText = (TextView) view.findViewById(R.id.txtTempResult);
        btnTemp = (Button)view.findViewById(R.id.btnTemp);
        onOffSwitch = (Switch)  view.findViewById(R.id.switch2);
        tempToConvert = (EditText)view.findViewById(R.id.txtTempNumber2);

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String tempString = tempToConvert.getText().toString();
                int tempConverted = Integer.parseInt(tempString);
                double temp = 0;

                if(isChecked) {
                    switchText.setText("C to F");
                    temp = convertCtoF(tempConverted);
                }
                else {
                    switchText.setText("F to C");
                    temp = convertFtoC(tempConverted);
                }
                resultText.setText(String.format(Locale.ENGLISH, "(%f)", temp) );
            }
        });

        btnTemp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String tempString = tempToConvert.getText().toString();
                int tempConverted = Integer.parseInt(tempString);
                double temp = 0;

                if(onOffSwitch.isChecked()){
                    temp = convertCtoF(tempConverted);
                }else{
                    temp = convertFtoC(tempConverted);
                }

                resultText.setText(String.format(Locale.ENGLISH, "(%f)", temp) );
            }
        });
        return view;
    }

    private double convertFtoC(int temp){
        double c = (temp - 32)/1.8;
        return c;
    }

    private double convertCtoF(int temp){
        double f = (temp*1.8)+32;
        return f;
    }
}

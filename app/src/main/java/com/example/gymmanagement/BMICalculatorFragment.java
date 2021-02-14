package com.example.gymmanagement;
import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BMICalculatorFragment extends Fragment {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    TextView lblBMI;
    Button btnCalculate;
    TextView txtHeightFeet;
    TextView txtHeightInches;
    TextView txtWeight;
    ImageView imgbmi;
    CalculateBMI calculateBMI;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmicalculator,container,false);
        btnCalculate = (Button) v.findViewById(R.id.btnCalculate);
        lblBMI = (TextView) v.findViewById(R.id.lblBMI);
        txtHeightFeet = (TextView) v.findViewById(R.id.txtFeet);
        txtHeightInches = (TextView) v.findViewById(R.id.txtInches);
        txtWeight = (TextView) v.findViewById(R.id.txtWeight);
        imgbmi = (ImageView) v.findViewById(R.id.imgbmi);
        TextView textview =v.findViewById(R.id.bmicolor);
        String B = "<font color='#B2EBF2'>B</font>";
        String M = "<font color='#F8BBD0'>M</font>";
        String I = "<font color='#FFEBEE'>I</font>";
        String C = "<font color='#B2EBF2'>C</font>";
        String A = "<font color='#F8BBD0'>A</font>";
        String L = "<font color='#FFEBEE'>L</font>";
        String Ca = "<font color='#B2EBF2'>C</font>";
        String U = "<font color='#F8BBD0'>U</font>";
        String La = "<font color='#FFEBEE'>L</font>";
        String Aa = "<font color='#B2EBF2'>A</font>";
        String T = "<font color='#F8BBD0'>T</font>";
        String O = "<font color='#FFEBEE'>O</font>";
        String Rw = "<font color='#B2EBF2'>R</font>";

        textview.setText(Html.fromHtml(B+M+I+" "+C+A+L+Ca+U+La+Aa+T+O+Rw));




        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Double txthfeet = Double.parseDouble(txtHeightFeet.getText().toString());
                    Double txthinch = Double.parseDouble(txtHeightInches.getText().toString());
                    Double txtweight = Double.parseDouble(txtWeight.getText().toString());


                    calculateBMI = new CalculateBMI(txthfeet,txthinch,txtweight);
                    // Calculating BMI
                    double bmi = calculateBMI.camlculatebmi(calculateBMI.getInputkg(),calculateBMI.getInputinches(),calculateBMI.getInputfeet());

                    //Getting BMI Type
                    String bmitype = calculateBMI.getbmitype(bmi);

                    //Getting device Date
                    String dat2e = formatter.format(date);


                    Toast.makeText(getActivity(),"Your BMI" + bmi +" " + bmitype, Toast.LENGTH_SHORT).show();

                    //Adding to Display Elements
                    String bmin=bmi+"";
                    String newbmi = "<font color='#C62828'>"+bmin+"</font>";
                    lblBMI.setText(Html.fromHtml("Your BMI is "+newbmi));
                    switch (bmitype)
                    {
                        case  "Underweight" :
                            imgbmi.setImageResource(R.drawable.underweight);
                            break;

                        case  "Normal Weight" :
                            imgbmi.setImageResource(R.drawable.normal);
                            break;

                        case "Over Weight"   :
                            imgbmi.setImageResource(R.drawable.overweight);
                            break;

                        case  "Obesity" :
                            imgbmi.setImageResource(R.drawable.obese);
                            break;

                        case "Extremely Obesity":
                            imgbmi.setImageResource(R.drawable.extremelyobese);
                            break;

                        default:
                            // imgbmi.setImageResource(R.drawable.maxresdefault);
                            break;

                    }

                }

                catch (Exception x)
                {
                    Toast.makeText(getActivity(),"Enter Valid Input" + x, Toast.LENGTH_SHORT).show();

                }


            }
        });


        return v;


    }
}

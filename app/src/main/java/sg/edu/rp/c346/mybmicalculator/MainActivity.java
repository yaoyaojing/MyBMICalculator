package sg.edu.rp.c346.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    Button btnCalculate;
    Button btnReset;
    Button btnDetail;
    TextView tvDate;
    TextView tvBMI;

    @Override
    protected void onResume() {
        super.onResume();

        //step1:
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        //step2:
        String bmi = prefs.getString("bmi", "0.0");
        String date = prefs.getString("date", "");
        String result = prefs.getString("result", "");


        //step3:
        tvBMI.setText( bmi);
        tvDate.setText(date);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = (EditText)findViewById(R.id.editTextWeight);
        etHeight = (EditText)findViewById(R.id.editTextHeight);
        btnCalculate = (Button)findViewById(R.id.buttonCalculate);
        btnReset= (Button)findViewById(R.id.buttonReset);
        tvDate = (TextView)findViewById(R.id.textViewDate);
        tvBMI = (TextView)findViewById(R.id.textViewBMI);
        btnDetail = (Button)findViewById(R.id.buttonDetail);



        etWeight.setSelection(0);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etWeight.getText().toString().isEmpty()
                        || etHeight.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter data", Toast.LENGTH_SHORT).show();
                }else{

                    //step1:
                    float weight = Float.parseFloat(etWeight.getText().toString());
                    float height = Float.parseFloat(etHeight.getText().toString());
                    String bmi = Float.toString(weight/(height*height));
                    float bmiFloat = weight/(height*height);
                    String result =" ";
                    if(bmiFloat >= 30){
                        result = "Obese";
                    }else if(bmiFloat >= 25){
                        result = "Overweight";
                    }else if(bmiFloat >= 18.5){
                        result = "Normal";
                    }else{
                        result = "Underweight";
                    }

                    //Create a calendar object with current date/time
                    Calendar now = Calendar.getInstance();
                    String datetime = now.get(Calendar.DAY_OF_MONTH) + "/"
                            + (now.get(Calendar.MONTH) + 1) + "/"
                            + now.get(Calendar.YEAR) + " "
                            + now.get(Calendar.HOUR_OF_DAY) + ":"
                            + now.get(Calendar.MINUTE);


                    //step2:
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                    //step3:
                    SharedPreferences.Editor prefEdit = prefs.edit();

                    //step4:
                    prefEdit.putString("bmi", bmi);
                    prefEdit.putString("date", datetime);
                    prefEdit.putString("result", result);

                    //step5:
                    prefEdit.commit();

                    etWeight.setText("");
                    etHeight.setText("");
                    tvDate.setText(datetime);
                    tvBMI.setText(bmi);

                }


            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDate.setText("");
                tvBMI.setText("0.0");

                //step2:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                //step3:
                SharedPreferences.Editor prefEdit = prefs.edit();

                //step4:
                prefEdit.putString("bmi", "0.0");
                prefEdit.putString("date", "");

                //step5:
                prefEdit.commit();

            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etWeight.getText().toString().isEmpty()
                        || etHeight.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter data", Toast.LENGTH_SHORT).show();
                }else{
                    //step1:
                    float weight = Float.parseFloat(etWeight.getText().toString());
                    float height = Float.parseFloat(etHeight.getText().toString());
                    String bmi = Float.toString(weight/(height*height));
                    float bmiFloat = weight/(height*height);
                    String result = "";
                    if(bmiFloat >= 30){
                        result = "Obese";
                    }else if(bmiFloat >= 25){
                        result = "Overweight";
                    }else if(bmiFloat >= 18.5){
                        result = "Normal";
                    }else{
                        result = "Underweight";
                    }
                    Intent intent = new Intent(getBaseContext(), Main2Activity.class);

                    intent.putExtra("result", "Your are " + result);

                    startActivity(intent);
                }
            }
        });

    }
}

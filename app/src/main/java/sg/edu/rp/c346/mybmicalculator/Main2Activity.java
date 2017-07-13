package sg.edu.rp.c346.mybmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResult = (TextView)findViewById(R.id.textViewResult);

        Intent intentREceived = getIntent();
        String result = intentREceived.getStringExtra("result");

        tvResult.setText(result);
    }
}

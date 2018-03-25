package com.weteam.weteam.dziennikprzedszkolaka;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import architecture.Preschool;

public class PreschoolsInformationActivity extends StartActivity {

    Preschool preschool;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preschools_information);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            id = myIntent.getInt("id");
            preschool = findPreschoolById(id);
        }

        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(preschool.name);
        textView = (TextView) findViewById(R.id.location);
        textView.setText(preschool.location);
        textView = (TextView) findViewById(R.id.phone);
        textView.setText(preschool.phone);
        textView = (TextView) findViewById(R.id.openingHours);
        textView.setText(preschool.openingHours);
    }
}

package com.weteam.weteam.dziennikprzedszkolaka;

import android.os.Bundle;
import android.widget.TextView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Preschool;
import architecture.PreschoolGroup;

public class ViewScheduleActivity extends StartActivity {

    PreschoolGroup group;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null) {
            userId = myIntent.getInt("id");
            group= findGroupById(userId);
        }

        TextView textView = (TextView)findViewById(R.id.poniedzialek);
        textView.setText(group.schedule.poniedzialek);
        textView = (TextView)findViewById(R.id.wtorek);
        textView.setText(group.schedule.wtorek);
        textView = (TextView)findViewById(R.id.sroda);
        textView.setText(group.schedule.sroda);
        textView = (TextView)findViewById(R.id.czwartek);
        textView.setText(group.schedule.czwartek);
        textView = (TextView)findViewById(R.id.piatek);
        textView.setText(group.schedule.piatek);
    }
}

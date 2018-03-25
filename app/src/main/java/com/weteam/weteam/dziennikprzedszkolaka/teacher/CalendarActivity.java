package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.PreschoolGroup;

public class CalendarActivity extends StartActivity {

    int groupId;
    PreschoolGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }
    }


    public void newDate(View view) {

        Intent intent = new Intent("DzPAddDateActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void viewCalendar(View view) {
        Intent intent = new Intent("DzPViewCalendarActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }
}

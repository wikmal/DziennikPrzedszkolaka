package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.PreschoolGroup;

public class ScheduleActivity extends StartActivity {

    PreschoolGroup group;
    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }
    }

    public void viewSchedule(View view) {
        if(group.schedule!=null) {
            Intent intent = new Intent("DzPViewScheduleActivity");
            intent.putExtra("id", group.preschoolGroupId);
            startActivity(intent);
        }
        else {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.schedule_doesn_t_exist, duration);

            toast.show();
        }
    }

    public void createSchedule(View view) {
        if(group.schedule == null) {
            Intent intent = new Intent("DzPCreateScheduleActivity");
            intent.putExtra("id", group.preschoolGroupId);
            startActivity(intent);
        }else{
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.schedule_already_exists, duration);
            toast.show();
        }
    }

    public void editSchedule(View view) {
        if(group.schedule != null) {
            Intent intent = new Intent("DzPEditScheduleActivity");
            intent.putExtra("id", group.preschoolGroupId);
            startActivity(intent);
        }else {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.no_schedule_to_edit, duration);
            toast.show();
        }
    }
}

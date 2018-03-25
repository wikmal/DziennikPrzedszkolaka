package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.PreschoolGroup;
import architecture.Schedule;

public class CreateScheduleActivity extends StartActivity {

    PreschoolGroup group;
    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }
    }

    public void createSchedule(View view) {
        String pn, wt, sr, cz, pt;

        EditText editText = (EditText)findViewById(R.id.poniedzialek);
        pn = editText.getText().toString();
        editText = (EditText)findViewById(R.id.wtorek);
        wt = editText.getText().toString();
        editText = (EditText)findViewById(R.id.sroda);
        sr = editText.getText().toString();
        editText = (EditText)findViewById(R.id.czwartek);
        cz = editText.getText().toString();
        editText = (EditText)findViewById(R.id.piatek);
        pt = editText.getText().toString();


            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                group.schedule = new Schedule(pn, wt, sr, cz, pt);

                toast = Toast.makeText(context, R.string.schedule_created, duration);
                saveAll();
            }
            catch (Exception e){
                toast = Toast.makeText(context, R.string.schedule_not_created, duration);
            }

            toast.show();

            Intent intent = new Intent("DzPScheduleActivity");
            intent.putExtra("id", group.preschoolGroupId);
            startActivity(intent);
    }
}

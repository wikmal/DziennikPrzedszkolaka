package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import architecture.Calendar;
import architecture.PreschoolGroup;

public class ViewCalendarActivity extends StartActivity {

    int groupId;
    PreschoolGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calendar);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }

        showCalendar();
    }

    private void showCalendar() {
        ArrayList<String> calendar = new ArrayList<>();
        ListView list ;
        ArrayAdapter<String> adapter ;
        String fusion;
        Calendar i;
        Queue<Calendar> buffor = new PriorityQueue<>();
        int size = group.calendar.size();
        for(int c = 0; c<size; c++){
            i = group.calendar.poll();
            fusion = String.valueOf(i.dzien) + "-" + String.valueOf(i.miesiac)+"     "+i.imie;
            calendar.add(fusion);
            buffor.add(i);
        }

        for(int c = 0; c<size; c++) {
            group.calendar.add(buffor.poll());
        }



        list=(ListView)findViewById(R.id.calendar);

        adapter=new ArrayAdapter<String>(this,R.layout.layout_edit_text,calendar);

        list.setAdapter(adapter);
    }


}

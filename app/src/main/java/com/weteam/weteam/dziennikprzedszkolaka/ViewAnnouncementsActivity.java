package com.weteam.weteam.dziennikprzedszkolaka;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import architecture.Announcement;
import architecture.PreschoolGroup;

public class ViewAnnouncementsActivity extends StartActivity {

    PreschoolGroup group;
    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_announcements);

        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }
        showListOfAnnouncements();
    }

    private void showListOfAnnouncements() {

        ArrayList<String> listOfAnnouncements = new ArrayList<>();
        ListView list ;
        ArrayAdapter<String> adapter ;
        String fusion;
        Announcement i;

        for(int j=group.announcements.size()-1;j>=0;j--){
            i=group.announcements.get(j);
            fusion = i.insertDate.toString();
            fusion+= "\n" + i.content + "\n";
            listOfAnnouncements.add(fusion);
        }


        list=(ListView)findViewById(R.id.AnnouncementList);

        adapter=new ArrayAdapter<String>(this,R.layout.layout_edit_text,listOfAnnouncements);

        list.setAdapter(adapter);
    }
}

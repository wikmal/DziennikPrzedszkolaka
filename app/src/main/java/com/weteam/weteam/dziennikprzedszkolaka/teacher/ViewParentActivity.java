package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;

import architecture.Parent;
import architecture.PreschoolGroup;

public class ViewParentActivity extends StartActivity {

    int groupId;
    PreschoolGroup group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parent);



        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }

        showListOfParents();
    }

    private void showListOfParents() {

        ArrayList<String> listOfParents = new ArrayList<>();
        ListView list ;
        ArrayAdapter<String> adapter ;

        for(Parent i: group.parents){
            if(i.preschoolGroupId == group.preschoolGroupId) {
                listOfParents.add(i.name);
            }
        }

        list=(ListView)findViewById(R.id.ChildrenList);

        adapter=new ArrayAdapter<String>(this,R.layout.layout_edit_text,listOfParents);

        list.setAdapter(adapter);

    }
}

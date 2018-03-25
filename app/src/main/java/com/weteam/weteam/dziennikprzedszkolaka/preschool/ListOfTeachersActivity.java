package com.weteam.weteam.dziennikprzedszkolaka.preschool;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;

import architecture.Preschool;
import architecture.Teacher;

public class ListOfTeachersActivity extends StartActivity {

    int preschoolId;
    Preschool preschool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_teachers);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            preschoolId = myIntent.getInt("id");
            preschool = findPreschoolById(preschoolId);
        }
        showListOfTeachers();
    }

    private void showListOfTeachers() {

        ArrayList<String> listOfTeachers = new ArrayList<>();
        ListView list ;
        ArrayAdapter<String> adapter ;

        for(Teacher i:teachers){
            if(i.preschoolId == preschoolId)
                listOfTeachers.add(i.name);
        }

        list=(ListView)findViewById(R.id.TeachersList);
        adapter=new ArrayAdapter<String>(this,R.layout.layout_edit_text,listOfTeachers);
        list.setAdapter(adapter);
    }


}

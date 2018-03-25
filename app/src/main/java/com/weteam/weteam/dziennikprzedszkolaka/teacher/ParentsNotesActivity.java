package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;

import architecture.Message;
import architecture.Parent;
import architecture.Teacher;

public class ParentsNotesActivity extends StartActivity {

    Parent parent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_notes);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null) {
            id = myIntent.getInt("id");
            parent = findParentById(id);
        }
        showNotes();
    }

    private void showNotes() {

        ArrayList<String> listOfNotes = new ArrayList<>();
        ListView list ;
        ArrayAdapter<String> adapter ;
        String fusion;
        Message i;

        for(int j=parent.messages.size()-1;j>=0;j--){
                i=parent.messages.get(j);
                fusion=i.insertDate;
                fusion+="\n"+i.text+"\n";
                listOfNotes.add(fusion);
        }

        list=(ListView)findViewById(R.id.TeachersList);

        adapter=new ArrayAdapter<String>(this,R.layout.layout_edit_text,listOfNotes);

        list.setAdapter(adapter);

    }


}

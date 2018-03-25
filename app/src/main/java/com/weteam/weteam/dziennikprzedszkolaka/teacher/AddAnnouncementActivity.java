package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import architecture.Announcement;
import architecture.PreschoolGroup;

public class AddAnnouncementActivity extends StartActivity {

    PreschoolGroup group;
    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent!=null) {
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }

    }

    public void createAnnouncement(View view) {

        EditText editText = (EditText) findViewById(R.id.content);
        String content = editText.getText().toString();
        int id = findNewAnnouncementId();

        Toast toast;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;


        if(checkEmptyField(content, editText)){

            try {
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Announcement a = new Announcement(id, content, date);
                group.announcements.add(a);
                toast = Toast.makeText(context, R.string.announcement_created, duration);

                editText = (EditText)findViewById(R.id.content);
                editText.setText("");

                saveAll();
            }
            catch (Exception e){
                toast = Toast.makeText(context, R.string.announcement_not_created, duration);
            }
            toast.show();
        }
    }

    private int findNewAnnouncementId(){

        int id = 0;
        for(Announcement a : group.announcements){
            id = a.id;
        }
        id++;
        return id;
    }
    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }
}

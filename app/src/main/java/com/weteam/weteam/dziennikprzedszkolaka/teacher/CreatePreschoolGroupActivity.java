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
import architecture.Teacher;

public class CreatePreschoolGroupActivity extends StartActivity {

    Teacher teacher;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_preschool_group);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent!=null) {
            userId = myIntent.getInt("id");
            teacher = findTeacherById(userId);
        }
    }

    public void createPreschoolGroup(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        if (checkEmptyField(name, editText)) {

            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                int preschoolGroupId = findNewPreschoolGroupId();
                PreschoolGroup preschoolGroup = new PreschoolGroup(name, preschoolGroupId, teacher.id);

                preschoolGroups.add(preschoolGroup);
                teacher.groupId = preschoolGroup.preschoolGroupId;

                saveAll();

                Intent intent = new Intent("DzPTeacherMenuActivity");
                intent.putExtra("id", teacher.id);
                startActivity(intent);

                toast = Toast.makeText(context, R.string.preschool_group_created, duration);

            } catch (Exception e) {
                toast = Toast.makeText(context, R.string.preschool_group_not_created, duration);
            }
            toast.show();
        }
    }

    public int findNewPreschoolGroupId() {
        int id = 0;
        for(PreschoolGroup i : preschoolGroups){
            if(id<i.preschoolGroupId){
                id = i.preschoolGroupId;
            }
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

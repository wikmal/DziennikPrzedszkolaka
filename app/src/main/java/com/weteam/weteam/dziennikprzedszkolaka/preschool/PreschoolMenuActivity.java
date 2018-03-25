package com.weteam.weteam.dziennikprzedszkolaka.preschool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;
import java.util.List;

import architecture.Parent;
import architecture.Preschool;
import architecture.PreschoolGroup;
import architecture.Teacher;
import architecture.User;

public class PreschoolMenuActivity extends StartActivity {

    Preschool user;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preschool_menu);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            userId = myIntent.getInt("id");
            user = findPreschoolById(userId);
        }
    }

    public void editInformation(View view) {
        Intent intent = new Intent("DzPEditPreschoolActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void createTeacher(View view) {
        Intent intent = new Intent("DzPCreateTeacherActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void deleteTeacher(View view) {
        List<String> listItems = new ArrayList<String>();
        final ArrayList <Teacher> teacherOfPreschool = new ArrayList<>();

        for(Teacher i: teachers){
            if(i.preschoolId == user.id) {
                listItems.add(i.name);
                teacherOfPreschool.add(i);
            }
        }
        CharSequence[] teachersList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_teacher);
        builder.setItems(teachersList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Teacher t = findTeacherById(teacherOfPreschool.get(i).id);
                PreschoolGroup group = findGroupById(teacherOfPreschool.get(i).id);

                Toast toast;
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                try {
                    for(Parent p:group.parents){
                            if(p.preschoolGroupId == group.preschoolGroupId){
                                users.remove(p);
                                group.parents.remove(p);
                        }
                    }
                    preschoolGroups.remove(group);
                    users.remove(t);
                    teachers.remove(t);

                    toast = Toast.makeText(context, R.string.teacher_deleted, duration);
                    saveAll();
                } catch (Exception e){
                    toast = Toast.makeText(context, R.string.teacher_not_deleted, duration);
                }
                toast.show();
            }
        });
        builder.create();
        builder.show();
    }

    public void addMenu(View view) {
        Intent intent = new Intent("DzPMainFoodActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void goToGuides(View view) {
        Intent intent = new Intent("DzPGuidesActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void viewTeachers(View view) {
        Intent intent = new Intent("DzPViewTeachersActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void chooseEditTeacher(View view) {
        List<String> listItems = new ArrayList<String>();
        final ArrayList <Teacher> teacherOfPreschool = new ArrayList<>();

        for(Teacher i: teachers){
            if(i.preschoolId == user.id) {
                listItems.add(i.name);
                teacherOfPreschool.add(i);
            }
        }
        CharSequence[] teachersList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_teacher);
        builder.setItems(teachersList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent("DzPEditTeacher2Activity");
                intent.putExtra("id", teacherOfPreschool.get(i).id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }
}
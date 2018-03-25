package com.weteam.weteam.dziennikprzedszkolaka.parent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;
import com.weteam.weteam.dziennikprzedszkolaka.childrenWork.MainChildrenActivity;
import com.weteam.weteam.dziennikprzedszkolaka.foodMenu.MainFoodActivity;

import java.util.ArrayList;
import java.util.List;

import architecture.Guide;
import architecture.Parent;
import architecture.Preschool;
import architecture.PreschoolGroup;
import architecture.Teacher;

public class ParentMenuActivity extends StartActivity {

    Parent user;
    int userId;
    PreschoolGroup group;
    Teacher teacher;
    Preschool preschool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_menu);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null) {
            userId = myIntent.getInt("id");
            user = findParentById(userId);
            group = findGroupById(user.preschoolGroupId);
            teacher = findTeacherById(group.teacherId);
            preschool = findPreschoolById(teacher.preschoolId);
        }
    }

    public void viewAnnouncements(View view) {
        Intent intent = new Intent("DzPViewAnnouncementsActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void viewPayments(View view) {
        Intent intent = new Intent("DzPViewPaymentsActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void guidebook(View view) {
        loadPreschools();
        List<String> listItems = new ArrayList<String>();
        final ArrayList <Guide> guidesOfPreschool = new ArrayList<>();

        for(Guide i: preschool.guideBook){
            listItems.add(i.title);
            guidesOfPreschool.add(i);
        }

        CharSequence[] guidesList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_guide);
        builder.setItems(guidesList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent("DzPViewGuidesActivity");
                intent.putExtra("guideId", guidesOfPreschool.get(i).id);
                intent.putExtra("id", preschool.id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void viewFoodMenu(View view) {

        Cursor cursor= MainFoodActivity.sqLiteHelper.getData("SELECT * FROM FOOD"+preschool.id);

        if(cursor.moveToNext()) {
            Intent intent = new Intent("DzPFoodGallery");
            intent.putExtra("id", preschool.id);
            startActivity(intent);
        } else {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.no_food_menu, duration);

            toast.show();
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

    public void addParentNote(View view) {
        Intent intent = new Intent("DzPAddParentNoteActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void editInformation(View view) {
        Intent intent = new Intent("DzPEditParentActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void viewPreschoolDetails(View view) {
        Intent intent = new Intent("DzPPreschoolInformationActivity");
        intent.putExtra("id", preschool.id);
        startActivity(intent);
    }

    public void showGallery(View view) {
        Cursor cursor= MainChildrenActivity.sqLiteHelper.getData("SELECT * FROM WORK"+group.preschoolGroupId);

        if(cursor.moveToNext()) {
            Intent intent = new Intent("DzPChildrenGallery");
            intent.putExtra("id", group.preschoolGroupId);
            startActivity(intent);
        } else {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.no_food_menu, duration);

            toast.show();
        }
    }
}

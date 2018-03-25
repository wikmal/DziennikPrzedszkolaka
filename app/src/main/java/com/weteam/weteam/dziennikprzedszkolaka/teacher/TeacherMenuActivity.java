package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;
import com.weteam.weteam.dziennikprzedszkolaka.foodMenu.MainFoodActivity;

import architecture.Parent;
import architecture.PreschoolGroup;
import architecture.Teacher;

public class TeacherMenuActivity extends StartActivity {


    Teacher user;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            userId = myIntent.getInt("id");
            user = findTeacherById(userId);
        }
    }

    public void editInformation(View view) {
        Intent intent2 = new Intent("DzPEditTeacherActivity");
        intent2.putExtra("id", user.id);
        startActivity(intent2);
    }

    public void viewGroupDetails(View view) {

        boolean check = false;

        for(PreschoolGroup i: preschoolGroups)
            if(user.groupId==i.preschoolGroupId) {
                Intent intent = new Intent("DzPViewGroupDetailsActivity");

                intent.putExtra("id", user.id);
                startActivity(intent);
                check = true;
            }
        if(check == false){
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.preschool_group_doesn_t_exist, duration);

            toast.show();
        }
    }

    public void viewPreschoolDetails(View view) {
        Intent intent = new Intent("DzPPreschoolInformationActivity");
        intent.putExtra("id", user.preschoolId);
        startActivity(intent);
    }

    public void createPreschoolGroup(View view) {
        boolean check = false;

        for(PreschoolGroup i: preschoolGroups)
            if(user.groupId==i.preschoolGroupId) {
                check = true;
            }
        if(check == false) {
            Intent intent = new Intent("DzPCreatePreschoolGroupActivity");
            intent.putExtra("id", user.id);
            startActivity(intent);
        }
        else{
            int duration = Toast.LENGTH_SHORT;
            Toast toast;
            Context context = getApplicationContext();
            toast = Toast.makeText(context, R.string.preschool_group_already_exists, duration);
            toast.show();
        }
    }

    public void deletePreschoolGroup(View view) {
        PreschoolGroup group = findGroupById(user.groupId);
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        Context context = getApplicationContext();

        boolean check = false;

        for(PreschoolGroup i: preschoolGroups)
            if(user.groupId==i.preschoolGroupId) {
                check = true; //istnieje taka grupa
            }
        if(check == true) {
            try {
                for(Parent p:group.parents){
                    users.remove(p);
                    group.parents.remove(p);
                }
                preschoolGroups.remove(group);
                user.preschoolId = 0;
                saveAll();
                toast = Toast.makeText(context, R.string.preschool_group_deleted, duration);
            } catch (Exception e) {
                toast = Toast.makeText(context, R.string.preschool_group_not_deleted, duration);
            }
        }else
            toast = Toast.makeText(context, R.string.no_preschool_group_to_delete, duration);

        toast.show();
    }

    public void viewFoodMenu(View view) {
        Cursor cursor= MainFoodActivity.sqLiteHelper.getData("SELECT * FROM FOOD"+user.preschoolId);

        if(cursor.moveToNext()) {
            Intent intent = new Intent("DzPFoodGallery");
            intent.putExtra("id", user.preschoolId);
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

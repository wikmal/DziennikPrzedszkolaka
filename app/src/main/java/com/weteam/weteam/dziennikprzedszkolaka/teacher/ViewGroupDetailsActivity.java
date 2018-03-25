package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;
import java.util.List;

import architecture.Parent;
import architecture.PreschoolGroup;
import architecture.Teacher;

public class ViewGroupDetailsActivity extends StartActivity {


    Teacher user;
    int userId;
    PreschoolGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_view_group_details);
            Bundle myIntent = getIntent().getExtras();

            if(myIntent !=null){

            userId = myIntent.getInt("id");
            user = findTeacherById(userId);
            group = findGroupById(user.groupId);
        }
        EditText name = (EditText)findViewById(R.id.enterName);
        name.setText(group.name);
    }


    public void addNewParent(View view) {
        PreschoolGroup group = findGroupById(user.groupId);

        Intent intent = new Intent("DzPCreateParentActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void viewMembers(View view) {
        Intent intent = new Intent("DzPViewParentActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void editNameOfPreschoolGroup(View view) {
        EditText editText = (EditText)findViewById(R.id.enterName);
        String name = editText.getText().toString();

        if(checkEmptyField(name, editText)) {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                PreschoolGroup group = findGroupById(user.groupId);
                group.name = name;
                toast = Toast.makeText(context, R.string.success, duration);
                savePreschoolGroups();

            }catch (Exception e){
                toast = Toast.makeText(context, R.string.failure, duration);
            }
            toast.show();
        }
    }


    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }

    public void chooseEditParent(View view) {
        final PreschoolGroup group = findGroupById(user.groupId);
        List<String> listItems = new ArrayList<String>();

        for(Parent i:group.parents){
            listItems.add(i.name);
        }
        CharSequence[] parentList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_parent);
        builder.setItems(parentList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("DzPEditParent2Activity");

                intent.putExtra("id", group.parents.get(i).id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void announcement(View view) {
        Intent intent = new Intent("DzPAddAnnouncementActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void viewAnnouncements(View view) {
        Intent intent = new Intent("DzPViewAnnouncementsActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void addPayment(View view) {
        Intent intent = new Intent("DzPAddPaymentActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void viewPayment(View view) {
        final PreschoolGroup group = findGroupById(user.groupId);
        List<String> listItems = new ArrayList<String>();

        for(Parent i:group.parents){
            listItems.add(i.name);
        }
        CharSequence[] parentList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_parent);
        builder.setItems(parentList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("DzPViewPaymentsActivity");
                intent.putExtra("id", group.parents.get(i).id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void changePayment(View view) {
        final PreschoolGroup group = findGroupById(user.groupId);
        List<String> listItems = new ArrayList<String>();

        for(Parent i:group.parents){
            listItems.add(i.name);
        }
        CharSequence[] parentList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_parent);
        builder.setItems(parentList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("DzPChangePaymentsActivity");
                intent.putExtra("id", group.parents.get(i).id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void chooseDeleteParent(View view) {
        final PreschoolGroup group = findGroupById(user.groupId);
        List<String> listItems = new ArrayList<String>();

        for(Parent i:group.parents){
            listItems.add(i.name);
        }
        CharSequence[] parentList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_parent);
        builder.setItems(parentList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                Context context = getApplicationContext();
                try {
                    Parent parent = findParentById(group.parents.get(i).id);
                    group.parents.remove(parent);
                    users.remove(parent);
                    toast = Toast.makeText(context, R.string.parent_deleted, duration);
                    saveAll();
                }catch (Exception e){
                    toast = Toast.makeText(context, R.string.parent_not_deleted, duration);
                }
                toast.show();
            }
        });
        builder.create();
        builder.show();
    }

    public void showParentsNotes(View view) {

        final PreschoolGroup group = findGroupById(user.groupId);
        List<String> listItems = new ArrayList<String>();

        for(Parent i:group.parents){
            listItems.add(i.name);
        }
        CharSequence[] parentList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_parent);
        builder.setItems(parentList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("DzPParentsNotesActivity");
                intent.putExtra("id", group.parents.get(i).id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();

    }

    public void Calendar(View view) {
        Intent intent = new Intent("DzPCalendarActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void schedule(View view) {
        Intent intent = new Intent("DzPScheduleActivity");
        intent.putExtra("id", group.preschoolGroupId);
        startActivity(intent);
    }

    public void childWork(View view) {
        Intent intent = new Intent("DzPMainChildrenActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }
}

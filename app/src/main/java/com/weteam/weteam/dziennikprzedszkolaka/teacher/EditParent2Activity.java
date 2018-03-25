package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Parent;
import architecture.Teacher;
import architecture.User;

public class EditParent2Activity extends StartActivity {

    Parent user;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parent2);
        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null){

            userId = myIntent.getInt("id");
            user = findParentById(userId);
        }

        EditText editText = (EditText)findViewById(R.id.eLogin);
        editText.setText(user.login);
        editText = (EditText)findViewById(R.id.eName);
        editText.setText(user.name);
     }

    public void changeLogin(View view) {
        if(checkLogin()) {

            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                EditText editText = (EditText) findViewById(R.id.eLogin);
                user.login = editText.getText().toString();
                toast = Toast.makeText(context, R.string.success, duration);
                saveAll();
            }
            catch (Exception e){
                toast = Toast.makeText(context, R.string.failure, duration);
            }
            toast.show();
        }
    }



    public void changeName(View view) {

        EditText editText = (EditText) findViewById(R.id.eName);
        String name = editText.getText().toString();
        if (checkEmptyField(name, editText)) {

            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                user.name = name;
                toast = Toast.makeText(context, R.string.success, duration);
                saveAll();
            } catch (Exception e) {
                toast = Toast.makeText(context, R.string.failure, duration);
            }
            toast.show();
        }
    }

    boolean checkLogin(){

        EditText editText = (EditText) findViewById(R.id.eLogin);
        String login = editText.getText().toString();

        if(login.equals("")){
            editText.setError(getString(R.string.error_null_login));
            return false;
        }

        for(User i: users)
            if(login.equals(i.login)) {
                editText.setError(getString(R.string.error_the_same_login));
                return false;
            }
        return true;
    }

    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }
}

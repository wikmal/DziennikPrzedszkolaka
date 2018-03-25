package com.weteam.weteam.dziennikprzedszkolaka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import architecture.Preschool;
import architecture.User;

public class CreatePreschoolActivity extends StartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_preschool);

        if (Build.VERSION.SDK_INT >= 11) {
            android.support.v7.app.ActionBar bar = getSupportActionBar();
            bar.hide();
        }
    }

    //--------------------------------------------------------------------------------------------------

    //dodac sprawdzanie unikalnego id
    public void addPreschool(String login, int password, String name, String location, String openingHours, int preschoolId, String phone){
        Preschool newPreschool = new Preschool(login, password, name, location, openingHours, preschoolId, phone);
        preschools.add(newPreschool);
        users.add(newPreschool);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean confirmPassword(){

        EditText editText;
        String password, password2;

        editText = (EditText) findViewById(R.id.password1);
        password = editText.getText().toString();

        if(password.length() >= 5){

            editText = (EditText) findViewById(R.id.password2);
            password2 = editText.getText().toString();


            if(!password.equals(password2)){
                editText.setError(getString(R.string.error_not_the_same_password));
                return false;
            }

            return true;
        }
        editText.setError(getString(R.string.error_password_too_short2));

        return false;
    }
    //dodac sprawdzanie czy wszystkie pola sa wypelnione
    public void createPreschool(View view) {


        if(checkLogin() && confirmPassword()) {
            String login, password, name, location, openingHours, phone;
            int preschoolId;

            EditText editText = (EditText) findViewById(R.id.login);
            login = editText.getText().toString();

            editText = (EditText) findViewById(R.id.password1);
            password = editText.getText().toString();

            editText = (EditText) findViewById(R.id.name);
            name = editText.getText().toString();

            if (checkEmptyField(name, editText)) {

                editText = (EditText) findViewById(R.id.address);
                location = editText.getText().toString();

                editText = (EditText) findViewById(R.id.openingHours);
                openingHours = editText.getText().toString();

                editText = (EditText) findViewById(R.id.phone);
                phone = editText.getText().toString();

                Toast toast;
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                try {
                    preschoolId = findNewId();
                    addPreschool(login, password.hashCode(), name, location, openingHours, preschoolId, phone);
                    toast = Toast.makeText(context, R.string.account_created, duration);
                    saveAll();
                }
                catch (Exception e) {
                    toast = Toast.makeText(context, R.string.account_not_created, duration);

                }
                toast.show();



                Intent intent = new Intent("DzPMainActivity");
                startActivity(intent);
            }
        }
    }

    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }

    boolean checkLogin(){

        EditText editText = (EditText) findViewById(R.id.login);
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
}

package com.weteam.weteam.dziennikprzedszkolaka;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.weteam.weteam.dziennikprzedszkolaka.preschool.PreschoolMenuActivity;

import architecture.Parent;
import architecture.Preschool;
import architecture.Teacher;
import architecture.User;

public class SignInActivity extends StartActivity {

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //chowa pasek z nazwa aplikacji, polozona u gory ekranu
        if (Build.VERSION.SDK_INT >= 11) {
            android.support.v7.app.ActionBar bar = getSupportActionBar();
            bar.hide();
        }
    }

    public User logIn(String login, int password, EditText eLogin, EditText ePassword){

        for (User i: users){
            if(i.login.equals(login)) {
                if (i.password == password)
                    return i;
                else {
                    ePassword.setError(getString(R.string.error_incorrect_password));
                    ePassword.setText("");
                    return null;
                }
            }
        }
        eLogin.setError(getString(R.string.error_incorrect_login));
        ePassword.setText("");
        return null;
    }


    public void signIn(View view) {

        EditText editText = (EditText) findViewById(R.id.login);
        String login = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.password);
        String password = editText2.getText().toString();


        user = logIn(login, password.hashCode(), editText, editText2);


        if(user!=null){


            //if(user.getClass().isAssignableFrom(Preschool.class)){
            if(user.authorisation == 2){
                Intent intent = new Intent(this, PreschoolMenuActivity.class);
                intent.putExtra("id", user.id);
                startActivity(intent);
            }
            //if(user.getClass().isAssignableFrom(Teacher.class)){
            if(user.authorisation == 1){
                Intent intent = new Intent("DzPTeacherMenuActivity");
                intent.putExtra("id", user.id);
                startActivity(intent);
            }
            //if(user.getClass().isAssignableFrom(Parent.class)){
            if(user.authorisation == 0){
                Intent intent = new Intent("DzPParentMenuActivity");
                intent.putExtra("id", user.id);
                startActivity(intent);
            }
            editText.setText("");
            editText2.setText("");
        }
    }
}

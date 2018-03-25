package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Parent;
import architecture.PreschoolGroup;
import architecture.User;

public class CreateParentActivity extends StartActivity {

    PreschoolGroup group;
    int groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parent);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }
    }

    public void createParent(View view) {
        if(checkLogin() && confirmPassword()) {
            String login, password, name, location, openingHours;
            int parentId;
            String birthDate;

            EditText editText = (EditText) findViewById(R.id.login);
            login = editText.getText().toString();

            editText = (EditText) findViewById(R.id.password1);
            password = editText.getText().toString();

            editText = (EditText) findViewById(R.id.name);
            name = editText.getText().toString();

            if (checkEmptyField(name, editText)) {

                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                Context context = getApplicationContext();


                try {
                    parentId = findNewId();
                    Parent newParent = new Parent(login, password.hashCode(), name, group.preschoolGroupId, parentId);
                    users.add(newParent);
                    group.parents.add(newParent);

                    editText = (EditText) findViewById(R.id.login);
                    editText.setText("");
                    editText = (EditText) findViewById(R.id.password1);
                    editText.setText("");
                    editText = (EditText) findViewById(R.id.password2);
                    editText.setText("");
                    editText = (EditText) findViewById(R.id.name);
                    editText.setText("");


                    toast = Toast.makeText(context, R.string.account_created, duration);
                    saveAll();
                }
                catch (Exception e){
                    toast = Toast.makeText(context, R.string.account_not_created, duration);
                }
                toast.show();
            }
        }
    }

    public boolean confirmPassword(){

        EditText editText;
        String password, password2;

        editText = (EditText) findViewById(R.id.password1);
        password = editText.getText().toString();

        if(password.length()>=5) {

            editText = (EditText) findViewById(R.id.password2);
            password2 = editText.getText().toString();


            if (!password.equals(password2)) {
                editText.setError(getString(R.string.error_not_the_same_password));
                return false;
            }

            return true;
        }
        editText.setError(getString(R.string.error_password_too_short2));

        return false;
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

    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }

}

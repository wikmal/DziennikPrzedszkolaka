package com.weteam.weteam.dziennikprzedszkolaka.preschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.MainActivity;
import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Guide;
import architecture.Preschool;

public class AddGuideActivity extends StartActivity {

    Preschool user;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guide);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            userId = myIntent.getInt("id");
            user = findPreschoolById(userId);
        }
    }



    public void createGuide(View view) {
        String title, description, link;
        int id;

        EditText editText = (EditText)findViewById(R.id.title);
        title = editText.getText().toString();

        if(checkEmptyField(title, editText)) {

            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            editText = (EditText)findViewById(R.id.description);
            description = editText.getText().toString();

            editText = (EditText)findViewById(R.id.link);
            link = editText.getText().toString();

            try {
                id = findNewGuideId();

                user.guideBook.add(new Guide(id, title, description, link));

                toast = Toast.makeText(context, R.string.guide_created, duration);
                saveAll();

                editText = (EditText) findViewById(R.id.title);
                editText.setText("");
                editText = (EditText) findViewById(R.id.description);
                editText.setText("");
                editText = (EditText) findViewById(R.id.link);
                editText.setText("");

            }
            catch (Exception e){

                toast = Toast.makeText(context, R.string.guide_not_created, duration);
            }

            toast.show();

            Intent intent = new Intent("DzPGuidesActivity");
            intent.putExtra("id", user.id);
            startActivity(intent);
        }
    }

    private int findNewGuideId() {
        int id = 0;
        for(Guide i: user.guideBook){
            id = i.id;
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

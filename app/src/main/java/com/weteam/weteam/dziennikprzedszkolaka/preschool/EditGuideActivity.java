package com.weteam.weteam.dziennikprzedszkolaka.preschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Guide;
import architecture.Preschool;

public class EditGuideActivity extends StartActivity {


    Preschool preschool;
    Guide guide;
    int userId, guideId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guide);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            userId = myIntent.getInt("id");
            guideId = myIntent.getInt("guideId");
            preschool = findPreschoolById(userId);
            guide = findGuideById(preschool, guideId);
        }

        EditText editText = (EditText)findViewById(R.id.title);
        editText.setText(guide.title);
        editText = (EditText)findViewById(R.id.description);
        editText.setText(guide.description);
        editText = (EditText)findViewById(R.id.link);
        editText.setText(guide.link);
    }

    private Guide findGuideById(Preschool preschool, int id) {
        for(Guide i: preschool.guideBook){
            if(i.id == id)
                return i;
        }
        return null;
    }

    public void editGuide(View view) {String title, description, link;

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
                preschool.guideBook.remove(guide);
                guide = new Guide(guideId, title, description, link);
                preschool.guideBook.add(guide);

                toast = Toast.makeText(context, R.string.guide_edited, duration);
                saveAll();
            }
            catch (Exception e){
                toast = Toast.makeText(context, R.string.guide_not_edited, duration);
            }
            toast.show();

            Intent intent = new Intent("DzPGuidesActivity");
            intent.putExtra("id", preschool.id);
            startActivity(intent);
        }
    }

    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }
}

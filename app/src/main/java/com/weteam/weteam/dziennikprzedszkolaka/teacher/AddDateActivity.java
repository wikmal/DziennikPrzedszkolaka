package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Calendar;
import architecture.PreschoolGroup;

public class AddDateActivity extends StartActivity {

    int groupId;
    PreschoolGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null){
            groupId = myIntent.getInt("id");
            group = findGroupById(groupId);
        }
    }

    public void addNewDate(View view) {
        int day, month, year;
        String name;

        EditText editText = (EditText)findViewById(R.id.name);
        name = editText.getText().toString();

        if(checkEmptyField(name, editText)) {

            editText = (EditText) findViewById(R.id.day);
            day = Integer.parseInt(editText.getText().toString());

            if (checkEmptyField(String.valueOf(day), editText) && day <=31 && day>= 1) {

                editText = (EditText) findViewById(R.id.month);
                month = Integer.parseInt(editText.getText().toString());

                if (checkEmptyField(String.valueOf(month), editText) && month <=12 && month >= 1) {

                    editText = (EditText) findViewById(R.id.year);
                    year = Integer.parseInt(editText.getText().toString());

                    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

                    if (checkEmptyField(String.valueOf(year), editText) && year <= currentYear - 3 && year >= currentYear - 8){

                        Toast toast;
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;

                        try {

                            group.calendar.add(new Calendar(year, month,day,name));
                            toast = Toast.makeText(context, R.string.date_added, duration);
                            saveAll();

                            editText = (EditText) findViewById(R.id.name);
                            editText.setText("");
                            editText = (EditText) findViewById(R.id.day);
                            editText.setText("");
                            editText = (EditText) findViewById(R.id.month);
                            editText.setText("");
                            editText = (EditText) findViewById(R.id.year);
                            editText.setText("");

                        } catch (Exception e) {
                            toast = Toast.makeText(context, R.string.date_not_added, duration);
                        }
                        toast.show();
                    }
                    else
                        editText.setError(getString(R.string.error_incorrect_year));

                }
                else
                    editText.setError(getString(R.string.error_incorrect_month));

            }
            else
                editText.setError(getString(R.string.error_incorrect_day));

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

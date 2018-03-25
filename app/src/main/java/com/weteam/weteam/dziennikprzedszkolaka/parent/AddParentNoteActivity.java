package com.weteam.weteam.dziennikprzedszkolaka.parent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import architecture.Message;
import architecture.Parent;

public class AddParentNoteActivity extends StartActivity {

    Parent parent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent_note);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent!=null) {
            id = myIntent.getInt("id");
            parent = findParentById(id);
        }
    }

    public void sendMessage(View view) {
        String message;

        EditText editText = (EditText)findViewById(R.id.message);
        message = editText.getText().toString();

        if(checkEmptyField(message, editText)){

            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                int messageId = findNewMessageId();
                Message newMessage = new Message(messageId, message, date);
                parent.messages.add(newMessage);

                editText = (EditText) findViewById(R.id.message);
                editText.setText("");

                toast = Toast.makeText(context, R.string.message_sent, duration);
                saveAll();
            }catch (Exception e){
                toast = Toast.makeText(context, R.string.message_not_sent, duration);
            }
           toast.show();

        }
    }

    private int findNewMessageId() {
        int id = 0;
        for(Message i: parent.messages){
            id = i.id;
        }
        id++;
        return id;    }

    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }
}

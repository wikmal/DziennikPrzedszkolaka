package com.weteam.weteam.dziennikprzedszkolaka;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;
import java.util.List;

import architecture.Parent;
import architecture.Payments;

public class ViewPaymentsActivity extends StartActivity {

    Parent parent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payments);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null) {
            id = myIntent.getInt("id");
            parent = findParentById(id);
        }


        List<String> listItems = new ArrayList<String>();

        for(Payments i:parent.payments){
            listItems.add(i.description);
        }
        CharSequence[] paymentList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_payment);
        builder.setItems(paymentList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = (TextView) findViewById(R.id.description);
                textView.setText(parent.payments.get(i).description);
                textView = (TextView) findViewById(R.id.amount);
                textView.setText(String.valueOf(parent.payments.get(i).amount));
                textView = (TextView) findViewById(R.id.status);
                if(String.valueOf(parent.payments.get(i).status) == "ZAPŁACONE")
                    textView.setText(R.string.paid);
                else
                    textView.setText(R.string.not_paid);
            }
        });
        builder.create();
        builder.show();
    }
}
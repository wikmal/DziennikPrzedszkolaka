package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;
import java.util.List;

import architecture.Parent;
import architecture.Payments;
import architecture.PreschoolGroup;
import architecture.Teacher;

public class ChangePaymentsActivity extends StartActivity {

    Parent parent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_payments);

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
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                Context context = getApplicationContext();

                if(parent.payments.get(i).status == Payments.Status.NIEZAPŁACONE){
                    parent.payments.get(i).status = Payments.Status.ZAPŁACONE;
                    toast = Toast.makeText(context, R.string.status_changed_to_paid, duration);
                }
                else {
                    parent.payments.get(i).status = Payments.Status.NIEZAPŁACONE;
                    toast = Toast.makeText(context, R.string.status_changed_to_not_paid, duration);
                }
                saveAll();

                toast.show();
                PreschoolGroup group = findGroupById(parent.preschoolGroupId);

                Intent intent = new Intent("DzPViewGroupDetailsActivity");
                intent.putExtra("id", group.teacherId);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }
}

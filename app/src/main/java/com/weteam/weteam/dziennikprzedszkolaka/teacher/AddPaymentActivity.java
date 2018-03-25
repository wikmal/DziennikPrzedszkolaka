package com.weteam.weteam.dziennikprzedszkolaka.teacher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Parent;
import architecture.Payments;
import architecture.PreschoolGroup;

public class AddPaymentActivity extends StartActivity {

    PreschoolGroup group;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null){
            id = myIntent.getInt("id");
            group = findGroupById(id);
        }

    }

    public void createPayment(View view) {
            String description;
            Float amount;

            EditText editText = (EditText) findViewById(R.id.description);
            description = editText.getText().toString();



            if (checkEmptyField(description, editText)) {

                editText = (EditText) findViewById(R.id.amount);
                amount = Float.parseFloat(editText.getText().toString());

                if(checkEmptyField(amount.toString(), editText)) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;
                    Context context = getApplicationContext();

                    try {
                        int id = findNewPaymentId();
                        Payments payment = new Payments(id, description, amount);
                        for(Parent p : group.parents){
                            p.payments.add(payment);
                        }

                        editText = (EditText) findViewById(R.id.description);
                        editText.setText("");
                        editText = (EditText) findViewById(R.id.amount);
                        editText.setText("");


                        toast = Toast.makeText(context, R.string.payment_created, duration);
                        saveAll();
                    } catch (Exception e) {
                        toast = Toast.makeText(context, R.string.payment_not_created, duration);
                    }
                    toast.show();
                }
            }
        }

    private int findNewPaymentId() {
        int id = 0;
        for(Payments i : group.parents.get(0).payments){
            if(id<i.id)
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

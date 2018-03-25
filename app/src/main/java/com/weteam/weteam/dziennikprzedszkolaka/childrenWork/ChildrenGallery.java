package com.weteam.weteam.dziennikprzedszkolaka.childrenWork;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;

/**
 * Created by User on 2018-01-14.
 */

public class ChildrenGallery extends StartActivity {

    GridView gridView;
    ArrayList<Work> list;
    WorkListAdapter adaper=null;
    int groupId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_list_activity);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            groupId = myIntent.getInt("id");
        }


        gridView=(GridView) findViewById(R.id.gridView);
        list=new ArrayList<>();
        adaper=new WorkListAdapter(this,R.layout.work_items,list);
        gridView.setAdapter(adaper);


        //get all data from sqlLite
        Cursor cursor= MainChildrenActivity.sqLiteHelper.getData("SELECT * FROM WORK"+groupId);

        list.clear();

        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            //String price=cursor.getString(2);
            byte[] image = cursor.getBlob(2);

            list.add(new Work(id, name, image));
        }

        adaper.notifyDataSetChanged();

    }
}

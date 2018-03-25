package com.weteam.weteam.dziennikprzedszkolaka.foodMenu;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;

/**
 * Created by User on 2018-01-14.
 */

public class FoodGallery extends StartActivity {

    GridView gridView;
    ArrayList<Food> list;
    FoodListAdapter adaper=null;
    int preschoolId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            preschoolId = myIntent.getInt("id");
        }


        gridView=(GridView) findViewById(R.id.gridView);
        list=new ArrayList<>();
        adaper=new FoodListAdapter(this,R.layout.food_items,list);
        gridView.setAdapter(adaper);


        //get all data from sqlLite
        Cursor cursor= MainFoodActivity.sqLiteHelper.getData("SELECT * FROM FOOD"+preschoolId);

        list.clear();

        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            //String price=cursor.getString(2);
            byte[] image = cursor.getBlob(2);

            list.add(new Food(id, name, image));
        }

        adaper.notifyDataSetChanged();

    }
}

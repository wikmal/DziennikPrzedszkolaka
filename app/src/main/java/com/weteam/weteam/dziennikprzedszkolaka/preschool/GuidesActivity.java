package com.weteam.weteam.dziennikprzedszkolaka.preschool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.util.ArrayList;
import java.util.List;

import architecture.Guide;
import architecture.Preschool;

public class GuidesActivity extends StartActivity {

    Preschool user;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent !=null){
            userId = myIntent.getInt("id");
            user = findPreschoolById(userId);
        }
    }


    public void showGuides(View view) {
        loadPreschools();
        List<String> listItems = new ArrayList<String>();
        final ArrayList <Guide> guidesOfPreschool = new ArrayList<>();

        for(Guide i: user.guideBook){

                listItems.add(i.title);
                guidesOfPreschool.add(i);

        }
        CharSequence[] guidesList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_guide_to_show);
        builder.setItems(guidesList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent("DzPViewGuidesActivity");
                intent.putExtra("id", user.id);
                intent.putExtra("guideId", guidesOfPreschool.get(i).id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void addGuides(View view) {
        Intent intent = new Intent("DzPAddGuideActivity");
        intent.putExtra("id", user.id);
        startActivity(intent);
    }

    public void editGuide(View view) {
        List<String> listItems = new ArrayList<String>();
        final ArrayList <Guide> guidesOfPreschool = new ArrayList<>();

        for(Guide i: user.guideBook){

            listItems.add(i.title);
            guidesOfPreschool.add(i);

        }
        CharSequence[] guidesList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_guide_to_edit);
        builder.setItems(guidesList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent("DzPEditGuideActivity");
                intent.putExtra("guideId", guidesOfPreschool.get(i).id);
                intent.putExtra("id", user.id);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void deleteGuide(View view) {
        List<String> listItems = new ArrayList<String>();
        final ArrayList <Guide> guidesOfPreschool = new ArrayList<>();

        for(Guide i: user.guideBook){

            listItems.add(i.title);
            guidesOfPreschool.add(i);

        }
        CharSequence[] guidesList = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_guide_to_delete);
        builder.setItems(guidesList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Guide guide = findGuideById(user, guidesOfPreschool.get(i).id);
                user.guideBook.remove(guide);
            }
        });
        builder.create();
        builder.show();
    }

    private Guide findGuideById(Preschool preschool, int id) {
        for(Guide i: preschool.guideBook){
            if(i.id == id)
                return i;
        }
        return null;
    }
}

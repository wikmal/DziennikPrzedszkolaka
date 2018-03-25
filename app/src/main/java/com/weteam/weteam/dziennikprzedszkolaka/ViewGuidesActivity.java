package com.weteam.weteam.dziennikprzedszkolaka;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import architecture.Guide;
import architecture.Preschool;

public class ViewGuidesActivity extends StartActivity {

    Guide guide;
    Preschool preschool;
    int userId;
    int guideId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guides);

        Bundle myIntent = getIntent().getExtras();

        if(myIntent !=null) {
            userId = myIntent.getInt("id");
            guideId = myIntent.getInt("guideId");
            preschool = findPreschoolById(userId);
            guide = findGuideById(preschool, guideId);
        }



        TextView textView = (TextView)findViewById(R.id.title);
        textView.setText(guide.title);
        textView = (TextView)findViewById(R.id.description);
        textView.setText(guide.description);
    }

    private Guide findGuideById(Preschool preschool, int id) {
        for(Guide i: preschool.guideBook){
            if(i.id == id)
                return i;
        }
        return null;
    }


    public void useHyperlink(View view) {
        Toast toast;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if (guide.link != null && guide.link != "") {
            if(URLUtil.isValidUrl(guide.link)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(guide.link));
                startActivity(intent);
            }
            else {
                toast = Toast.makeText(context, R.string.url_is_invalid, duration);
                toast.show();
            }
        }
        else {
            toast = Toast.makeText(context, R.string.no_link_to_this_guide, duration);
            toast.show();
        }
    }
}

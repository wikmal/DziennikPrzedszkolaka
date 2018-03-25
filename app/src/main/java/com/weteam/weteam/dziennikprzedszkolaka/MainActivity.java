package com.weteam.weteam.dziennikprzedszkolaka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.weteam.weteam.dziennikprzedszkolaka.R;

import java.util.Locale;

public class MainActivity extends StartActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 11) {
            ActionBar bar = getSupportActionBar();
            bar.hide();
        }

        ////DO ZMIANY JEZYKA
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent("DzPSignInActivity");
        startActivity(intent);
    }

    public void goToCreatePreschool(View view) {
        Intent intent = new Intent("DzPCreatePreschoolActivity");
        startActivity(intent);
    }

    public void changeLanguagePL(View view) {

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "pl").commit();
        setLangRecreate("pl");
        return;
    }

    public void changeLanguageGB(View view) {

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
        setLangRecreate("en");
        return;
    }

    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}

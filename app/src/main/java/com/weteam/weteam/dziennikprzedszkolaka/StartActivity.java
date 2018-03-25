package com.weteam.weteam.dziennikprzedszkolaka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import architecture.Parent;
import architecture.Preschool;
import architecture.PreschoolGroup;
import architecture.Teacher;
import architecture.User;

import static java.lang.Thread.sleep;

public class StartActivity extends AppCompatActivity {

    static public ArrayList<Preschool> preschools;// = new ArrayList<>();
    static public ArrayList<User> users;// = new ArrayList<>();
    static public ArrayList<Teacher> teachers;// = new ArrayList<>();
    static public ArrayList<PreschoolGroup> preschoolGroups;// = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //chowa pasek z nazwa aplikacji, polozona u gory ekranu
        if (Build.VERSION.SDK_INT >= 11) {
            android.support.v7.app.ActionBar bar = getSupportActionBar();
            bar.hide();
        }
        loadUsers();
        loadPreschools();
        loadTeachers();
        loadPreschoolGroups();

        //test();
    }

    public void saveAll(){
        savePreschoolGroups();
        savePreschools();
        saveTeachers();
        saveUsers();
    }

    public void loadPreschools() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("preschoolsList", null);
        Type type = new TypeToken<ArrayList<Preschool>>(){}.getType();
        preschools = gson.fromJson(json, type);

        if(preschools == null){
            preschools = new ArrayList<>();
        }
    }

    public void loadUsers() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences3", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("usersList", null);
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(json, type);

        if(users == null){
            users = new ArrayList<>();
        }
    }

    public void loadTeachers() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("teachersList", null);
        Type type = new TypeToken<ArrayList<Teacher>>(){}.getType();
        teachers = gson.fromJson(json, type);

        if(teachers == null){
            teachers = new ArrayList<>();
        }
    }

    public void loadPreschoolGroups() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences4", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("preschoolGroupsList", null);
        Type type = new TypeToken<ArrayList<PreschoolGroup>>(){}.getType();
        preschoolGroups = gson.fromJson(json, type);

        if(preschoolGroups == null){
            preschoolGroups = new ArrayList<>();
        }
    }

    public void savePreschools() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(preschools);
        editor.putString("preschoolsList", json);
        //editor.apply();
        editor.commit();
    }

    public void saveUsers() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("usersList", json);
        //editor.apply();
        editor.commit();
    }

    public void saveTeachers() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(teachers);
        editor.putString("teachersList", json);
        //editor.apply();
        editor.commit();
    }

    public void savePreschoolGroups() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences4", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(preschoolGroups);
        editor.putString("preschoolGroupsList", json);
        //editor.apply();
        editor.commit();
    }

    public void test(){

    }

    public void goToMenu(View view) {
        Intent intent = new Intent("DzPMainActivity");
        startActivity(intent);
    }

    public Parent findParentById(int parentId) {
        for(PreschoolGroup g: preschoolGroups){
            for(Parent p: g.parents){
                if(p.id == parentId){
                    return p;
                }
            }
        }
        Log.e("BLAD","Nie ma takiego rodzica");
        return null;
    }

    public Teacher findTeacherById(int teacherId) {
        for(Teacher t: teachers){
            if(t.id==teacherId)
                return t;
        }
        Log.e("BLAD", "Nie ma takiego nauczyciela");
        return null;
    }

    public Preschool findPreschoolById(int preschoolId) {
        for(Preschool p: preschools){
            if(p.id==preschoolId)
                return p;
        }
        Log.e("BLAD", "Nie ma takiego przedszkola");
        return null;
    }

    public PreschoolGroup findGroupById(int groupId) {
        for(PreschoolGroup pg: preschoolGroups){
            if(pg.preschoolGroupId==groupId)
                return pg;
        }
        Log.e("BLAD", "Nie ma takiej grupy");
        return null;
    }

    public int findNewId() {
        int id = 0;
        for(User i : users){
            if(id<i.id) {
                id = i.id;
            }
        }
        id++;
        return id;
    }

}

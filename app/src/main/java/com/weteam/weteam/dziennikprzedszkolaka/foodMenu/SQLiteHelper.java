package com.weteam.weteam.dziennikprzedszkolaka.foodMenu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by User on 2018-01-14.
 */

public class SQLiteHelper extends SQLiteOpenHelper{

    int preschoolId;

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, int preschoolId) {
        super(context, name, factory, version);
        this.preschoolId = preschoolId;
    }

    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql="INSERT INTO FOOD"+ preschoolId +" VALUES (NULL, ?,?)";


        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        //statement.bindString(2,price);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

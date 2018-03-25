package com.weteam.weteam.dziennikprzedszkolaka.childrenWork;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.StartActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import architecture.Teacher;

public class MainChildrenActivity extends StartActivity {


    Teacher user;
    int userId;

    EditText edtName, edtPrice;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView2;

    final int REQUEST_CODE_GALLERY=999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent!=null) {
            userId = myIntent.getInt("id");
            user = findTeacherById(userId);
        }

        init();

        sqLiteHelper =  new SQLiteHelper(this,"WorksDB.sqlite",null,1, user.groupId);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS WORK"+user.groupId+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, image BLOG)");

        }

    private byte[] imageView2ToByte(ImageView image) {
        Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte [] byteArray=stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(),R.string.you_dont_have_permission_to_access_a_file_location, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null){
           Uri uri=data.getData();

           try {
               InputStream inputStream=getContentResolver().openInputStream(uri);

               Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
               imageView2.setImageBitmap(bitmap);

           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
       }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtName=(EditText) findViewById(R.id.edtName);
        btnChoose=(Button) findViewById(R.id.btnChoose);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnList=(Button) findViewById(R.id.btnList);
        imageView2=(ImageView) findViewById(R.id.imageView2);
    }


    public void chooseWork(View view) {

        ActivityCompat.requestPermissions(
                MainChildrenActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }

    public void addWork(View view) {
        try{
            sqLiteHelper.insertData(
                    edtName.getText().toString().trim(),
                    //edtPrice.getText().toString().trim(),
                    imageView2ToByte(imageView2)
            );
            Toast.makeText(getApplicationContext(),R.string.added_successfully,Toast.LENGTH_SHORT).show();
            edtName.setText("");
           // edtPrice.setText("");
            imageView2.setImageResource(R.mipmap.ic_launcher);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ViewWorks(View view) {
        Cursor cursor= MainChildrenActivity.sqLiteHelper.getData("SELECT * FROM WORK"+user.groupId);

        if(cursor.moveToNext()) {
            Intent intent = new Intent("DzPChildrenGallery");
            intent.putExtra("id", user.groupId);
            startActivity(intent);
        } else {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, R.string.no_food_menu, duration);

            toast.show();
        }
    }
}

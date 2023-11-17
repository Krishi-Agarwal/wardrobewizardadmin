package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;

public class Profile extends AppCompatActivity {
    MaterialTextView username,email,age,address,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        age=findViewById(R.id.age);
        address=findViewById(R.id.address);
        phonenumber=findViewById(R.id.phone);
        DbHelper dbh= new DbHelper(Profile.this);
        SQLiteDatabase db=dbh.getReadableDatabase();
        Cursor c= db.rawQuery("Select * from "+dbh.TABLE_USERS+" where id=?",new String[]{String.valueOf(loginpage.id)});
        while (c.moveToNext())
        {
            username.setText(c.getString(1));
            phonenumber.setText(c.getString(3));
            email.setText(c.getString(4));
            address.setText(c.getString(5));
            age.setText(c.getString(6));
        }
        db.close();
    }
}
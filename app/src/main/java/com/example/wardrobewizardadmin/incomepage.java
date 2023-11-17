package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class incomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomepage);
        ArrayList<String> empdetails =new ArrayList<>();
        empdetails.add("ID\t\tAMOUNT\t\tDATE");
        ListView lv=findViewById(R.id.dataListView);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select id,amount,date from income",null);
        while (cursor.moveToNext())
        {
            empdetails.add(cursor.getString(0)+"\t\t"+String.valueOf(cursor.getDouble(1))+"\t\t"+cursor.getString(2)+"\t\t");
        }
        ArrayAdapter<String> income = new ArrayAdapter<>(incomepage.this, android.R.layout.simple_list_item_1,empdetails);
        lv.setAdapter(income);
    }
}
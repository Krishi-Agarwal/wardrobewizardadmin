package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class userspage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userspage);
        ArrayList<String> empdetails =new ArrayList<>();
        empdetails.add("ID\t\tNAME\t\tPHONENUMBER");
        ListView lv=findViewById(R.id.dataListView);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select id,username,phonenumber from "+DbHelper.TABLE_CUSTOMERS,null);
        while (cursor.moveToNext())
        {
            empdetails.add(cursor.getString(0)+"\t\t"+cursor.getString(1)+"\t\t"+cursor.getString(2));
        }
        ArrayAdapter<String> income = new ArrayAdapter<>(userspage.this, android.R.layout.simple_list_item_1,empdetails);
        lv.setAdapter(income);
    }
}
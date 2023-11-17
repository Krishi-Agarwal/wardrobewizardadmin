package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class employees extends AppCompatActivity {
    private DbHelper dbHelper;

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        ArrayList<String> empdetails =new ArrayList<>();
        empdetails.add("ID\t\tNAME\t\tROLE\t\tSALARY");
        ListView lv=findViewById(R.id.dataListView);
        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select id,name,role,salary from "+DbHelper.TABLE_EMPLOYEES,null);
        while (cursor.moveToNext())
        {
            empdetails.add(cursor.getString(0)+"\t\t"+cursor.getString(1)+"\t\t"+cursor.getString(2)+"\t\t"+cursor.getString(3)+"\t\t");
        }
        ArrayAdapter<String> employees = new ArrayAdapter<>(com.example.wardrobewizardadmin.employees.this, android.R.layout.simple_list_item_1,empdetails);
        lv.setAdapter(employees);
    }
}
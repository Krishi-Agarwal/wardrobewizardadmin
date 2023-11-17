package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        nameTextView = findViewById(R.id.nameTextView);
        Intent abc = getIntent();
        Bundle extras = abc.getExtras();
        if (extras.containsKey("username")) {
            nameTextView.setText(extras.getString("username").toString());
        }
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Add items to the adapter
        adapter.add("Last 7 days");
        adapter.add("Current Month");
        adapter.add("Current Year");
        adapter.add("ALL");
//

// Set the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0) {
                    DbHelper db = new DbHelper(homepage.this);
                    SQLiteDatabase sql = db.getReadableDatabase();

// Query for the number of employees
                    Cursor c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_EMPLOYEES +
                            " WHERE strftime('%s', 'now') - strftime('%s', joindate) <= 7*24*3600", null);
                    TextView emp = findViewById(R.id.employes);
                    if (c.moveToFirst()) {
                        emp.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of users
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView users = findViewById(R.id.users);
                    if (c.moveToFirst()) {
                        users.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of complaints
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_COMPLAINTS +
                            " WHERE strftime('%s', 'now') - strftime('%s', date) <= 7*24*3600", null);
                    TextView complaints = findViewById(R.id.complaints);
                    if (c.moveToFirst()) {
                        complaints.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of NGOs
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_NGO, null);
                    TextView ngo = findViewById(R.id.ngo);
                    if (c.moveToFirst()) {
                        ngo.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

//                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.CLOTHES_DONATED, null);
                    TextView clothes = findViewById(R.id.clothesdonated);
//                    if (c.moveToFirst()) {
//
//                    }
                    clothes.setText("0");
//                    c.close();
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView newusers = findViewById(R.id.newusers);
                    if (c.moveToFirst()) {
                        newusers.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

                    c = sql.rawQuery("SELECT Sum(amount) FROM " + DbHelper.TABLE_INCOME +
                            " WHERE strftime('%s', 'now') - strftime('%s', date) <= 7*24*3600", null);
                    TextView income = findViewById(R.id.income);
                    if (c.moveToFirst()) {
                        income.setText(String.valueOf(c.getInt(0)));
                    } else {
                        income.setText(String.valueOf(0));
                    }
                    c.close();


                } else if (position == 1) {
                    DbHelper db = new DbHelper(homepage.this);
                    SQLiteDatabase sql = db.getReadableDatabase();

// Query for the number of employees
                    Cursor c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_EMPLOYEES +
                            " WHERE strftime('%s', 'now') - strftime('%s', joindate) <= 31*24*3600", null);
                    TextView emp = findViewById(R.id.employes);
                    if (c.moveToFirst()) {
                        emp.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of users
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView users = findViewById(R.id.users);
                    if (c.moveToFirst()) {
                        users.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of complaints
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_COMPLAINTS +
                            " WHERE strftime('%s', 'now') - strftime('%s', date) <= 31*24*3600", null);
                    TextView complaints = findViewById(R.id.complaints);
                    if (c.moveToFirst()) {
                        complaints.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of NGOs
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_NGO, null);
                    TextView ngo = findViewById(R.id.ngo);
                    if (c.moveToFirst()) {
                        ngo.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

//                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.CLOTHES_DONATED, null);
                    TextView clothes = findViewById(R.id.clothesdonated);
//                    if (c.moveToFirst()) {
//
//                    }
                    clothes.setText("0");
//                    c.close();
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView newusers = findViewById(R.id.newusers);
                    if (c.moveToFirst()) {
                        newusers.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

                    c = sql.rawQuery("SELECT Sum(amount) FROM " + DbHelper.TABLE_INCOME +
                            " WHERE strftime('%s', 'now') - strftime('%s', date) <= 31*24*3600", null);
                    TextView income = findViewById(R.id.income);
                    if (c.moveToFirst()) {
                        income.setText(String.valueOf(c.getInt(0)));
                    } else {
                        income.setText(String.valueOf(0));
                    }
                    c.close();


                } else if (position == 2) {
                    DbHelper db = new DbHelper(homepage.this);
                    SQLiteDatabase sql = db.getReadableDatabase();

// Query for the number of employees
                    Cursor c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_EMPLOYEES +
                            " WHERE strftime('%s', 'now') - strftime('%s', joindate) <= 365*24*3600", null);
                    TextView emp = findViewById(R.id.employes);
                    if (c.moveToFirst()) {
                        emp.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of users
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView users = findViewById(R.id.users);
                    if (c.moveToFirst()) {
                        users.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of complaints
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_COMPLAINTS +
                            " WHERE strftime('%s', 'now') - strftime('%s', date) <= 365*24*3600", null);
                    TextView complaints = findViewById(R.id.complaints);
                    if (c.moveToFirst()) {
                        complaints.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of NGOs
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_NGO, null);
                    TextView ngo = findViewById(R.id.ngo);
                    if (c.moveToFirst()) {
                        ngo.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

//                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.CLOTHES_DONATED, null);
                    TextView clothes = findViewById(R.id.clothesdonated);
//                    if (c.moveToFirst()) {
//
//                    }
                    clothes.setText("0");
//                    c.close();
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView newusers = findViewById(R.id.newusers);
                    if (c.moveToFirst()) {
                        newusers.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

                    c = sql.rawQuery("SELECT Sum(amount) FROM " + DbHelper.TABLE_INCOME +
                            " WHERE strftime('%s', 'now') - strftime('%s', date) <= 365*24*3600", null);
                    TextView income = findViewById(R.id.income);
                    if (c.moveToFirst()) {
                        income.setText(String.valueOf(c.getInt(0)));
                    } else {
                        income.setText(String.valueOf(0));
                    }
                    c.close();


                }
                else
                {
                    DbHelper db = new DbHelper(homepage.this);
                    SQLiteDatabase sql = db.getReadableDatabase();

// Query for the number of employees
                    Cursor c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_EMPLOYEES, null);
                    TextView emp = findViewById(R.id.employes);
                    if (c.moveToFirst()) {
                        emp.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of users
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView users = findViewById(R.id.users);
                    if (c.moveToFirst()) {
                        users.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of complaints
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_COMPLAINTS, null);
                    TextView complaints = findViewById(R.id.complaints);
                    if (c.moveToFirst()) {
                        complaints.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

// Query for the number of NGOs
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_NGO, null);
                    TextView ngo = findViewById(R.id.ngo);
                    if (c.moveToFirst()) {
                        ngo.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

//                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.CLOTHES_DONATED, null);
                    TextView clothes = findViewById(R.id.clothesdonated);
//                    if (c.moveToFirst()) {
//
//                    }
                    clothes.setText("0");
//                    c.close();
                    c = sql.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE_CUSTOMERS, null);
                    TextView newusers = findViewById(R.id.newusers);
                    if (c.moveToFirst()) {
                        newusers.setText(String.valueOf(c.getInt(0)));
                    }
                    c.close();

                    c = sql.rawQuery("SELECT Sum(amount) FROM " + DbHelper.TABLE_INCOME, null);
                    TextView income = findViewById(R.id.income);
                    if (c.moveToFirst()) {
                        income.setText(String.valueOf(c.getInt(0)));
                    }
                    else
                    {
                        income.setText(String.valueOf(0));
                    }
                    c.close();



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This method is invoked when the currently selected item is deselected.
                // You can add your own logic here if needed.
            }
        });

        String[] sampleFeedbacks = {
                "Best App! Very useful.",
                "Needs improvement in the user interface.",
                "Excellent customer service!",
                "Could use more features.",
                "User-friendly and intuitive.",
        };
        ListView listView = findViewById(R.id.comments);
        ArrayList<String> commentslist = new ArrayList<>();
        for (String abc1 : sampleFeedbacks) {
            commentslist.add(abc1.toString());
        }
        Log.d("commentslist", String.valueOf(commentslist));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentslist);
        listView.setAdapter(arrayAdapter);

    }

    public void openverificationh(View v) {
        Context abc = getApplicationContext();
        Intent ver = new Intent(abc, verification.class);
        if (abc != this) {
            startActivity(ver);


        }
    }

    public void opensettingsh(View v) {

        Intent set = new Intent(homepage.this, settings.class);

            startActivity(set);



    }
    public void income(View v)
    {
        startActivity(new Intent(homepage.this, incomepage.class));
    }
    public void users(View v)
    {
        startActivity(new Intent(homepage.this,userspage.class));
    }
    public void ngos(View v)
    {
        startActivity(new Intent(homepage.this, activengo.class));
    }
    public void donatedclothes(View v)
    {
        startActivity(new Intent(homepage.this, donatedclothes.class));
    }
    public void employees(View v)
    {
        startActivity(new Intent(homepage.this, employees.class));
    }
    public void complaints(View v)
    {
        startActivity(new Intent(homepage.this, complaints.class));
    }
    public void profile(View v)
    {
        startActivity(new Intent(homepage.this, Profile.class));
    }



}
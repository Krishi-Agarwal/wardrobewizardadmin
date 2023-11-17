package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class complaints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        ArrayList<String> complaintDetails = new ArrayList<>();
        ArrayList<String> complaint = new ArrayList<>();
        ArrayList<Integer> idc = new ArrayList<>();
        complaintDetails.add("Compt ID\t\tCust ID\t\tDate\t\tStatus");
        ListView lv = findViewById(R.id.dataListView);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_COMPLAINTS, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int complaintId = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_COMPLAINT_ID));
            @SuppressLint("Range") int customerId = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_CUSTOMER_ID));
            @SuppressLint("Range") String complaintText = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_COMPLAINT_TEXT));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_COMPLAINT_DATE));
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_COMPLAINT_STATUS));

            complaintDetails.add(complaintId + "\t\t" + customerId + "\t\t" + date + "\t\t" + status);
            complaint.add(complaintText);
            idc.add(customerId);
        }

        ArrayAdapter<String> complaintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, complaintDetails);
        lv.setAdapter(complaintAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(complaints.this);

                // Set the dialog title and message
                builder.setTitle("COMPLAINT");
                if(position!=0)
                    builder.setMessage(complaint.get(position-1) );

                // Set positive button and its click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase du=dbHelper.getWritableDatabase();
                        ContentValues cv= new ContentValues();

                        cv.put(DbHelper.COLUMN_COMPLAINT_STATUS,"resolved");

                        du.close();
                        dialog.dismiss();

                    }
                });

                // Create and show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
            }


    }

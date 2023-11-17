package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfile extends AppCompatActivity {
    private TextInputEditText nameEditText, emailEditText, phoneNumberEditText, addressEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        addressEditText = findViewById(R.id.editTextAddress);
        DbHelper dbh= new DbHelper(EditProfile.this);
        SQLiteDatabase db=dbh.getReadableDatabase();
        Cursor c= db.rawQuery("Select * from "+dbh.TABLE_USERS+" where id=?",new String[]{String.valueOf(loginpage.id)});
        while (c.moveToNext())
        {
            nameEditText.setText(c.getString(1));
            phoneNumberEditText.setText(c.getString(3));
            emailEditText.setText(c.getString(4));
            addressEditText.setText(c.getString(5));

        }
        db.close();
        SQLiteDatabase db1=dbh.getWritableDatabase();
        // Find and set the Save button's OnClickListener
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the data from the TextInputEditText fields
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String address = addressEditText.getText().toString();
                ContentValues values = new ContentValues();
                values.put(DbHelper.COLUMN_USERNAME, name);
                values.put(DbHelper.COLUMN_EMAIL, email);
                values.put(DbHelper.COLUMN_PHONENUMBER, phoneNumber);
                values.put(DbHelper.COLUMN_ADDRESS, address);

                // Define the WHERE clause to specify which user to update (use the user's unique identifier, e.g., user ID)
                String whereClause = DbHelper.COLUMN_ID + " = ?";
                String[] whereArgs = { String.valueOf(loginpage.id) };
                int rowsUpdated = db1.update(DbHelper.TABLE_USERS, values, whereClause, whereArgs);

                if (rowsUpdated > 0) {
                    Toast.makeText(EditProfile.this, "SUCCESSFULLY UPDATED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfile.this, " UPDATE FAILED", Toast.LENGTH_SHORT).show();
                }
                db1.close();

                // Now you have the data in the 'name', 'email', 'phoneNumber', and 'address' variables
                // You can perform further actions or save the data to a database.
            }
        });
    }
}
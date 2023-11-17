package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class changepassword extends AppCompatActivity {

    private TextInputEditText currentPasswordEditText;
    private TextInputEditText newPasswordEditText;
    private TextInputEditText confirmPasswordEditText;
    private DbHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DbHelper(this);

        currentPasswordEditText = (TextInputEditText) ((TextInputLayout)(findViewById(R.id.currentPassword))).getEditText();
        newPasswordEditText = (TextInputEditText) ((TextInputLayout)(findViewById(R.id.newPassword))).getEditText();
        confirmPasswordEditText = (TextInputEditText) ((TextInputLayout)(findViewById(R.id.confirmPassword))).getEditText();

        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String currentPassword = currentPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        // Verify the old password
        SQLiteDatabase db = database.getReadableDatabase();
        String id = String.valueOf(loginpage.id);
        Cursor cursor = db.rawQuery("SELECT * FROM " + database.TABLE_USERS + " WHERE id=? AND " + database.COLUMN_PASSWORD + "=?", new String[]{id, currentPassword});

        if (cursor.getCount() > 0) {
            if (newPassword.equals(confirmPassword)) {
                // Update the password with the new one
                SQLiteDatabase writeDb = database.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(database.COLUMN_PASSWORD, newPassword);
                writeDb.update(database.TABLE_USERS, values, "id=?", new String[]{id});
                writeDb.close();
                Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
            } else {
                confirmPasswordEditText.setError("Passwords do not match");
                confirmPasswordEditText.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        } else {
            Toast.makeText(this, "Incorrect current password", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}

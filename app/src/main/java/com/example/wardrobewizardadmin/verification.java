package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class verification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
    }


    public void opensettingsv(View v) {
        Context abc = getApplicationContext();
        Intent set = new Intent(abc, settings.class);
        if (abc != this) {
            startActivity(set);
            finish();
        }
    }

    public void openhomepagev(View v) {
        try {
            Intent hmpg = new Intent(verification.this, homepage.class);
            startActivity(hmpg);
//            finish();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("ERROR IN HOME", "openhomepagev: "+e.getMessage() );
        }

    }
}
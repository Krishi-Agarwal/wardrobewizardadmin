package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class settings extends AppCompatActivity {
    private TextView changePasswordButton,logout,editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        changePasswordButton=findViewById(R.id.changePasswordTextSettings);
        logout=findViewById(R.id.logout);
        editprofile=findViewById(R.id.editprofile);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginpage.id=-1;
                Intent lp= new Intent(settings.this,loginpage.class);
                loginpage.esp.putString("username","");
                loginpage.esp.putString("password","");
                loginpage.esp.apply();
                startActivity(lp);
                finish();
            }
        });
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cp = new Intent(settings.this,changepassword.class);
                startActivity(cp);
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cp = new Intent(settings.this,EditProfile.class);
                startActivity(cp);

            }
        });

    }
    public void openverifications(View v) {
        Context abc = getApplicationContext();
        Intent ver = new Intent(abc, verification.class);
        if (abc != this) {
            startActivity(ver);
            finish();
        }
    }



    public void openhomepages(View v) {


        Intent hmpg = new Intent(settings.this, homepage.class);

            startActivity(hmpg);
            finish();


    }

}
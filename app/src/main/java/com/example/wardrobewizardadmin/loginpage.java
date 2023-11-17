package com.example.wardrobewizardadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class loginpage extends AppCompatActivity {
    public TextInputLayout username,password;
    public TextInputEditText usernametext,passwordtext;
    public String usernamedata,passworddata;
    public Button login;
    public SQLiteDatabase db;
    public ArrayList<User> admindata;
    public DbHelper database;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor esp;
    public static int id=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp=getSharedPreferences("logindata",MODE_PRIVATE);

        esp=sp.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        login=findViewById(R.id.buttonLogin);
        username=findViewById(R.id.editTextUsername);
        password=findViewById(R.id.editTextPassword);
        usernametext=(TextInputEditText)(username.getEditText());
        passwordtext=(TextInputEditText)(password.getEditText());
        usernametext.setText(sp.getString("username",""));
        String a=sp.getString("password","");



        Log.d("password","password "+passworddata);
        admindata = new ArrayList<>();
        database= new DbHelper(this);

        db=database.getReadableDatabase();

        Cursor cursor=db.rawQuery("Select * from "+database.TABLE_USERS,null);
        while(cursor.moveToNext())
        {
            User model = new User();
            model.id=cursor.getInt(0);
            model.username=cursor.getString(1);
            model.password=cursor.getString(2);
            admindata.add(model);
        }
        if(!a.equals(""))
        {
            passwordtext.setText(a);
            click();

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });
    }

    public void click()
    {

        usernamedata=usernametext.getText().toString();
        passworddata=passwordtext.getText().toString();
        for(User a :admindata)
        {
            if(a.username.equals(usernamedata) && a.password.equals(passworddata))
            {
                id=a.id;
            }
        }
        if(id!=-1) {
            esp.putString("username",usernamedata);
            esp.putString("password",passworddata);
            esp.apply();
            Intent homepage = new Intent(loginpage.this,com.example.wardrobewizardadmin.homepage.class);
            if(usernamedata != null)
            {
                homepage.putExtra("username", usernametext.getText().toString());
            }
            startActivity(homepage);
            finish();
        }
        else
        {
            passwordtext.setError("Passwords do not match");
            passwordtext.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
    }

}
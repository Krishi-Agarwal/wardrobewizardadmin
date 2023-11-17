package com.example.wardrobewizardadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_PHONENUMBER = "phonenumber";

    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ADDRESS = "address";

    public static final String COLUMN_DOB = "dob";

    // Create table query
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_PHONENUMBER + " TEXT DEFAULT 'NOT AVAILABLE', " +

                    COLUMN_EMAIL + " TEXT DEFAULT 'NOT AVAILABLE', " +
                    COLUMN_ADDRESS + " TEXT DEFAULT 'NOT AVAILABLE', " +
                    COLUMN_DOB + " DATE DEFAULT '1000-01-01');";

    public static final String TABLE_INCOME = "income";
    public static final String COLUMN_INCOME_ID = "id";
    public static final String COLUMN_INCOME_AMOUNT ="amount" ;
    public static final String COLUMN_INCOME_DATE = "date";
    public static final String COLUMN_INCOME_SOURCE = "source";
    public static final String TABLE_INCOME_CREATE =
            "CREATE TABLE " + TABLE_INCOME + " (" +
                    COLUMN_INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_INCOME_AMOUNT + " DECIMAL(10, 2) NOT NULL, " +
                    COLUMN_INCOME_DATE + " DATE NOT NULL, " +
                    COLUMN_INCOME_SOURCE+ "TEXT NOT NULL);";


    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_EMPLOYEE_NAME = "name";
    public static final String COLUMN_EMPLOYEE_ROLE = "role";
    public static final String COLUMN_EMPLOYEE_SALARY = "salary";
    public static final String COLUMN_EMPLOYEE_EMAIL = "email";
    public static final String COLUMN_EMPLOYEE_PHONE = "phone";
    public static final String COLUMN_EMPLOYEE_ADDRESS = "address";
    public static final String COLUMN_EMPLOYEE_JOIN_DATE = "joindate";
    public static final String TABLE_EMPLOYEES_CREATE =
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMPLOYEE_NAME + " TEXT NOT NULL, " +
                    COLUMN_EMPLOYEE_ROLE + " TEXT, " +
                    COLUMN_EMPLOYEE_SALARY + " DECIMAL(10, 2), " +
                    COLUMN_EMPLOYEE_JOIN_DATE + " DATE, " +
                    COLUMN_EMPLOYEE_ADDRESS + " TEXT, " +
                    COLUMN_EMPLOYEE_PHONE + " TEXT, " +
                    COLUMN_EMPLOYEE_EMAIL + " TEXT);";


    public static final String TABLE_CUSTOMERS = "customers";
    public static final String TABLE_CUSTOMERS_CREATE =
            "CREATE TABLE " + TABLE_CUSTOMERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_PHONENUMBER + " TEXT DEFAULT 'NOT AVAILABLE', " +
                    COLUMN_EMAIL + " TEXT DEFAULT 'NOT AVAILABLE', " +
                    COLUMN_ADDRESS + " TEXT DEFAULT 'NOT AVAILABLE', " +
                    COLUMN_DOB + " DATE DEFAULT '1000-01-01');";

    public static final String TABLE_COMPLAINTS = "complaints";
    public static final String COLUMN_COMPLAINT_ID = "complaintid";
    public static final String COLUMN_CUSTOMER_ID = "customerid";
    public static final String COLUMN_COMPLAINT_TEXT = "complaint";
    public static final String COLUMN_COMPLAINT_DATE = "date";
    public static final String COLUMN_COMPLAINT_STATUS = "status";
    public static final String TABLE_COMPLAINTS_CREATE =
            "CREATE TABLE " + TABLE_COMPLAINTS + " (" +
                    COLUMN_COMPLAINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CUSTOMER_ID + " INTEGER, " +
                    COLUMN_COMPLAINT_TEXT + " TEXT NOT NULL, " +
                    COLUMN_COMPLAINT_DATE + " DATE NOT NULL, " +
                    COLUMN_COMPLAINT_STATUS + " TEXT DEFAULT 'UNRESOLVED', " +
                    "FOREIGN KEY(" + COLUMN_CUSTOMER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "));";



    public static final String TABLE_NGO = "ngo";
    public static final String COLUMN_NGO_ID = "ngoid";
    public static final String COLUMN_NGO_NAME = "name";
    public static final String COLUMN_NGO_MISSION = "mission";
    public static final String COLUMN_NGO_DESCRIPTION = "description";
    public static final String COLUMN_NGO_FOUNDATION_DATE = "foundationdate";
    public static final String COLUMN_NGO_WEBSITE = "website";
    public static final String COLUMN_NGO_EMAIL = "email";
    public static final String COLUMN_NGO_PHONE = "phone";
    public static final String COLUMN_NGO_ADDRESS = "address";


    public static final String TABLE_NGO_CREATE =
            "CREATE TABLE " + TABLE_NGO + " (" +
                    COLUMN_NGO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NGO_NAME + " TEXT NOT NULL, " +
                    COLUMN_NGO_MISSION + " TEXT, " +
                    COLUMN_NGO_DESCRIPTION + " TEXT, " +
                    COLUMN_NGO_FOUNDATION_DATE + " DATE, " +
                    COLUMN_NGO_WEBSITE + " TEXT, " +
                    COLUMN_NGO_EMAIL + " TEXT, " +
                    COLUMN_NGO_PHONE + " TEXT, " +
                    COLUMN_NGO_ADDRESS + " TEXT);";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CUSTOMERS_CREATE);
        db.execSQL(TABLE_NGO_CREATE);
        db.execSQL(TABLE_EMPLOYEES_CREATE);
        db.execSQL(TABLE_COMPLAINTS_CREATE);
        db.execSQL(TABLE_INCOME_CREATE);
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_USERNAME,"Krishi");
        cv.put(COLUMN_PASSWORD,"12345");
        db.insert(TABLE_USERS,null,cv);
        cv.put(COLUMN_USERNAME,"Ayush");
        cv.put(COLUMN_PASSWORD,"12345");
        db.insert(TABLE_USERS,null,cv);
        cv.put(COLUMN_USERNAME,"Arpit");
        cv.put(COLUMN_PASSWORD,"12345");
        db.insert(TABLE_USERS,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

        String[] tableNames = {TABLE_CUSTOMERS, TABLE_INCOME, TABLE_EMPLOYEES, TABLE_COMPLAINTS, TABLE_NGO};

// Loop through the array and drop and recreate each table
//        for (String tableName : tableNames) {
//            db.execSQL("DROP TABLE IF EXISTS " + tableName);
//            // Recreate the table using the appropriate CREATE statement
//            if (tableName.equals(TABLE_CUSTOMERS)) {
//                db.execSQL(TABLE_CUSTOMERS_CREATE);
//            } else if (tableName.equals(TABLE_INCOME)) {
//                db.execSQL(TABLE_INCOME_CREATE);
//            } else if (tableName.equals(TABLE_EMPLOYEES)) {
//                db.execSQL(TABLE_EMPLOYEES_CREATE);
//            } else if (tableName.equals(TABLE_COMPLAINTS)) {
//                db.execSQL(TABLE_COMPLAINTS_CREATE);
//            } else if (tableName.equals(TABLE_NGO)) {
//                db.execSQL(TABLE_NGO_CREATE);
//            }
//        }

    }

    // Add a new user to the database
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Check if a user with the given username and password exists
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[] { username, password });
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
}

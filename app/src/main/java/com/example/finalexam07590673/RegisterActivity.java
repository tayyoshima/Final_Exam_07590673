package com.example.finalexam07590673;



import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn ;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private EditText regName,regUser,regPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        openHelper = new DatabaseHelper(this);

        registerBtn = findViewById(R.id.register_button);
        regName = findViewById(R.id.full_name_edit_text);
        regUser = findViewById(R.id.username_edit_text);
        regPassword = findViewById(R.id.password_edit_text);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = regName.getText().toString().trim();
                String fUser = regUser.getText().toString().trim();
                String fPassword = regPassword.getText().toString().trim();
                if (fname.isEmpty() || fPassword.isEmpty() || fUser.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(fname,fUser,fPassword);
                    Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void insertData(String fname,String fUser,String fPassword){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,fname);
        contentValues.put(DatabaseHelper.COL_3,fUser);
        contentValues.put(DatabaseHelper.COL_4,fPassword);

        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }
}
package com.rekatrina.activitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QueryActivity extends AppCompatActivity {

    private EditText editText_id;
    private EditText editText_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        editText_id = (EditText)findViewById(R.id.editText_bikeid);
        editText_pwd = (EditText)findViewById(R.id.editText_pwd);


    }
}

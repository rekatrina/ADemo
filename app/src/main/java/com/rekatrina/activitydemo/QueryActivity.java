package com.rekatrina.activitydemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

public class QueryActivity extends AppCompatActivity {

    private EditText editText_id;
    private EditText editText_pwd;
    private SQLiteHelp helper;
    private Cursor cursor;
    private ListView lvBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        editText_id = (EditText)findViewById(R.id.editText_bikeid);
        editText_pwd = (EditText)findViewById(R.id.editText_pwd);
        lvBike = (ListView)findViewById(R.id.listView_bike);
        //表中内容填充到自定义ListView
        helper = new SQLiteHelp(this);
        cursor = helper.select();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.bikelist,
                cursor,
                new String[] {"Num","Pwd"},
                new int[] { R.id.textNum,R.id.textPwd}
        );
        lvBike.setAdapter(adapter);

//        // lvBook设置OnItemClickListener监听事件
//        lvBike.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
//                cursor.moveToPosition(arg2);          // 将cursor移到所点击的值
//                id = cursor.getInt(0);                // 取得字段_id的值
//                editText_id.setText(cursor.getString(1));    // 取得字段Rec_text的值
//                editAuthor.setText(cursor.getString(2));
//                editPublisher.setText(cursor.getString(3));
//            }
//        });

        editText_id.addTextChangedListener(new TextWatcher(){
            private CharSequence temp;
            private int editStart ;
            private int editEnd ;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if(temp.length()<=6)
//                {
//                    editText_pwd.setText("good");
//                }
//                else
//                    editText_pwd.setText("Too many");
            }
        });



    }

    public void addRec(View v)
    {
        String  id = editText_id.getText().toString();
        if (id.length()!=6) {
            editText_id.setText("Please input 6 nums");
            return;
        }
        helper.insert(editText_id.getText().toString(),editText_pwd.getText().toString());
//        lvBike.invalidateViews();
    }

    public void getRec(View v)
    {
        editText_pwd.setText("1234");
    }
}

package com.rekatrina.activitydemo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SimpleCursorAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
    String msg = "CongCong;";

    private EditText editText_name;
    private Button button_showName;
    private Button button_send;
    private TextView textView_content;


    private EditText editText_id;
    private EditText editText_pwd;
    private SQLiteHelp helper;
    private Cursor cursor;
    private ListView lvBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // get widget handle
        editText_name = (EditText)findViewById(R.id.editText_name);
        button_showName = (Button)findViewById(R.id.button1);
        textView_content = (TextView)findViewById(R.id.show_text);
        button_send = (Button)findViewById(R.id.button2);

        button_send.setOnClickListener(new onSendClickListener());

        button_showName.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                textView_content.setText("喂！ 你到底放不放手！");
                return true;
            }
        }
        );

        button_showName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String name = editText_name.getText().toString();
                String cong = "cong";
                if(name.equals(cong))
                    textView_content.setText("你是小宝贝！！ yoyo");
                else
                    textView_content.setText("NO NO NO！");
                textView_content.setTextColor(Color.RED);
            }
        });

        // configure Sliding Menu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindWidthRes(R.dimen.slidingMenu_offset);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable( R.drawable.shadow ) ;
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.activity_query);


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

        Log.d(msg, "onCreate()");
    }

    private final class onSendClickListener implements View.OnClickListener{
        public void onClick(View view)
        {
            String msg = editText_name.getText().toString();
            Intent intent = new Intent(MainActivity.this, QueryActivity.class);

            Bundle data  = new Bundle();
            data.putString("msg",msg);

            intent.putExtras(data);

            startActivity(intent);
        }
    }


    // 当Activity即将变得可见时会调用onStart()方法
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The function onStart() was called.");
    }

    // 当Activity已经变得可见时会调用onResume()方法
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The function onResume() was called.");
    }

    // 当其他的Activity已经获得了焦点时会调用onPause()方法
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The function onPause() was called.");
    }

    // 当Activity不再可见的时候会调用onStop()方法
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The function onStop() was called.");
    }

    // 当Activity被销毁的时候会调用onDestroy()方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The function onDestroy() was called.");
    }

    public void addRec(View v)
    {
        String  id = editText_id.getText().toString();
        if (id.length()!=5) {
            editText_id.setText("Please input 5 nums");
            return;
        }
        helper.insert(editText_id.getText().toString(),editText_pwd.getText().toString());
//        lvBike.invalidateViews();
    }

    public void getRec(View v)
    {
        String et=editText_id.getText().toString();
        String args[]=new String[]{et};
        cursor=helper.query(args);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.bikelist,
                cursor,
                new String[] {"Num","Pwd"},
                new int[] { R.id.textNum,R.id.textPwd}
        );
        lvBike.setAdapter(adapter);
        while (cursor.moveToNext())
        {
            String uid = cursor.getString(cursor.getColumnIndex("Pwd"));
            editText_pwd.setText(uid);
        }

    }
}

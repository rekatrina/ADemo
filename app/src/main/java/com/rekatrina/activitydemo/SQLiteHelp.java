package com.rekatrina.activitydemo;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/12.
 */

public class SQLiteHelp extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "School";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "Bike";

    public SQLiteHelp(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(_id INTEGER PRIMARY KEY,"
                + " Num VARCHAR(30)  NOT NULL,"
                + " Pwd VARCHAR(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    //获取游标
    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }

    //插入一条记录
    public long insert(String num, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Num", num);
        cv.put("Pwd", pwd);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

    //根据条件查询
    public Cursor query(String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "Num = ? ", args, null, null, null);
//        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE Num = ?", args);
        return cursor;
    }

    //删除记录
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where ="_id = ?";
        String[] whereValue = { Integer.toString(id) };
        db.delete(TABLE_NAME, where, whereValue);
    }

    //更新记录
    public void update(int id, String num, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = { Integer.toString(id) };
        ContentValues cv = new ContentValues();
        cv.put("Num", num);
        cv.put("Pwd", pwd);
        db.update(TABLE_NAME, cv, where, whereValue);
    }
}

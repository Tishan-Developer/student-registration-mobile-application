package com.example.dbapp.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "School_Database",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE student (reg_no TEXT PRIMARY KEY, name TEXT, BDay TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS student");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String _reg_no, String _fName, String _bDay, String _email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("reg_no", _reg_no);
        cv.put("name", _fName);
        cv.put("BDay", _bDay);
        cv.put("email",_email);
        long result = db.insert("student",null,cv);
        return result != -1;
    }

    public boolean updateDate(String _reg_no, String _fName, String _bDay, String _email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (_fName != null) {
            cv.put("name", _fName);
        }
        if (_bDay != null) {
            cv.put("BDay", _bDay);
        }
        if (_email != null) {
            cv.put("email",_email);
        }
        Cursor c = db.rawQuery("SELECT * FROM student WHERE reg_no = ?", new String[]{_reg_no});
        if (c.getCount() > 0) {
            c.close();
            long result = db.update("student",cv,"reg_no = ?",new String[]{_reg_no});
            return result != -1;
        }
        return false;
    }

    public boolean deleteData(String _reg_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor c = db.rawQuery("SELECT * FROM student WHERE reg_no = ?", new String[]{_reg_no});
        if (c.getCount() > 0) {
            c.close();
            long result = db.delete("student","reg_no = ?",new String[]{_reg_no});
            return result != -1;
        }
        return false;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM student",null);
    }
}

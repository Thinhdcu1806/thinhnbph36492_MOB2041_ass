package com.example.thinhnbph36492_mob2041_ass.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thinhnbph36492_mob2041_ass.DataBase.DbHelper;
import com.example.thinhnbph36492_mob2041_ass.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        values.put("soluong", obj.getSoluong());
        return db.insert("Sach", null, values);
    }

    public int updateS(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        values.put("soluong", obj.getSoluong());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.getMaSach())});
    }

    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    // lấy toàn bộ sách có trong thư viện
    //get data nhiều tham so
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs) {
        List<Sach> list = new ArrayList<Sach>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(c.getString (c.getColumnIndex( "maSach"))));
            obj.setTenSach(c.getString(c.getColumnIndex( "tenSach")));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex( "giaThue"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex( "maLoai"))));
            obj.setSoluong(Integer.parseInt(c.getString(c.getColumnIndex("soluong"))));
            list.add(obj);
        }
        return list;
    }

    public List<Sach> getAll() {
        String sql = "SELECT  * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }
}

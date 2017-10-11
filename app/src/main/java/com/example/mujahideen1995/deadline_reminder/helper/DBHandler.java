package com.example.mujahideen1995.deadline_reminder.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mujahideen1995.deadline_reminder.model.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mujahideen1995 on 10/11/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_reminder"; // NAMA DATABASE
    private static final String TABLE_REMINDER = "table_reminder"; // NAMA TABEL
    private static final String COLUMN_ID = "id"; //
    private static final String COLUMN_TITLE = "title"; //
    private static final String COLUMN_DESCRIPTION = "description"; //
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_REMINDER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA MAHASISWA
    public void tambahReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, reminder.getTitle());
        values.put(COLUMN_DESCRIPTION, reminder.getDescription());
        values.put(COLUMN_DATE, reminder.getDate());
        values.put(COLUMN_TIME, reminder.getTime());

        db.insert(TABLE_REMINDER, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA MAHASISWA
    public Reminder getMahasiswa(int id_mahasiswa){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REMINDER, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_DATE, COLUMN_TIME},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_mahasiswa)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Reminder mahasiswa = new Reminder(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return mahasiswa;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA MAHASISWA
    public List<Reminder> getSemuaReminder(){
        List<Reminder> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REMINDER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Reminder mahasiswa = new Reminder(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }
        return mahasiswaList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getMahasiswaCount(){
        String countQuery = "SELECT * FROM " + TABLE_REMINDER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // FUNGSI UPDATE DATA MAHASISWA
    public int updateDataMahasiswa(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, reminder.getTitle());
        values.put(COLUMN_DESCRIPTION, reminder.getTitle());
        values.put(COLUMN_DATE, reminder.getDate());
        values.put(COLUMN_TIME, reminder.getTime());
        return db.update(TABLE_REMINDER, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(reminder.getId())});
    }

    // FUNGSI HAPUS DATA 1 MAHASISWA
    public void hapusDataMahasiswa(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDER, COLUMN_ID + " = ?",
                new String[]{String.valueOf(reminder.getId())});
        db.close();
    }

    // FUNGSI UNTUK MENGHAPUS SEMUA DATA MAHASISWA
    public void hapusSemuaDataReminder(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_REMINDER);
    }
}

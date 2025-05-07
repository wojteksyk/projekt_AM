package com.example.ordertracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_ORDERS = "orders";
    public static final String COLUMN_ORDER_NUMBER = "orderNumber";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DETAILS = "details";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_ORDERS + " (" +
                COLUMN_ORDER_NUMBER + " TEXT PRIMARY KEY, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_DETAILS + " TEXT)";
        db.execSQL(createTable);


        addSampleOrder(db, "123", "w drodze", "Zamówienie zostało nadane i oczekuje na kuriera.");
        addSampleOrder(db, "123456", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addSampleOrder(db, "11223", "W drodze", "Zamówienie jest w trakcie transportu.");
        addSampleOrder(db, "44556", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addSampleOrder(db, "2137", "w drodze", "Papież Polak samodzielnie dostarczy zamówienie.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }


    private void addSampleOrder(SQLiteDatabase db, String orderNumber, String status, String details) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_NUMBER, orderNumber);
        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_DETAILS, details);
        db.insert(TABLE_ORDERS, null, values);
    }


    public Cursor getOrder(String orderNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ORDERS,
                new String[]{COLUMN_ORDER_NUMBER, COLUMN_STATUS, COLUMN_DETAILS},
                COLUMN_ORDER_NUMBER + " = ?",
                new String[]{orderNumber},
                null, null, null);
    }
}
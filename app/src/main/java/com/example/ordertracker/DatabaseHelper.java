package com.example.ordertracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 6;

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


        addOrder(db, "1", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "2", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "3", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "4", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "5", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "6", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "7", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "8", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "9", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "10", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "11", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "12", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "13", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "14", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "15", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "16", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "17", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "18", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "19", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "20", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "21", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "22", "Dostarczone", "Zamówienie zostało dostarczone do odbiorcy.");
        addOrder(db, "23", "Oczekujące", "Zamówienie oczekuje na przetworzenie.");
        addOrder(db, "24", "W drodze", "Zamówienie jest w trakcie transportu.");
        addOrder(db, "2137", "W drodze", "Papież Leon jedzie w papaMobilu");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }


    private void addOrder(SQLiteDatabase db, String orderNumber, String status, String details) {
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
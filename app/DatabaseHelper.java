import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "OrderTrackerDB";
    private static final int DATABASE_VERSION = 1;

    // Nazwy tabel i kolumn
    public static final String TABLE_ORDERS = "orders";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NUMBER = "order_number";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DETAILS = "details";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tworzenie tabeli
        String CREATE_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NUMBER + " TEXT, "
                + COLUMN_STATUS + " TEXT, "
                + COLUMN_DETAILS + " TEXT)";
        db.execSQL(CREATE_TABLE);

        // Dodaj przykładowe dane
        addSampleOrders(db);
    }

    private void addSampleOrders(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Przykładowe zamówienie 1
        values.put(COLUMN_NUMBER, "ORD123");
        values.put(COLUMN_STATUS, "W drodze");
        values.put(COLUMN_DETAILS, "Paczka w sortowni w Warszawie");
        db.insert(TABLE_ORDERS, null, values);

        // Przykładowe zamówienie 2
        values.clear();
        values.put(COLUMN_NUMBER, "ORD456");
        values.put(COLUMN_STATUS, "Dostarczone");
        values.put(COLUMN_DETAILS, "Odebrano 20.05.2024");
        db.insert(TABLE_ORDERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    // Metoda do wyszukiwania zamówienia
    public Cursor getOrder(String orderNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ORDERS,
                new String[]{COLUMN_NUMBER, COLUMN_STATUS, COLUMN_DETAILS},
                COLUMN_NUMBER + "=?",
                new String[]{orderNumber},
                null, null, null);
    }
}
package bestsolutions.net.consultamais.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "consultamais_db";

    public DBConnector(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MovieDB.TABLE_NAME + "(" +
                MovieDB.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MovieDB.COLUMN_CAMINHOPOSTER + " TEXT, " +
                MovieDB.COLUMN_CAMINHOBACKPOSTER + " TEXT, " +
                MovieDB.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MovieDB.COLUMN_RELEASEDATE + " TEXT, " +
                MovieDB.COLUMN_ORIGINALTITLE + " TEXT, " +
                MovieDB.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieDB.COLUMN_POPULARITY + " REAL, " +
                MovieDB.COLUMN_QTDVOTES + " INT, " +
                MovieDB.COLUMN_ASSISTIDO + " INT, " +
                MovieDB.COLUMN_BYTEPOST + " BLOB, " +
                MovieDB.COLUMN_BYTEBACKPOST + " BLOB, " +
                MovieDB.COLUMN_AVERAGEVOTE + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
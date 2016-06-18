package bestsolutions.net.consultamais.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "consultamais_db";

    public DBConnector(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PacienteDB.TABLE_NAME + "(" +
                PacienteDB.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PacienteDB.COLUMN_NOME + " TEXT NOT NULL, " +
                PacienteDB.COLUMN_SEXO + " INT, " +
                PacienteDB.COLUMN_TELEFONE + " TEXT, " +
                PacienteDB.COLUMN_CELULAR + " TEXT, " +
                PacienteDB.COLUMN_RUA + " TEXT, " +
                PacienteDB.COLUMN_NUMERO + " TEXT, " +
                PacienteDB.COLUMN_COMPLEMENTO + " TEXT, " +
                PacienteDB.COLUMN_CEP + " TEXT, " +
                PacienteDB.COLUMN_BAIRRO + " TEXT, " +
                PacienteDB.COLUMN_CIDADE + " TEXT, " +
                PacienteDB.COLUMN_ESTADO + " TEXT )");

        db.execSQL("CREATE TABLE " + MedicoDB.TABLE_NAME + "(" +
                MedicoDB.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MedicoDB.COLUMN_NOME + " TEXT NOT NULL, " +
                MedicoDB.COLUMN_ESPECIALIDADE + " TEXT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
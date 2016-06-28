package bestsolutions.net.consultamais.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bestsolutions.net.consultamais.entidades.Consulta;
import bestsolutions.net.consultamais.entidades.Medico;

public class ConsultaDB {
    public static final String TABLE_NAME = "consulta";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PACIENTE = "paciente";
    public static final String COLUMN_MEDICO = "medico";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HORA = "hora";


    private Context mContext;

    public ConsultaDB(Context context) {
        this.mContext = context;
    }

    public long Inserir(Consulta cons) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromClass(cons);

        long id = sqLiteDatabase.insert(ConsultaDB.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return id;
    }

    public int Update(Consulta consulta) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromClass(consulta);

        int rowsAffected = sqLiteDatabase.update(ConsultaDB.TABLE_NAME, contentValues,
                ConsultaDB.COLUMN_ID + "=?",
                new String[]{String.valueOf(consulta.getId())});

        sqLiteDatabase.close();

        return rowsAffected;
    }

    public int Delete(int idConsulta) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();
        int rowsAffected = sqLiteDatabase.delete(ConsultaDB.TABLE_NAME,
                ConsultaDB.COLUMN_ID + " = ?",
                new String[]{String.valueOf(idConsulta)});
        sqLiteDatabase.close();

        return rowsAffected;
    }

    public ArrayList<Consulta> Listagem() {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ConsultaDB.TABLE_NAME + " ORDER BY " + ConsultaDB.COLUMN_PACIENTE, null);
        ArrayList<Consulta> consultas = new ArrayList<>();
        int idxID = cursor.getColumnIndex(ConsultaDB.COLUMN_ID);
        int idxPaciente = cursor.getColumnIndex(ConsultaDB.COLUMN_PACIENTE);
        int idxMedico = cursor.getColumnIndex(ConsultaDB.COLUMN_MEDICO);
        int idxData = cursor.getColumnIndex(ConsultaDB.COLUMN_DATA);
        int idxHora = cursor.getColumnIndex(ConsultaDB.COLUMN_HORA);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Consulta consulta = new Consulta();
                consulta.setId(cursor.getInt(idxID));
                consulta.setPaciente(cursor.getString(idxPaciente));
                consulta.setNomeMedico(cursor.getString(idxMedico));
                consulta.setDataAtendimento(cursor.getString(idxData));
                consulta.setHoraAtendimento(cursor.getString(idxHora));
                consultas.add(consulta);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return consultas;
    }

    private ContentValues valuesFromClass(Consulta consulta) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConsultaDB.COLUMN_PACIENTE, consulta.getPaciente());
        contentValues.put(ConsultaDB.COLUMN_MEDICO, consulta.getNomeMedico());
        contentValues.put(ConsultaDB.COLUMN_DATA, consulta.getDataAtendimento());
        contentValues.put(ConsultaDB.COLUMN_HORA, consulta.getHoraAtendimento());
        return contentValues;
    }
}
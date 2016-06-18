package bestsolutions.net.consultamais.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bestsolutions.net.consultamais.entidades.Medico;
import bestsolutions.net.consultamais.entidades.Paciente;

public class MedicoDB {
    public static final String TABLE_NAME = "medico";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_ESPECIALIDADE = "especialidade";

    private Context mContext;

    public MedicoDB(Context context) {
        this.mContext = context;
    }

    public long Inserir(Medico med) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromClass(med);

        long id = sqLiteDatabase.insert(MedicoDB.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return id;
    }

    public int Update(Medico med) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromClass(med);

        int rowsAffected = sqLiteDatabase.update(MedicoDB.TABLE_NAME, contentValues,
                MedicoDB.COLUMN_ID + "=?",
                new String[]{String.valueOf(med.getId())});

        sqLiteDatabase.close();

        return rowsAffected;
    }

    public int Delete(int idMedico) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();
        int rowsAffected = sqLiteDatabase.delete(MedicoDB.TABLE_NAME,
                MedicoDB.COLUMN_ID + " = ?",
                new String[]{String.valueOf(idMedico)});
        sqLiteDatabase.close();

        return rowsAffected;
    }

    public ArrayList<Medico> Listagem() {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MedicoDB.TABLE_NAME + " ORDER BY " + MedicoDB.COLUMN_NOME, null);
        ArrayList<Medico> medicos = new ArrayList<>();
        int idxID = cursor.getColumnIndex(MedicoDB.COLUMN_ID);
        int idxNome = cursor.getColumnIndex(MedicoDB.COLUMN_NOME);
        int idxEspecialidade = cursor.getColumnIndex(MedicoDB.COLUMN_ESPECIALIDADE);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Medico med = new Medico();
                med.setId(cursor.getInt(idxID));
                med.setNome(cursor.getString(idxNome));
                med.setEspecialidade(cursor.getString(idxEspecialidade));
                medicos.add(med);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return medicos;
    }

    private ContentValues valuesFromClass(Medico med) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MedicoDB.COLUMN_NOME, med.getNome());
        contentValues.put(MedicoDB.COLUMN_ESPECIALIDADE, med.getEspecialidade());
        return contentValues;
    }
}
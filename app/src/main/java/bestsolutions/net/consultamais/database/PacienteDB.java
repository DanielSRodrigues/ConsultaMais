package bestsolutions.net.consultamais.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bestsolutions.net.consultamais.entidades.Paciente;

public class PacienteDB {
    public static final String TABLE_NAME = "paciente";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_SEXO = "sexo";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_CELULAR = "celular";
    public static final String COLUMN_RUA = "rua";
    public static final String COLUMN_NUMERO = "numero";
    public static final String COLUMN_COMPLEMENTO = "complemento";
    public static final String COLUMN_CEP = "cep";
    public static final String COLUMN_BAIRRO = "bairro";
    public static final String COLUMN_CIDADE = "cidade";
    public static final String COLUMN_ESTADO = "estado";

    private Context mContext;

    public PacienteDB(Context context) {
        this.mContext = context;
    }

    public long Inserir(Paciente paciente) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromClass(paciente);

        long id = sqLiteDatabase.insert(PacienteDB.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return id;
    }

    public int Update(Paciente paciente) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromClass(paciente);

        int rowsAffected = sqLiteDatabase.update(PacienteDB.TABLE_NAME, contentValues,
                PacienteDB.COLUMN_ID + "=?",
                new String[]{String.valueOf(paciente.getId())});

        sqLiteDatabase.close();

        return rowsAffected;
    }

    public int Delete(int idPaciente) {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();
        int rowsAffected = sqLiteDatabase.delete(PacienteDB.TABLE_NAME,
                PacienteDB.COLUMN_ID + " = ?",
                new String[]{String.valueOf(idPaciente)});
        sqLiteDatabase.close();

        return rowsAffected;
    }

    public ArrayList<Paciente> Listagem() {
        DBConnector produtoDbHelper = new DBConnector(mContext);
        SQLiteDatabase sqLiteDatabase = produtoDbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PacienteDB.TABLE_NAME + " ORDER BY " + PacienteDB.COLUMN_NOME, null);
        ArrayList<Paciente> filmes = new ArrayList<>();
        int idxID = cursor.getColumnIndex(PacienteDB.COLUMN_ID);
        int idxNome = cursor.getColumnIndex(PacienteDB.COLUMN_NOME);
        int idxSexo = cursor.getColumnIndex(PacienteDB.COLUMN_SEXO);
        int idxTelefone = cursor.getColumnIndex(PacienteDB.COLUMN_TELEFONE);
        int idxCelular = cursor.getColumnIndex(PacienteDB.COLUMN_CELULAR);
        int idxRua = cursor.getColumnIndex(PacienteDB.COLUMN_RUA);
        int idxNumero = cursor.getColumnIndex(PacienteDB.COLUMN_NUMERO);
        int idxComplemento = cursor.getColumnIndex(PacienteDB.COLUMN_COMPLEMENTO);
        int idxCep = cursor.getColumnIndex(PacienteDB.COLUMN_CEP);
        int idxBairro = cursor.getColumnIndex(PacienteDB.COLUMN_BAIRRO);
        int idxCidade = cursor.getColumnIndex(PacienteDB.COLUMN_CIDADE);
        int idxEstado = cursor.getColumnIndex(PacienteDB.COLUMN_ESTADO);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Paciente paciente = new Paciente();
                paciente.setId(cursor.getInt(idxID));
                paciente.setNome(cursor.getString(idxNome));
                paciente.setSexo(cursor.getInt(idxSexo));
                paciente.setTelefone(cursor.getString(idxTelefone));
                paciente.setCelular(cursor.getString(idxCelular));
                paciente.setRua(cursor.getString(idxRua));
                paciente.setNumero(cursor.getString(idxNumero));
                paciente.setComplemento(cursor.getString(idxComplemento));
                paciente.setCep(cursor.getString(idxCep));
                paciente.setBairro(cursor.getString(idxBairro));
                paciente.setCidade(cursor.getString(idxCidade));
                paciente.setEstado(cursor.getString(idxEstado));
                filmes.add(paciente);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return filmes;
    }

    public ArrayList<String> ListagemNomesPacientes() {
        ArrayList<String> pacientes = new ArrayList<String>();
        for (Paciente p : Listagem()) {
            pacientes.add(p.getNome());
        }
        return pacientes;
    }

    private ContentValues valuesFromClass(Paciente paciente) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(PacienteDB.COLUMN_ID, paciente.getId());
        contentValues.put(PacienteDB.COLUMN_NOME, paciente.getNome());
        contentValues.put(PacienteDB.COLUMN_SEXO, paciente.getSexo());
        contentValues.put(PacienteDB.COLUMN_TELEFONE, paciente.getTelefone());
        contentValues.put(PacienteDB.COLUMN_CELULAR, paciente.getCelular());
        contentValues.put(PacienteDB.COLUMN_RUA, paciente.getRua());
        contentValues.put(PacienteDB.COLUMN_NUMERO, paciente.getNumero());
        contentValues.put(PacienteDB.COLUMN_COMPLEMENTO, paciente.getComplemento());
        contentValues.put(PacienteDB.COLUMN_CEP, paciente.getCep());
        contentValues.put(PacienteDB.COLUMN_BAIRRO, paciente.getBairro());
        contentValues.put(PacienteDB.COLUMN_CIDADE, paciente.getCidade());
        contentValues.put(PacienteDB.COLUMN_ESTADO, paciente.getEstado());
        return contentValues;
    }
}
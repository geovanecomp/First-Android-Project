package com.example.rg.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.rg.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rg on 09/08/16.
 */
public class AlunoDao extends SQLiteOpenHelper {
    public AlunoDao(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL," +
                     "endereco TEXT, telefone TEXT, site TEXT, nota REAL )";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void inserir(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = getContentValues(aluno);

        db.insert("Alunos", null, valores);

    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues valores = new ContentValues();
        valores.put("nome", aluno.getNome());
        valores.put("endereco", aluno.getEndereco());
        valores.put("telefone", aluno.getTelefone());
        valores.put("site", aluno.getSite());
        valores.put("nota", aluno.getNota());
        return valores;
    }

    public List<Aluno> buscarAlunos() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Alunos";
        Cursor c = db.rawQuery(sql, null);
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getInt(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        c.close();
        return alunos;

    }

    public void deletar(int id) {
        SQLiteDatabase db =  getWritableDatabase();
        String[] parametros = {id+""};
        db.delete("Alunos", "id = ?", parametros);
    }

    public void alterar(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = getContentValues(aluno);

        String[] parametros = {aluno.getId()+""};
        db.update("Alunos", valores, "id = ?", parametros);
    }
}

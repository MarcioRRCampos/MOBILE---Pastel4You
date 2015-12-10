package com.br.marcio.campos.pastel4you.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.br.marcio.campos.pastel4you.R;
import com.br.marcio.campos.pastel4you.domain.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcio on 06/11/2015.
 */
public class ClienteDAO {

    private static final String TABLE = "client";
    private DatabaseHandler handler;
    private SQLiteDatabase db;

    public ClienteDAO(Context context) {
        handler = new DatabaseHandler(context);
        db = handler.openDatabase();
    }


    public List<Client> listar() {
        List<Client> lista = new ArrayList<Client>();

        String sql = "select * from " + TABLE + " order by nome";

        Cursor cursor = handler.getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Client client = new Client();
                client.setId(cursor.getLong(0));
                client.setNome(cursor.getString(1));
                client.setEmail(cursor.getString(2));
                client.setPassword(cursor.getString(3));
                client.setTelefone(cursor.getString(4));
                client.setBairro(cursor.getString(5));
                client.setRua(cursor.getString(6));
                client.setCep(cursor.getString(7));
                client.setNumero(cursor.getInt(8));


                lista.add(client);
            }
        } catch (SQLiteException e) {
            Log.e("Lista Clientes", e.getMessage());
        } finally {
            cursor.close();
        }

        return lista;
    }


    public void cadastrar (Client client){
        try {
            ContentValues values = new ContentValues();
            values.put("nome", client.getNome());
            values.put("email", client.getEmail());
            values.put("passoword", client.getPassword());
            values.put("telefone", client.getTelefone());
            values.put("bairro", client.getBairro());
            values.put("rua", client.getRua());
            values.put("cep", client.getCep());
            values.put("numero", client.getNumero());

            db.insert(TABLE, null, values);
            Log.i("INSER CLIENTE", "" + client.getNome());

        }catch (Exception e){
            e.getStackTrace();
            e.fillInStackTrace();
        }

    }

    public void deletar(Client cliente) {
        String[] args = {cliente.getId().toString(), cliente.getNome()};
        db.delete(TABLE, "id=? and nome=?", args);
        Log.i("DELETA CLIENTE", "CLIENTE DELETADO:" + cliente.getNome());
    }

    public void Alterar(Client client) {
        ContentValues values = new ContentValues();
        values.put("nome", client.getNome());
        values.put("email", client.getEmail());
        values.put("passoword", client.getPassword());
        values.put("telefone", client.getTelefone());
        values.put("bairro", client.getBairro());
        values.put("rua", client.getRua());
        values.put("numero",client.getNumero());
        values.put("cep",client.getCep());

        String[] agrs = {client.getId().toString()};

        db.update(TABLE, values, "id = ?", agrs);

        Log.i("Alterar CLIENTE","Alterção em cadastro do usuario : " + client.getNome());
    }


    public Client listarPorEmail(String email) {
        List<Client> lista = new ArrayList<Client>();

        String sql = "select * from " + TABLE + " where email = '"+ email+"'";

        Cursor cursor = handler.getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {

                Client client = new Client();
                client.setId(cursor.getLong(0));
                client.setNome(cursor.getString(1));
                client.setEmail(cursor.getString(2));
                client.setPassword(cursor.getString(3));
                client.setTelefone(cursor.getString(4));
                client.setBairro(cursor.getString(5));
                client.setRua(cursor.getString(6));
                client.setCep(cursor.getString(7));
                client.setNumero(cursor.getInt(8));


                lista.add(client);
            }
        } catch (SQLiteException e) {
            Log.e("Lista Clientes", e.getMessage());
        } finally {
            cursor.close();
        }

        return lista.get(0);
    }

}

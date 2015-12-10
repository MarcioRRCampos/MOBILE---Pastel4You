package com.br.marcio.campos.pastel4you.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.br.marcio.campos.pastel4you.domain.Client;
import com.br.marcio.campos.pastel4you.domain.Ingredientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcio on 06/11/2015.
 */
public class IngredientesDAO {

    private static final String TABLE = "ingredientes";
    private SQLiteDatabase db;
    private DatabaseHandler handler;

    public IngredientesDAO(Context context) {
        handler = new DatabaseHandler(context);
        db = handler.openDatabase();
    }

    public void cadastrar (Ingredientes ingredientes){

        ContentValues values = new ContentValues();
        values.put("nome", ingredientes.getNome());
        values.put("tx_coversao", ingredientes.getTxConversao());
        values.put("url_foto", ingredientes.getUrlFoto());

        db.insert(TABLE, null, values);
        Log.i("INSER CLIENTE", "" + ingredientes.getNome());
    }

    public void deletar(Client cliente) {
        String[] args = {cliente.getId().toString(), cliente.getNome()};
        db.delete(TABLE, "id=? and nome=?", args);
        Log.i("DELETA CLIENTE", "CLIENTE DELETADO:" + cliente.getNome());
    }

    public void Alterar (Ingredientes ingredientes) {
        ContentValues values = new ContentValues();
        values.put("nome", ingredientes.getNome());
        values.put("tx_coversao", ingredientes.getTxConversao());


        String[] agrs = {ingredientes.getId().toString()};

        db.update(TABLE, values, "id = ?", agrs);

        Log.i("Alterar CLIENTE","Alterção em cadastro do usuario : " + ingredientes.getNome());
    }

    public List<Ingredientes> listar() {
        List<Ingredientes> lista = new ArrayList<Ingredientes>();

        String sql = "select * from " + TABLE + " order by nome";

        Cursor cursor = handler.getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Ingredientes ingredientes = new Ingredientes();
                ingredientes.setId(cursor.getLong(0));
                ingredientes.setNome(cursor.getString(1));
                ingredientes.setTxConversao(cursor.getDouble(2));
                ingredientes.setUrlFoto(cursor.getString(3));
                lista.add(ingredientes);
            }
        } catch (SQLiteException e) {
            Log.e("Lista INGREDIENTES ", e.getMessage());

        } finally {
            cursor.close();
        }

        return lista;
    }


}

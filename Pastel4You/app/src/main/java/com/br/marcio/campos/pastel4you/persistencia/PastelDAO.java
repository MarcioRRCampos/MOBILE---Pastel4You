package com.br.marcio.campos.pastel4you.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.br.marcio.campos.pastel4you.domain.Pastel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcio on 18/11/2015.
 */
public class PastelDAO extends SQLiteOpenHelper{
    private static final String TAG = "sql";
    //Nome do banco
    private static final String NOME_BD = "pastel_4_you.sqlite";
    private static final int VERSAO_BANCO = 1;


    public PastelDAO(Context context) {
        // context, nome do banco, factory, versão
        super(context, NOME_BD, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela Pastel...");

        db.execSQL("create table if not exists pastel " +
                        "(_id long primary key autoincrement," +
                        "nm_pastel text, url_img text," +
                        "id_cliente long, valor_pastel double);"
        );

        Log.d(TAG, "Tabela carro criada com sucesso.");
    }

    public PastelDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso mude a versão do banco de dados, podemos executar um SQL aqui
        if (oldVersion == 1 && newVersion == 2) {
            // Execute o script para atualizar a versão...
        }
    }
    // Insere ou atualiza
    public long save(Pastel pastel) {
        long id = pastel.getId();
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            //values.put("id", pastel.getId());
            values.put("nm_pastel", pastel.getNamePastel());
            values.put("id_cliente", pastel.getId_cliente());
            values.put("url_img", pastel.getUrlFoto());
            values.put("valor_pastel", pastel.getPreco());

            if (id != 0) {

                String _id = String.valueOf(pastel.getId());
                String[] whereArgs = new String[]{_id};

                // update carro set values = ... where _id=?
                int count = db.update("pastel", values, "_id=?", whereArgs);

                return count;
            } else {
                // insert into carro values (...)
                id = db.insert("pastel", "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }


    //Delete pastel
    public int delete(Pastel pastel) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // Deleta pelo ID
            int count = db.delete("pastel", "_id=?", new String[]{String.valueOf(pastel.getId())});
            Log.i(TAG, "Deletou [" + count + "] registros");
            return count;
        } finally {
            db.close();
        }
    }

    // Lê o cursor e cria a lista de carros
    private List<Pastel> toList(Cursor pas) {
        List<Pastel> pastels = new ArrayList<Pastel>();

        if (pas.moveToFirst()) {
            do {
                Pastel pastel = new Pastel();
                pastels.add(pastel);

                // recupera os atributos do pastel
                pastel.setId(pas.getLong(pas.getColumnIndex("_id"))) ;
                pastel.setNome(pas.getString(pas.getColumnIndex("nm_pastel")));


            } while (pas.moveToNext());
        }

        return pastels;
    }

}

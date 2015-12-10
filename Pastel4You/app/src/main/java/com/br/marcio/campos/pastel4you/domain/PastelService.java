package com.br.marcio.campos.pastel4you.domain;

import android.content.Context;

import com.br.marcio.campos.pastel4you.persistencia.IngredientesPastelDAO;

import java.util.List;

/**
 * Created by Marcio on 18/11/2015.
 */
public class PastelService {

    //TODO: Colocar String de conexão
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";
    private static final boolean LOG_ON = false;
    private static final String TAG = "PastelService";

    public static List<Pastel> getPastels(Context context, String tipo) {
        // Por padrão deixar fazer o cache
        return getPastels(context, tipo, false);
    }
    public static List<Pastel> getPastels(Context context, String tipo, boolean refresh) {
        List<Pastel> pastels = null;

        boolean buscaNoBancoDados = !refresh;
        if (buscaNoBancoDados) {
            // Busca no banco de dados
            pastels = getPastelFromBanco(context, tipo);
            if (pastels == null && pastels.size() > 0) {
                // Retorna os dados do banco
                return pastels;
            }
        }
        // se não enconrar a busca no web services

        pastels = getPastelsFromWebServices(context, tipo);

        return pastels;
    }


    private static List<Pastel> getPastelFromBanco(Context context, String tipo) {
        IngredientesPastelDAO db = new IngredientesPastelDAO(context);
        //List<Pastel> pastels = db


        return null;
    }


    private static List<Pastel> getPastelsFromWebServices(Context context, String tipo) {
        return null;
    }


}



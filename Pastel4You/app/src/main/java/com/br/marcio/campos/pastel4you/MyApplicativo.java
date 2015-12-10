package com.br.marcio.campos.pastel4you;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcio on 19/10/2015.
 */
public class MyApplicativo extends Application {
    private static final String TAG = "Pastel4YouApp";
    // Singleton
    private static MyApplicativo instance = null;
    // Map com o tipo do carro e o flag se precisa atualizar a lista
    private Map<String, Boolean> mapUpdate = new HashMap<String,Boolean>();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Pastel4YouApp.onCreate()");

       // facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Salva a instância para termos acesso como Singleton
        instance = this;

    }

public static MyApplicativo getInstance() {
        return instance;
        }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.d(TAG, "Pastel4YouApp.onTerminate()");
    }

    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.br.marcio.campos.pastel4you", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("PASTEL4YOU", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public void setPrecisaAtualizar(String tipo, boolean b) {
        this.mapUpdate.put(tipo, b);
    }

    public boolean isPrecisaAtualizar(String tipo) {
        if(mapUpdate.containsKey(tipo)) {
            boolean b = mapUpdate.remove(tipo);
            return b;
        }
        return false;
    }



}

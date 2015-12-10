package com.br.marcio.campos.pastel4you;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.br.marcio.campos.pastel4you.adpter.NavDrawerMenuAdapter;
import android.view.Menu;
import android.widget.Button;

import com.br.marcio.campos.pastel4you.location.gpstracker.GPSTracker;
import com.br.marcio.campos.pastel4you.location.gpstracker.GpsLocation;
import com.br.marcio.campos.pastel4you.prefs.BaseActivity;

import livroandroid.lib.fragment.NavigationDrawerFragment;

public class EntrarActivity extends BaseActivity{
    public static final int INDEX_CUSTOM_LOGIN = 1;
    private FragmentManager mFragmentManager;
    public static final String FRAGMENT_TAG = "fragment_tag";
    public static final String REGISTER_TAG = "register_tag";
    public Button login;

    private NavigationDrawerFragment mNavDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;
    Fragment fragment;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        log("ON NEW INTENT!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_inicial);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        // Setando o Toolbar para a classe
        setUpToolbar();

        replaceFragment(new MainFragmentLogin());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private GpsLocation getMyLocation() {
        // GPSTracker class
        GPSTracker gps = new GPSTracker(EntrarActivity.this);
        GpsLocation location = null;

        // Verificando se o GPS está habilitado
        if(gps.canGetLocation()){
            location = new GpsLocation();
            location.setLatitude(gps.getLatitude());
            location.setLongitude(gps.getLongitude());

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        return location;
    }

    // Adiciona o fragment no centro da tela
    private void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_drawer_container, frag, "TAG").commit();
    }

}

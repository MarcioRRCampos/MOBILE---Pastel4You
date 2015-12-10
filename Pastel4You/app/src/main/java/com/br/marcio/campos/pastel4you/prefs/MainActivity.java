package com.br.marcio.campos.pastel4you.prefs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;

import com.br.marcio.campos.pastel4you.AccountFragment;
import com.br.marcio.campos.pastel4you.ListIngredientes;
import com.br.marcio.campos.pastel4you.MainFragmentLogin;
import com.br.marcio.campos.pastel4you.UserSessionManager;
import com.br.marcio.campos.pastel4you.adpter.NavDrawerMenuAdapter;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.br.marcio.campos.pastel4you.R;
import com.br.marcio.campos.pastel4you.adpter.NavDrawerMenuItem;
import com.br.marcio.campos.pastel4you.location.gpstracker.GPSTracker;
import com.br.marcio.campos.pastel4you.location.gpstracker.GpsLocation;

import java.util.HashMap;
import java.util.List;

import livroandroid.lib.fragment.NavigationDrawerFragment;
import livroandroid.lib.utils.AndroidUtils;

public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    public static final int INDEX_CUSTOM_LOGIN = 1;
    private FragmentManager mFragmentManager;
    public static final String FRAGMENT_TAG = "fragment_tag";
    public static final String REGISTER_TAG = "register_tag";
    public Button login;

    private NavigationDrawerFragment mNavDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;

    // User Session Manager Class
    UserSessionManager session;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        log("ON NEW INTENT!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new UserSessionManager(getApplicationContext());

        if(session.checkLogin())
            finish();

        FragmentManager supportFragmentManager = getSupportFragmentManager();

        // Setando o Toolbar para a classe
        setUpToolbar();

        // Nav Drawer -- passa o id do NAv do menu inicial onde se encontra o meu menu em layout
        mNavDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment);

        // Congifigura o drawer layout a barra de menu pro layout onde ela pode ser chamada
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.primary_dark);
        mNavDrawerFragment.setUp(drawerLayout);

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


    // Deve retornar a view e o indetificador
    @Override
    public NavigationDrawerFragment.NavDrawerListView getNavDrawerView(NavigationDrawerFragment navDrawerFrag, LayoutInflater layoutInflater, ViewGroup container) {
        View view = layoutInflater.inflate(R.layout.nav_drawer_listview, container, false);

        // Logo redonda do menu lateral
        navDrawerFrag.setHeaderValues(view, R.id.listViewContainer, R.drawable.pastel_textura, (R.drawable.icon_pastel_null), R.string.nav_drawer_username, R.string.nav_drawer_email);

        return new NavigationDrawerFragment.NavDrawerListView(view, R.id.listView);
    }


    @Override
    public ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navigationDrawerFragment) {
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        // Deixa o primeiro item selecionado
        list.get(0).selected = true;
        this.listAdapter = new NavDrawerMenuAdapter(this, list);
        return listAdapter;
    }

    @Override
    public void onNavDrawerItemSelected(NavigationDrawerFragment navigationDrawerFragment, int position) {
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        NavDrawerMenuItem selectedItem = list.get(position);

        // Seleciona a linha do menu lateral
        this.listAdapter.setSelected(position, true);

        if (position == 0) {
            //Conta -- Registro -- Account

            replaceFragment(new AccountFragment());

        } else if (position == 1) {
            startActivity(new Intent(this, ListIngredientes.class));
        } else if (position == 2) {
            if (AndroidUtils.isAndroid3Honeycomb()) {
                //startActivity(new Intent(this, ConfiguracoesV11Activivity.class));
            } else {
                //startActivity(new Intent(this, ConfiguracoesActivivity.class));
            }
        } else {
            Log.e("Pastel4You", "Item de menu inválido");
        }
    }

    // Adiciona o fragment no centro da tela
    private void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_drawer_container, frag, "TAG").commit();
    }




}

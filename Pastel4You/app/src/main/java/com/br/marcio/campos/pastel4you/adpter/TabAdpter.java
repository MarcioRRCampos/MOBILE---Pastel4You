package com.br.marcio.campos.pastel4you.adpter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.br.marcio.campos.pastel4you.fragments.PasteisFragment;

/**
 * Created by Marcio on 17/11/2015.
 */
public class TabAdpter extends android.support.v4.app.FragmentPagerAdapter{

    private Context context;
    
    // PASSANDO O CONTEXTO PARA VARIAVEL
    public TabAdpter(Context context, android.support.v4.app.FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public int getContext() {
        return 3 ;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if(position == 0){
            args.putString("tipo","promocao");
        }else if (position == 1){
            args.putString("tipo","cardapio");
        }else {
            args.putString("tipo","ingredientes");
        }
        Fragment f = new PasteisFragment();
        f.setArguments(args);
        return f;

    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){

            return "Promocao";
        }else if(position == 2){
            return "cardapio";
        }

        return "ingredientes";
    }

    @Override
    public int getCount() {
        return 0;
    }
}

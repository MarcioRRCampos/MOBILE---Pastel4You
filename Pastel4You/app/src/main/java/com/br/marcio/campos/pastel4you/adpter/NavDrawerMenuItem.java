package com.br.marcio.campos.pastel4you.adpter;

import com.br.marcio.campos.pastel4you.R;

import java.util.ArrayList;
import java.util.List;
import com.br.marcio.campos.pastel4you.R;
/**
 * Created by Marcio on 08/11/2015.
 */
public class NavDrawerMenuItem {
    // Titulo
    public int title;
    // Figura
    public int img;
    //Para colocar um fundo cinza quando a linha está selecionada
    public boolean selected;

    public NavDrawerMenuItem(int title, int img){
        this.title = title;
        this.img = img;
    }

    // Criar a listar com os itens de menu
    public static List<NavDrawerMenuItem> getList() {
        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
        list.add(new NavDrawerMenuItem(R.string.Conta, R.drawable.ic_account_grey600_36dp));
        list.add(new NavDrawerMenuItem(R.string.Ingredientes, R.drawable.ic_food_variant_grey600_36dp));
        list.add(new NavDrawerMenuItem(R.string.Cardapio, R.drawable.ic_food_grey600_36dp));
        list.add(new NavDrawerMenuItem(R.string.settings, R.drawable.ic_settings_grey600_36dp));

        return list;
    }


}

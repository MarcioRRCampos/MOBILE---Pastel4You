package com.br.marcio.campos.pastel4you;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.marcio.campos.pastel4you.domain.Ingredientes;

import java.util.List;


/**
 * Created by rogeriofontes on 9/8/15.
 */
public class IngredienteAdapter extends BaseAdapter {

    private List<Ingredientes> listIngredientes = null;
    private Context context = null;

    public IngredienteAdapter(List<Ingredientes> listIngredientes, Context context) {
        this.listIngredientes = listIngredientes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listIngredientes.size();
    }

    @Override
    public Object getItem(int position) {
        return listIngredientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return atividades.get(position).getId();
        return 0;
    }

    @Override
    public View getView(int position, View convetView, ViewGroup viewGroup) {
        Ingredientes ingrediente = listIngredientes.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingrediente_item, null);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_selecao);

        TextView textoLView = (TextView) view.findViewById(R.id.textoLView);

        textoLView.setText(ingrediente.getNome());
        return view;
    }
}

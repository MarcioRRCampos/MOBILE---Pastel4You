package com.br.marcio.campos.pastel4you;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.br.marcio.campos.pastel4you.domain.Ingredientes;
import com.br.marcio.campos.pastel4you.persistencia.IngredientesDAO;


import java.util.ArrayList;
import java.util.List;

public class ListIngredientes extends AppCompatActivity {

    private IngredienteAdapter ingredienteAdapter;
    private List<Ingredientes> ingredientes = null;
    ListView listaIngredientes;

    IngredientesDAO dao;

    ArrayList<Ingredientes> liststatic = new ArrayList();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // other setup code


        setContentView(R.layout.activity_list_ingredientes);

        Ingredientes Queijo = new Ingredientes();
        Queijo.setNome("Queijo");
        liststatic.add(Queijo);
        Ingredientes Frango = new Ingredientes();
        Frango.setNome("Frango");
        liststatic.add(Frango);
        Ingredientes Carne = new Ingredientes();
        Carne.setNome("Carne");
        liststatic.add(Carne);
        Ingredientes Requeijão = new Ingredientes();
        Requeijão.setNome("Requeijão");
        liststatic.add(Requeijão);
        Ingredientes Cheder = new Ingredientes();
        Cheder.setNome("Cheder");
        liststatic.add(Cheder);
        Ingredientes Queiroba = new Ingredientes();
        Queiroba.setNome("Queiroba");
        liststatic.add(Queiroba);

        ListView listaIngredientes = (ListView) findViewById(R.id.lista_ingredietes);
        dao = new IngredientesDAO(this);

        List<Ingredientes> ingredientes = liststatic;

       //  List<Ingredientes> ingredientes = dao.listar();

        if (ingredientes != null) {
            ingredienteAdapter = new IngredienteAdapter(ingredientes, this);
            listaIngredientes.setAdapter(ingredienteAdapter);
            // registerForContextMenu(ingredienteAdapter);
        }



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_ingredientes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

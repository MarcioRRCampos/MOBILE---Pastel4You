package com.br.marcio.campos.pastel4you.domain;

import java.util.List;

/**
 * Created by Marcio on 12/11/2015.
 */
public class Pastel extends Ingredientes {

    String namePastel;

    // Flag para a action bar de contexto
    public boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    List<Ingredientes> ListIngredientes;

    double preco;

    Long id_cliente;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }


    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNamePastel() {
        return namePastel;
    }

    public void setNamePastel(String namePastel) {
        this.namePastel = namePastel;
    }

    public List<Ingredientes> getListIngredientes() {
        return ListIngredientes;
    }

    public void setListIngredientes(List<Ingredientes> listIngredientes) {
        ListIngredientes = listIngredientes;
    }

    @Override
    public String toString() {
        return "Pastel{" +
                "namePastel='" + namePastel + '\'' +
                ", ListIngredientes=" + ListIngredientes +
                '}';
    }
}

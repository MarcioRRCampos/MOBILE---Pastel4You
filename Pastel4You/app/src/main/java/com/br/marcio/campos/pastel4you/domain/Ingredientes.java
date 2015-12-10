package com.br.marcio.campos.pastel4you.domain;

import java.io.Serializable;

/**
 * Created by Marcio on 07/11/2015.
 */
public class Ingredientes implements Serializable {

    private Long id;
    private String nome;
    private double txConversao;
    private String urlFoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTxConversao() {
        return txConversao;
    }

    public void setTxConversao(double txConversao) {
        this.txConversao = txConversao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", txConversao=" + txConversao +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }
}

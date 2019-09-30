/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import chain.Processadora;

/**
 *
 * @author 
 */
public class Cliente {
    
    private final int id;
    private String nome;
    private int idade;
    private double renda;
    private double limiteDeCredito;
    private String classificacao;

    public Cliente(int id, String nome, int idade, double renda) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.renda = renda;
        limiteDeCredito = renda * 0.4;
        classificacao = Processadora.INSTANCE.processar(renda);
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getRenda() {
        return renda;
    }

    public double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setRenda(double renda) {
        this.renda = renda;
        limiteDeCredito = renda * 0.4;
        classificacao = Processadora.INSTANCE.processar(renda);
    }
}

package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;

public class Modelo implements PersisteDados {
    
    //Atributos
    private int id;
    private String nome;
    private Marca marca;
    
    //Construtores
    public Modelo() {
        this.id = 0;
        this.nome = "";
        this.marca = null;
    }
    
    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    //Métodos Próprios
    
    //Métodos Herdados
    @Override
    public Object monteObjeto(String dadosDoObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String desmonteObjeto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

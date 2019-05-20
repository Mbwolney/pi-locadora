package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.util.List;

public class Endereco extends PersisteDados {
    
    //Atributos
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    
    //Construtores
    public Endereco() {
        super();
        this.logradouro = "";
        this.numero = "";
        this.complemento = "";
        this.bairro = "";
        this.cep = "";
    }
    
    //Getters e Setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    //Métodos Próprios
    
    //Métodos herdados
    @Override
    public void monteObjeto(String dadosDoObjeto) {
		String dadosPropriosDesteObjeto = RegexUtils.removaAgregacoes(dadosDoObjeto);
		dadosPropriosDesteObjeto = RegexUtils.removaNomeEntidade(dadosPropriosDesteObjeto);
		List<String> campos = Utils.obtenhaCampos(dadosPropriosDesteObjeto);

		this.id = Utils.convertaParaInt(campos.get(0));
        this.logradouro = campos.get(1);
        this.numero = campos.get(2);
        this.complemento = campos.get(3);
        this.bairro = campos.get(4);
        this.cep = campos.get(5);
    }

    @Override
    public String desmonteObjeto() {
		StringBuilder atributos = new StringBuilder();

		String nomeEntidade = RegexUtils.separeComoCampo(this.getClass().getSimpleName());
		String campoId = RegexUtils.separeComoCampo(this.getIdComoString());
		String campoLogradouro = RegexUtils.separeComoCampo(this.logradouro);
		String campoNumero = RegexUtils.separeComoCampo(this.numero);
		String campoComplemento = RegexUtils.separeComoCampo(this.complemento);
		String campoBairro = RegexUtils.separeComoCampo(this.bairro);
		String campoCep = RegexUtils.separeComoCampo(this.cep);

		atributos.append(nomeEntidade);
		atributos.append(campoId);
		atributos.append(campoLogradouro);
		atributos.append(campoNumero);
		atributos.append(campoComplemento);
		atributos.append(campoBairro);
		atributos.append(campoCep);

		return atributos.toString();
    }
}

package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.util.List;

public class Categoria extends PersisteDados {

    //Atributos
    private String nome;
    private float valorDiarioLocacao;
    
    //Construtores
    public Categoria() {
        super();
        this.nome = "";
        this.valorDiarioLocacao = 0;
    }
    
    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValorDiarioLocacao() {
        return valorDiarioLocacao;
    }

    public void setValorDiarioLocacao(float valorDiarioLocacao) {
        this.valorDiarioLocacao = valorDiarioLocacao;
    }
    
    //Métodos Próprios
    
    //Métodos Herdados    
    @Override
    public void monteObjeto(String dadosDoObjeto) {
		String dadosPropriosDesteObjeto = RegexUtils.removaAgregacoes(dadosDoObjeto);
		dadosPropriosDesteObjeto = RegexUtils.removaNomeEntidade(dadosPropriosDesteObjeto);
		List<String> campos = Utils.obtenhaCampos(dadosPropriosDesteObjeto);
		List<String> agregacoes = RegexUtils.extraiaAgregacoes(dadosDoObjeto);

		this.id = Utils.convertaParaInt(campos.get(0));
		this.nome = campos.get(1);
		this.valorDiarioLocacao = Utils.convertaParaFloat(campos.get(2));
    }

    @Override
    public String desmonteObjeto() {
		StringBuilder atributos = new StringBuilder();

		String nomeEntidade = RegexUtils.separeComoCampo(this.getClass().getSimpleName());
		String campoId = RegexUtils.separeComoCampo(this.getIdComoString());
		atributos.append(nomeEntidade);
		atributos.append(campoId);

		if(this.id == 0) {
			String campoNome = RegexUtils.separeComoCampo(this.nome);
			String campoValorLocacao = RegexUtils.separeComoCampo(String.valueOf(this.valorDiarioLocacao));
			atributos.append(campoNome);
			atributos.append(campoValorLocacao);
		}

		return atributos.toString();
    }
}

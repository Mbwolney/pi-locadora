package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.util.List;

public class Marca extends PersisteDados {
    
    //Atributos
    private String nome;
    
    //Construtores
    public Marca() {
        super();
        this.nome = "";
    }
    
    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Métodos Próprios
    
    //Métodos Herdados
    @Override
    public void monteObjeto(String dadosDoObjeto) {
		String dadosPropriosDesteObjeto = RegexUtils.removaAgregacoes(dadosDoObjeto);
		dadosPropriosDesteObjeto = RegexUtils.removaNomeEntidade(dadosPropriosDesteObjeto);
		List<String> campos = Utils.obtenhaCampos(dadosPropriosDesteObjeto);

        this.id = Utils.convertaParaInt(campos.get(0));
        this.nome = campos.get(1);
    }

    @Override
    public String desmonteObjeto() {
        StringBuilder atributos = new StringBuilder();

        String nomeEntidade = RegexUtils.separeComoCampo(this.getClass().getSimpleName());
        String campoId = RegexUtils.separeComoCampo(this.getIdComoString());

		atributos.append(nomeEntidade);
		atributos.append(campoId);

        if(this.getId() == 0) {
			String campoNome = RegexUtils.separeComoCampo(this.nome);
			atributos.append(campoNome);
		}

        return atributos.toString();
    }

}

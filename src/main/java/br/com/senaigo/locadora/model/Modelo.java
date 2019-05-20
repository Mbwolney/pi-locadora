package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.util.List;

public class Modelo extends PersisteDados {
    
    //Atributos
    private String nome;
    private Marca marca;
    
    //Construtores
    public Modelo(Marca marca) {
        super();
        this.nome = "";
        this.marca = marca;
    }

	public Modelo() {
		super();
		this.nome = "";
		this.marca = null;
	}
    
    //Getters e Setters
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
    public void monteObjeto(String dadosDoObjeto) {
    	//Sempre chega Entidade;Id;Dados{DadosAgregações}
		String dadosPropriosDesteObjeto = RegexUtils.removaAgregacoes(dadosDoObjeto);
		dadosPropriosDesteObjeto = RegexUtils.removaNomeEntidade(dadosPropriosDesteObjeto);
		List<String> campos = Utils.obtenhaCampos(dadosPropriosDesteObjeto);
		List<String> agregacoes = RegexUtils.extraiaAgregacoes(dadosDoObjeto);

        this.id = Utils.convertaParaInt(campos.get(0));
        this.nome = campos.get(1);
        Marca marca = (Marca) PersisteDadosFactory.obtenhaInstancia("Marca");
		marca.monteObjeto(agregacoes.get(0));
		this.marca = marca;
    }

    @Override
    public String desmonteObjeto() {
    	StringBuilder atributos = new StringBuilder();

		String nomeEntidade = RegexUtils.separeComoCampo(this.getClass().getSimpleName());
		String campoId = RegexUtils.separeComoCampo(this.getIdComoString());
		String campoNome = RegexUtils.separeComoCampo(this.nome);
		String campoMarca = RegexUtils.separeComoAgregacao(marca.desmonteObjeto());

    	atributos.append(nomeEntidade);
    	atributos.append(campoId);
    	atributos.append(campoNome);
    	atributos.append(campoMarca);

    	return atributos.toString();
    }
    
    
    
}

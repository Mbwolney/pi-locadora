package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.util.List;

public class Veiculo extends PersisteDados {

	//Atributos
	private String placa;
	private long renavam;
	private int anoFabricacao;
	private float valorCompra;
	private int kmAtual;
	private Categoria categoria;
	private Estado estado;
	private Modelo modelo;

	//Construtores
	public Veiculo() {
		super();
		this.placa = "";
		this.renavam = 0;
		this.anoFabricacao = 0;
		this.valorCompra = 0;
		this.kmAtual = 0;
		this.categoria = null;
		this.estado = null;
		this.modelo = null;
	}

	//Getters e Setters
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public long getRenavam() {
		return renavam;
	}

	public void setRenavam(long renavam) {
		this.renavam = renavam;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public int getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(int kmAtual) {
		this.kmAtual = kmAtual;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	//Métodos próprios

	//Métodos Herdados
	@Override
	public void monteObjeto(String dadosDoObjeto) {
		String dadosPropriosDesteObjeto = RegexUtils.removaAgregacoes(dadosDoObjeto);
		dadosPropriosDesteObjeto = RegexUtils.removaNomeEntidade(dadosPropriosDesteObjeto);
		List<String> campos = Utils.obtenhaCampos(dadosPropriosDesteObjeto);
		List<String> agregacoes = RegexUtils.extraiaAgregacoes(dadosDoObjeto);

		this.id = Utils.convertaParaInt(campos.get(0));
		this.placa = campos.get(1);
		this.renavam = Utils.convertaParaLong(campos.get(2));
		this.anoFabricacao = Utils.convertaParaInt(campos.get(3));
		this.valorCompra = Utils.convertaParaFloat(campos.get(4));
		this.kmAtual = Utils.convertaParaInt(campos.get(5));
		Categoria categoria = (Categoria) PersisteDadosFactory.obtenhaInstancia("Categoria");
		categoria.monteObjeto(agregacoes.get(0));
		this.categoria = categoria;
		Estado estado = Estado.valueOf(Utils.convertaParaInt(campos.get(6)));
		this.estado = estado;
		Modelo modelo = (Modelo) PersisteDadosFactory.obtenhaInstancia("Modelo");
		modelo.monteObjeto(agregacoes.get(1));
		this.modelo = modelo;
	}

	@Override
	public String desmonteObjeto() {
		StringBuilder atributos = new StringBuilder();

		String nomeEntidade = RegexUtils.separeComoCampo(this.getClass().getSimpleName());
		String campoId = RegexUtils.separeComoCampo(this.getIdComoString());

		atributos.append(nomeEntidade);
		atributos.append(campoId);

		if(this.id == 0) {
			String campoPlaca = RegexUtils.separeComoCampo(this.placa);
			String campoRenavam = RegexUtils.separeComoCampo(String.valueOf(this.renavam));
			String campoAnoFabricacao = RegexUtils.separeComoCampo(String.valueOf(anoFabricacao));
			String campoValorCompra = RegexUtils.separeComoCampo(String.valueOf(this.valorCompra));
			String campoKmAtual = RegexUtils.separeComoCampo(String.valueOf(this.kmAtual));
			String campoCategoria = this.categoria.getId() == 0 ? RegexUtils.separeComoAgregacao(categoria.desmonteObjeto()) : RegexUtils.separeComoAgregacao(categoria.getIdComoString());
			String campoEstado = RegexUtils.separeComoCampo(String.valueOf(this.estado.getValor()));
			String campoModelo = RegexUtils.separeComoAgregacao(this.modelo.desmonteObjeto());

			atributos.append(campoPlaca);
			atributos.append(campoRenavam);
			atributos.append(campoAnoFabricacao);
			atributos.append(campoValorCompra);
			atributos.append(campoKmAtual);
			atributos.append(campoCategoria);
			atributos.append(campoEstado);
			atributos.append(campoModelo);
		}
		return atributos.toString();
	}
}

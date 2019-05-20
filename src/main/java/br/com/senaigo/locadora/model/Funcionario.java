package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.DataUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class Funcionario extends PersisteDados {

	//Atributos
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	private Endereco endereco;
	private Telefone telefonePrincipal;
	private Telefone telefoneAlternativo;
	private String email;
	private String rg;
	private String carteiraDeTrabalho;
	private String login;
	private String senha;

	public Funcionario() {
		super();
		this.nome = "";
		this.dataNascimento = null;
		this.cpf = "";
		this.endereco = null;
		this.telefonePrincipal = null;
		this.telefoneAlternativo = null;
		this.email = "";
		this.rg = "";
		this.carteiraDeTrabalho = "";
		this.login = "";
		this.senha = "";
	}

	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(Telefone telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public Telefone getTelefoneAlternativo() {
		return telefoneAlternativo;
	}

	public void setTelefoneAlternativo(Telefone telefoneAlternativo) {
		this.telefoneAlternativo = telefoneAlternativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCarteiraDeTrabalho() {
		return carteiraDeTrabalho;
	}

	public void setCarteiraDeTrabalho(String carteiraDeTrabalho) {
		this.carteiraDeTrabalho = carteiraDeTrabalho;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		this.dataNascimento = DataUtils.convertaStringParaLocalDate(campos.get(2));
		this.cpf = campos.get(3);
		Endereco endereco = (Endereco) PersisteDadosFactory.obtenhaInstancia("Endereco");
		endereco.monteObjeto(agregacoes.get(0));
		this.endereco = endereco;
		Telefone telefonePrincipal = (Telefone) PersisteDadosFactory.obtenhaInstancia("Telefone");
		telefonePrincipal.monteObjeto(agregacoes.get(1));
		this.telefonePrincipal = telefonePrincipal;
		Telefone telefoneAlternativo = (Telefone) PersisteDadosFactory.obtenhaInstancia("Telefone");
		telefoneAlternativo.monteObjeto(agregacoes.get(2));
		this.telefoneAlternativo = telefoneAlternativo;
		this.email = campos.get(4);
		this.rg = campos.get(5);
		this.carteiraDeTrabalho = campos.get(6);
		this.login = campos.get(7);
		this.senha = campos.get(8);
	}

	@Override
	public String desmonteObjeto() {
		StringBuilder atributos = new StringBuilder();

		String nomeEntidade = RegexUtils.separeComoCampo(this.getClass().getSimpleName());
		String campoId = RegexUtils.separeComoCampo(this.getIdComoString());

		atributos.append(nomeEntidade);
		atributos.append(campoId);

		if (id == 0) {
			String campoNome = RegexUtils.separeComoCampo(this.nome);
			String campoDataNascimento = RegexUtils.separeComoCampo(DataUtils.convertaLocalDateParaStringFormatada(this.dataNascimento));
			String campoCpf = RegexUtils.separeComoCampo(this.cpf);
			String campoEndereco = this.endereco.getId() == 0 ? RegexUtils.separeComoAgregacao(endereco.desmonteObjeto()) : RegexUtils.separeComoAgregacao(endereco.getIdComoString());
			String campoTelefonePrincipal = this.telefonePrincipal.getId() == 0 ? RegexUtils.separeComoAgregacao(telefonePrincipal.desmonteObjeto()) : RegexUtils.separeComoAgregacao(telefonePrincipal.getIdComoString());
			String campoTelefoneAlternativo = this.telefoneAlternativo.getId() == 0 ? RegexUtils.separeComoAgregacao(telefoneAlternativo.desmonteObjeto()) : RegexUtils.separeComoAgregacao(telefoneAlternativo.getIdComoString());
			String campoEmail = RegexUtils.separeComoCampo(this.email);
			String campoRg = RegexUtils.separeComoCampo(this.rg);
			String campoCarteiraTrabalho = RegexUtils.separeComoCampo(this.carteiraDeTrabalho);
			String campoLogin = RegexUtils.separeComoCampo(this.login);
			String campoSenha = RegexUtils.separeComoCampo(this.senha);

			atributos.append(campoNome);
			atributos.append(campoDataNascimento);
			atributos.append(campoCpf);
			atributos.append(campoEndereco);
			atributos.append(campoTelefonePrincipal);
			atributos.append(campoTelefoneAlternativo);
			atributos.append(campoEmail);
			atributos.append(campoRg);
			atributos.append(campoCarteiraTrabalho);
			atributos.append(campoLogin);
			atributos.append(campoSenha);
		}

		return atributos.toString();
	}
}

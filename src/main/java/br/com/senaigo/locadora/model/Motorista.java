package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.DataUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class Motorista extends PersisteDados {
    
    //Atributos
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private Endereco endereco;
    private Telefone telefonePrincipal;
    private Telefone telefoneAlternativo;
    private String email;
    private String cnh;
    
    //Construtores
    public Motorista() {
        super();
        this.nome = "";
        this.dataNascimento = null;
        this.cpf = "";
        this.endereco = null;
        this.telefonePrincipal = null;
        this.telefoneAlternativo = null;
        this.email = "";
        this.cnh = "";        
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {    
        this.cnh = cnh;
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
		this.cpf = campos.get(2);
		this.dataNascimento = DataUtils.convertaStringParaLocalDate(campos.get(3));
		Endereco endereco = (Endereco) PersisteDadosFactory.obtenhaInstancia("Endereco");
		endereco.monteObjeto(agregacoes.get(0));
		this.endereco = endereco;
		Telefone telefonePrincipal = (Telefone) PersisteDadosFactory.obtenhaInstancia("Telefone");
		telefonePrincipal.monteObjeto(agregacoes.get(1));
		this.telefonePrincipal = telefonePrincipal;
		Telefone telefoneAlternativo = (Telefone) PersisteDadosFactory.obtenhaInstancia("Telefone");
		telefoneAlternativo.monteObjeto(agregacoes.get(2));
		this.telefoneAlternativo = telefoneAlternativo;
		this.email = campos.get(3);
		this.cnh = campos.get(4);
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
			String campoCpf = RegexUtils.separeComoCampo(this.cpf);
			String campoDataNascimento = RegexUtils.separeComoCampo(DataUtils.convertaLocalDateParaStringFormatada(this.dataNascimento));
			String campoEndereco = this.endereco.getId() == 0 ? RegexUtils.separeComoAgregacao(endereco.desmonteObjeto()) : RegexUtils.separeComoAgregacao(endereco.getIdComoString());
			String campoTelefonePrincipal = this.telefonePrincipal.getId() == 0 ? RegexUtils.separeComoAgregacao(telefonePrincipal.desmonteObjeto()) : RegexUtils.separeComoAgregacao(telefonePrincipal.getIdComoString());
			String campoTelefoneAlternativo = this.telefoneAlternativo.getId() == 0 ? RegexUtils.separeComoAgregacao(telefoneAlternativo.desmonteObjeto()) : RegexUtils.separeComoAgregacao(telefoneAlternativo.getIdComoString());
			String campoEmail = RegexUtils.separeComoCampo(this.email);
			String campoCnh = RegexUtils.separeComoCampo(this.cnh);

			atributos.append(campoNome);
			atributos.append(campoCpf);
			atributos.append(campoDataNascimento);
			atributos.append(campoEndereco);
			atributos.append(campoTelefonePrincipal);
			atributos.append(campoTelefoneAlternativo);
			atributos.append(campoEmail);
			atributos.append(campoCnh);
		}

		return atributos.toString();
    }
    
}

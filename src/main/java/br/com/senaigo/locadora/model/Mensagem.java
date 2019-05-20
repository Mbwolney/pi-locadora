package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.persistencia.Operacao;
import br.com.senaigo.locadora.utils.RegexUtils;

import java.util.ArrayList;
import java.util.List;

public class Mensagem {

	private int operacao;
	private String nomeEntidade;
	private String dadosObjetoPrincipal;
	private List<String> dadosObjetosAgregados;
	private String requisicaoOriginal;

	public Mensagem(String requisicao) {
		this.requisicaoOriginal = requisicao;
		this.operacao = RegexUtils.extraiaOperacao(requisicao);
		this.nomeEntidade = RegexUtils.extraiaNomeEntidade(requisicao);
		if(operacao != Operacao.LISTAR.getValor()) {
			this.dadosObjetoPrincipal = RegexUtils.removaOperacao(requisicao);
			this.dadosObjetosAgregados = obtenhaObjetosAgregados(requisicao);
		}
	}

	public int getOperacao() {
		return operacao;
	}

	public String getNomeEntidade() {
		return nomeEntidade;
	}

	public String getDadosObjetoPrincipal() {
		return dadosObjetoPrincipal;
	}

	public List<String> getDadosObjetosAgregados() {
		return dadosObjetosAgregados;
	}

	public void setDadosObjetosAgregados(List<String> dadosObjetosAgregados) {
		this.dadosObjetosAgregados = dadosObjetosAgregados;
	}

	private List<String> obtenhaObjetosAgregados(String requisicao) {
		List<String> dados = RegexUtils.extraiaAgregacoes(requisicao);
		List<String> objetosAgregados = new ArrayList<>();

		for (String dado : dados) {
			if (!dado.contains("{")) {
				objetosAgregados.add(dado);
			} else {
				String dadoSemAgregacoes = RegexUtils.removaAgregacoes(dado);
				dados.add(dadoSemAgregacoes);
				dados.addAll(RegexUtils.extraiaAgregacoes(dado));
			}
		}
		return objetosAgregados;
	}
}

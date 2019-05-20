package br.com.senaigo.locadora.controller;

import br.com.senaigo.locadora.model.Mensagem;
import br.com.senaigo.locadora.model.PersisteDadosFactory;
import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.persistencia.Operacao;
import br.com.senaigo.locadora.persistencia.Repositorio;
import br.com.senaigo.locadora.utils.RegexUtils;
import br.com.senaigo.locadora.utils.Utils;

import java.io.IOException;
import java.util.List;

public class ServerTcpController {

	public String atendaRequisicao(String mensagem) throws Exception {
		Mensagem parametros = new Mensagem(mensagem);
		Operacao operacao = Operacao.valueOf(parametros.getOperacao());
		String resposta = "";

		if (parametros == null) {
			throw new IllegalArgumentException("Parametros invalidos.");
		}

		if (operacao == null) {
			throw new IllegalArgumentException("Operacao inválida.");
		}


		switch (operacao) {
			case INCLUIR:
				resposta = incluir(parametros);
				break;
			case ALTERAR:
				resposta = alterar(parametros);
				break;
			case EXCLUIR:
				resposta = excluir(parametros);
				break;
			case LISTAR:
				resposta = listar(parametros);
				break;
		}

		return resposta;
	}

	private String incluir(Mensagem mensagem) throws IOException {
		String nomeEntidade = mensagem.getNomeEntidade();
		Repositorio repositorio = new Repositorio(nomeEntidade);
		String dadosObjeto = mensagem.getDadosObjetoPrincipal();
		PersisteDados objeto = PersisteDadosFactory.obtenhaInstancia(nomeEntidade);
		//Os dados não podem passar daqui sem tratamento
		List<String> dadosObjetosAgregados = mensagem.getDadosObjetosAgregados();
		for(String dado : dadosObjetosAgregados) {
			int id = RegexUtils.extraiaId(dado);
			if(id != 0) {
				String nomeEntidadeAgregada = RegexUtils.extraiaNomeEntidade(dado);
				String dadosEncontrados = repositorio.busquePorId(nomeEntidadeAgregada, id);
				dadosObjeto = dadosObjeto.replace(dado, dadosEncontrados);
			}
		}
		objeto.monteObjeto(dadosObjeto);
		repositorio.incluir(objeto);
		return nomeEntidade + " incluído com sucesso.";
	}

	private String listar(Mensagem mensagem) throws IOException {
		String nomeEntidade = mensagem.getNomeEntidade();
		Repositorio repositorio = new Repositorio(nomeEntidade);
		return repositorio.listar();
	}

	private String alterar(Mensagem mensagem) throws IOException {
		String nomeEntidade = mensagem.getNomeEntidade();
		String campoAlterado = mensagem.getDadosObjetoPrincipal();
		String id = Utils.obtenhaCampos(campoAlterado).get(0);
		String regex = "(?<!.)" + id + ";.*";
		Repositorio repositorio = new Repositorio(nomeEntidade);
		String dadosAtuais = repositorio.listar();
		String dadosNovos = dadosAtuais.replaceFirst(regex, campoAlterado);
		repositorio.alterar(dadosNovos);
		return "Dados Alterados com sucesso!";
	}

	private String excluir(Mensagem mensagem) throws IOException {
		String nomeEntidade = mensagem.getNomeEntidade();
		String campoExcluir = mensagem.getDadosObjetoPrincipal();
		Repositorio repositorio = new Repositorio(nomeEntidade);
		String dadosAtuais = repositorio.listar();
		String dadosNovos = dadosAtuais.replaceFirst(campoExcluir + "\n", "");
		repositorio.alterar(dadosNovos);
		return "Dados Alterados com sucesso!";
	}
}

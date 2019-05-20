package br.com.senaigo.locadora.controller;

import br.com.senaigo.locadora.comunicacao.ClienteTcp;
import br.com.senaigo.locadora.model.PersisteDadosFactory;
import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.persistencia.Operacao;
import br.com.senaigo.locadora.utils.RegexUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteTcpController {

	private ClienteTcp conexaoComCliente;

	public ClienteTcpController() throws IOException {
		conexaoComCliente = new ClienteTcp("127.0.0.1", 7777);
	}

	public String execute(Object objeto, Operacao operacao) throws IOException {
		try {
			PersisteDados dados = (PersisteDados) objeto;
			String mensagem = RegexUtils.separeComoOperacao(operacao) + dados.desmonteObjeto();
			System.out.println("Enviando requisição: " + mensagem);
			conexaoComCliente.enviarMensagem(mensagem);
			String resposta = conexaoComCliente.receberMensagem();
			return resposta;
		} catch (Exception erro) {
			throw erro;
		}
	}

	public String execute(String nomeEntidade, Operacao operacao) throws IOException {
		//TODO buscar solução melhor que passar String
		try {
			String mensagem = RegexUtils.separeComoOperacao(operacao) + nomeEntidade;
			System.out.println("Enviando requisição: " + mensagem);
			conexaoComCliente.enviarMensagem(mensagem);
			String resposta = conexaoComCliente.receberMensagem();
			return resposta;
		} catch (Exception erro) {
			throw erro;
		}
	}

	//TODO rever o retorno do método, para object.
	public List<? extends PersisteDados> liste(String nomeEntidade) throws IOException {
		String dados = execute(nomeEntidade, Operacao.LISTAR);
		List<String> dadosObjetos = Arrays.asList(dados.split("\n"));
		PersisteDados objeto;
		List<PersisteDados> listaDeObjetos = new ArrayList<>();

		for (String dadosDeUmObjeto : dadosObjetos) {
			objeto = PersisteDadosFactory.obtenhaInstancia(nomeEntidade);
			objeto.monteObjeto(dadosDeUmObjeto);
			listaDeObjetos.add(objeto);
		}
		return listaDeObjetos;

	}
}

package br.com.senaigo.locadora.utils;

import br.com.senaigo.locadora.persistencia.Operacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	private static final String SEPARADOR_OPERACAO = "#";
	private static final String SEPARADOR_INICIO_AGREGACAO = "{";
	private static final String SEPARADOR_FIM_AGREGACAO = "}";
	private static final String SEPARADOR_CAMPOS = ";";
	private static final String REGEX_OPERACAO = "(?<=#)\\d+(?=#)";
	private static final String REGEX_AGREGACAO = "(?<=\\{).*?(?=;\\{|\\})";
	private static final String REGEX_DADOS_OBJETO_COM_AGREGACAO = "#\\d+#";
	private static final String REGEX_DADOS_OBJETO_SEM_AGREGACAO = "#\\d+#|\\{.*?\\}";
	private static final String REGEX_NOME_ENTIDADE = "^\\w+";
	private static final String REGEX_ID = "\\d+";
	private static final String REGEX_PESQUISA_ID = "^\\d+;.*";

	public static String separeComoOperacao(Operacao operacao) {
		StringBuilder execucao = new StringBuilder();
		execucao.append(SEPARADOR_OPERACAO);
		execucao.append(operacao.getValor());
		execucao.append(SEPARADOR_OPERACAO);

		return execucao.toString();
	}


	public static String separeComoAgregacao(String dadosObjetoAgregado) {
		StringBuilder agregacao = new StringBuilder();

		agregacao.append(SEPARADOR_INICIO_AGREGACAO);
		agregacao.append(dadosObjetoAgregado);
		agregacao.append(SEPARADOR_FIM_AGREGACAO);

		return agregacao.toString();
	}

	public static int extraiaId(String dadosObjeto) {
		String mensagemSemEntidade = removaNomeEntidade(dadosObjeto);
		return Utils.convertaParaInt(extraiaUmResultado(REGEX_ID, mensagemSemEntidade));
	}

	public static String separeComoCampo(String campoSemSeparador) {
		return campoSemSeparador + SEPARADOR_CAMPOS;
	}

	public static int extraiaOperacao(String mensagem) {
		String operacaoEncontrada = extraiaUmResultado(REGEX_OPERACAO, mensagem);

		if(operacaoEncontrada.equals("") || operacaoEncontrada == null) {
			throw new RuntimeException("Operação fora do padrão.");
		}

		return Utils.convertaParaInt(operacaoEncontrada);
	}

	public static List<String> extraiaAgregacoes(String mensagem) {
		List<String> agregacoes = new ArrayList<>();
		Matcher match = obtenhaMatcher(REGEX_AGREGACAO, mensagem);

		while(match.find()) {
			String agregacao = match.group();
			if (agregacao.contains("{")) {
				agregacao = agregacao + "}";
			}
			agregacoes.add(agregacao);
		}

		return agregacoes;
	}

	public static String removaOperacao(String mensagem) {
		//Remove a operação
		return mensagem.replaceAll(REGEX_DADOS_OBJETO_COM_AGREGACAO, "");
	}

	private static String obtenhaRegexId(int id) {
		return "^" + id +";.*";
	}

	public static String extraiaPorId(String mensagem, int id) {
		String regexComId = obtenhaRegexId(id);
		return extraiaUmResultado(regexComId, mensagem);
	}

	public static String removaAgregacoes(String mensagem) {
		String mensagemSemAgregacoes = mensagem.replaceAll(REGEX_DADOS_OBJETO_SEM_AGREGACAO, "");
		return mensagemSemAgregacoes.replaceAll("\\}", "");
	}

	public static String extraiaNomeEntidade(String mensagem) {
		String mensagemFormatada = removaOperacao(mensagem);
		return extraiaUmResultado(REGEX_NOME_ENTIDADE, mensagemFormatada);
	}

	private static String extraiaUmResultado(String regex, String mensagem) {
		Matcher match = obtenhaMatcher(regex, mensagem);

		boolean encontrouResultado = match.find();

		if (!encontrouResultado) {
			throw new RuntimeException("Mensagem fora do padrão.");
		}

		return match.group();
	}

	private static Matcher obtenhaMatcher(String regex, String mensagem) {
		Pattern padrao = Pattern.compile(regex);
		return padrao.matcher(mensagem);
	}

	public static String removaNomeEntidade(String dado) {
		String nomeEntidade = extraiaNomeEntidade(dado);
		String nomeEntidadeRemover = separeComoCampo(nomeEntidade);
		return dado.replace(nomeEntidadeRemover, "");
	}

}

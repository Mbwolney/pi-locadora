package br.com.senaigo.locadora.persistencia;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.model.PersisteDadosFactory;
import br.com.senaigo.locadora.utils.RegexUtils;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.*;
import java.util.List;

public class Repositorio {

	private final String caminhoParaArquivoEntidade;
	private final String nomeEntidade;
	private final GeradorId geradorId;

	public Repositorio(String nomeEntidade) throws IOException {
		this.caminhoParaArquivoEntidade = obtenhaCaminhoParaArquivo(nomeEntidade);
		this.nomeEntidade = nomeEntidade;
		this.geradorId = new GeradorId();
	}

	private String obtenhaCaminhoParaArquivo(String nomeEntidade) {
		return "Persistência/" + nomeEntidade + ".txt";
	}

	public int incluir(PersisteDados objeto) throws IOException {
		//Lógica para separar os dados e guardar em cada arquivo
		String dadosObjetoPrincipal = objeto.desmonteObjeto();
		List<String> dadosAgregacoes = RegexUtils.extraiaAgregacoes(dadosObjetoPrincipal);
		for(String dadoObjeto : dadosAgregacoes) {
			int id = RegexUtils.extraiaId(dadoObjeto);
			if(id == 0) {//incluir se for uma nova instância
				int idGerada = escrevaDadoEmArquivo(dadoObjeto, false);
				String nomeEntidade = RegexUtils.extraiaNomeEntidade(dadoObjeto);
				dadosObjetoPrincipal = dadosObjetoPrincipal.replace(dadoObjeto, RegexUtils.separeComoCampo(nomeEntidade) + idGerada);
			}
		}
		return escrevaDadoEmArquivo(dadosObjetoPrincipal, true);
	}

	private int escrevaDadoEmArquivo(String dado, boolean objetoPrincipal) throws IOException {
		int id = geradorId.getUltimaIdGerada();
		String nomeEntidade = RegexUtils.extraiaNomeEntidade(dado);
		String caminhoArquivo = obtenhaCaminhoParaArquivo(nomeEntidade);
		String dadosParaSalvar = RegexUtils.removaNomeEntidade(dado);

		if(!objetoPrincipal) {
			PersisteDados objetoParaPersistir = PersisteDadosFactory.obtenhaInstancia(nomeEntidade);
			objetoParaPersistir.monteObjeto(dado);
			objetoParaPersistir.setId(id);
			dadosParaSalvar = objetoParaPersistir.desmonteObjeto() + "\n";
			dadosParaSalvar = RegexUtils.removaNomeEntidade(dadosParaSalvar);
		} else {
			String idConvertida = String.valueOf(id);
			dadosParaSalvar = dadosParaSalvar.replaceFirst("^\\d", idConvertida)  + "\n";
		}

		garantaExistenciaDoArquivo(caminhoArquivo);

		FileWriter escritorArquivo = new FileWriter(caminhoArquivo, true);
		BufferedWriter escritoTexto = new BufferedWriter(escritorArquivo);
		escritoTexto.write(dadosParaSalvar);
		escritoTexto.close();
		geradorId.finalize();
		return id;
	}

	public void garantaExistenciaDoArquivo(String caminhoArquivo) throws IOException {
		File file = new File(caminhoArquivo);
		if(!file.exists()) {
			file.createNewFile();
		}
	}

	public String listar() throws IOException {
		return listar(this.nomeEntidade);
	}

	private String listar(String nomeEntidade) throws IOException {
		String caminhoParaArquivo = obtenhaCaminhoParaArquivo(nomeEntidade);
		FileReader fr = new FileReader(caminhoParaArquivo);
		BufferedReader br  = new BufferedReader(fr);
		StringBuilder dados = new StringBuilder();
		String linha = "";

		while((linha = br.readLine()) != null){
			dados.append(linha).append("\n");
		}

		br.close();

		return dados.toString();
	}

	public void alterar(String novosDados) throws IOException {
		FileWriter escritorArquivo = new FileWriter(caminhoParaArquivoEntidade, false);
		BufferedWriter escritoTexto = new BufferedWriter(escritorArquivo);
		escritoTexto.write(novosDados);
		escritoTexto.close();
	}

	public String busquePorId(String nomeEntidadeAgregada, int id) throws IOException {
		String dadosNoRepositorio = listar(nomeEntidadeAgregada);
		String dadosObjetoNoRepositorio = RegexUtils.extraiaPorId(dadosNoRepositorio, id);
		return RegexUtils.separeComoCampo(nomeEntidadeAgregada) + dadosObjetoNoRepositorio;
	}

}

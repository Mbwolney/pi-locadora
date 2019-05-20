package br.com.senaigo.locadora.main;

import br.com.senaigo.locadora.controller.ClienteTcpController;
import br.com.senaigo.locadora.model.*;
import br.com.senaigo.locadora.persistencia.Operacao;
import br.com.senaigo.locadora.utils.DataUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class MainTestesMensagem {

	public static void main(String[] args) {

		//
		Cliente cliente = obtenhaClienteTesteCompleto();
		Marca marca = obtenhaMarcaTeste();
		Modelo modelo = obtenhaModeloTeste();
		Telefone telefonePrincipal = obtenhaTelefonePrincipalTeste();
		Telefone telefoneAlternativo = obtenhaTelefoneAlternativoTeste();
		Endereco endereco = obtenhaEnderecoTeste();
		Categoria categoria = obtenhaCategoriaTeste();
		Funcionario funcionario = obtenhaFuncionarioTeste();
		Motorista motorista = obtenhaMotoristaTeste();
		Veiculo veiculo = obtenhaVeiculoTeste();

		try {
			ClienteTcpController controller = new ClienteTcpController();
			/*
			String resultado = "";
			resultado = controller.execute(modelo, Operacao.INCLUIR);
			System.out.println(resultado);
			resultado = controller.execute(cliente, Operacao.INCLUIR);
			System.out.println(resultado);
			marca.setNome("Salvando sem estar agregado, direto");
			endereco.setLogradouro("Salvando Sem agregação");
			telefonePrincipal.setDdd(66);
			telefoneAlternativo.setDdd(99);
			*/




			/*
			controller.execute(marca, Operacao.INCLUIR);
			controller.execute(endereco, Operacao.INCLUIR);
			controller.execute(telefonePrincipal, Operacao.INCLUIR);
			controller.execute(telefoneAlternativo, Operacao.INCLUIR);
			controller.execute(categoria, Operacao.INCLUIR);
			controller.execute(funcionario, Operacao.INCLUIR);
			controller.execute(motorista, Operacao.INCLUIR);
			 */
			marca.setNome("Volks que será salvo com o veículo");
			controller.execute(marca, Operacao.INCLUIR);
			marca.setId(1);
			modelo.setNome("Gol que serã salvo com o veículo");
			controller.execute(modelo, Operacao.INCLUIR);
			modelo.setId(2);
			modelo.setMarca(marca);
			veiculo.setModelo(modelo);
			controller.execute(veiculo, Operacao.INCLUIR);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static Cliente obtenhaClienteTesteCompleto() {
		Cliente cliente = new Cliente();
		cliente.setNome("João Teste");
		cliente.setRazaoSocial("Razão Social Teste");
		cliente.setNomeFantasia("Nome Fantasia Teste");
		cliente.setDataNascimento(DataUtils.convertaStringParaLocalDate("17/10/1989"));
		cliente.setCpf("123.456.789-01");
		cliente.setCnpj("12.1234.1213/0001-00");
		Endereco endereco = obtenhaEnderecoTeste();
		endereco.setLogradouro("Rua A agregada a um cliente");
		cliente.setEndereco(endereco);
		Telefone telefonePrincipal = obtenhaTelefonePrincipalTeste();
		Telefone telefoneAlternativo = obtenhaTelefoneAlternativoTeste();
		cliente.setTelefonePrincipal(telefonePrincipal);
		cliente.setTelefoneAlternativo(telefoneAlternativo);
		cliente.setEmail("email@email.com");
		return cliente;
	}

	private static Endereco obtenhaEnderecoTeste() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua A");
		endereco.setComplemento("Ed. B");
		endereco.setBairro("Bairro C");
		endereco.setNumero("123");
		endereco.setCep("74650-140");
		return endereco;
	}

	private static Telefone obtenhaTelefonePrincipalTeste() {
		return Telefone.obtenhaInstancia("(62)1234-1234");
	}

	private static Telefone obtenhaTelefoneAlternativoTeste() {
		return Telefone.obtenhaInstancia("(62)12345-1234");
	}

	private static Marca obtenhaMarcaTeste() {
		Marca marca = new Marca();
		marca.setId(0);
		marca.setNome("Volkswagen");
		return marca;
	}

	private static Modelo obtenhaModeloTeste() {
		Modelo modelo = new Modelo();
		modelo.setId(0);
		modelo.setNome("Gol");
		Marca marca = obtenhaMarcaTeste();
		marca.setNome("Volks Agregado a modelo");
		modelo.setMarca(marca);
		return modelo;
	}

	private static Categoria obtenhaCategoriaTeste() {
		Categoria categoria = new Categoria();
		categoria.setId(0);
		categoria.setNome("Econômico");
		categoria.setValorDiarioLocacao(132.88f);
		return categoria;
	}

	private static Funcionario obtenhaFuncionarioTeste() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(0);
		funcionario.setNome("Teste da Silva Worker");
		funcionario.setDataNascimento(DataUtils.convertaStringParaLocalDate("15/10/1984"));
		funcionario.setCpf("123.456.789-11");
		Endereco endereco = obtenhaEnderecoTeste();
		endereco.setLogradouro("Rua A agregado a Funcionário");
		funcionario.setEndereco(endereco);
		funcionario.setTelefonePrincipal(obtenhaTelefonePrincipalTeste());
		funcionario.setTelefoneAlternativo(obtenhaTelefoneAlternativoTeste());
		funcionario.setEmail("funcionário@email.com");
		funcionario.setRg("123456-7");
		funcionario.setCarteiraDeTrabalho("12345-112-a");
		funcionario.setLogin("loginfuncionario");
		funcionario.setSenha("senhadofuncionario");
		return funcionario;
	}

	private static Motorista obtenhaMotoristaTeste() {
		Motorista motorista = new Motorista();
		motorista.setId(0);
		motorista.setNome("Teste da Silva Driver");
		motorista.setDataNascimento(DataUtils.convertaStringParaLocalDate("27/09/2004"));
		motorista.setCpf("987-654-321-00");
		Endereco endereco = obtenhaEnderecoTeste();
		endereco.setLogradouro("Rua A agregado a Motorista");
		motorista.setEndereco(endereco);
		motorista.setTelefonePrincipal(obtenhaTelefonePrincipalTeste());
		motorista.setTelefoneAlternativo(obtenhaTelefoneAlternativoTeste());
		motorista.setEmail("motorista@email.com");
		motorista.setCnh("2142131240-12");
		return motorista;
	}

	private static Veiculo obtenhaVeiculoTeste() {
		Veiculo veiculo = new Veiculo();
		veiculo.setId(0);
		veiculo.setPlaca("ABC-123");
		veiculo.setRenavam(123456789987654321L);
		veiculo.setAnoFabricacao(2019);
		veiculo.setValorCompra(38.222f);
		veiculo.setKmAtual(0);
		Categoria categoria = obtenhaCategoriaTeste();
		categoria.setNome("Econômico agregado a Veiculo");
		veiculo.setCategoria(categoria);
		veiculo.setEstado(Estado.LIVRE);
		Modelo modelo = obtenhaModeloTeste();
		modelo.setNome("Gol agregado a Veiculo");
		veiculo.setModelo(modelo);
		return veiculo;
	}
}

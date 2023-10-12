package application;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import control.dbConn.ConexaoException;
import control.pessoas.PessoaException;
import control.pessoas.pessoasDB.ClienteDao;
import control.pessoas.pessoasDB.EnderecoDao;
import control.pessoas.pessoasDB.FuncionarioDao;
import control.pessoas.pessoasDB.GerenteDao;
import control.pessoas.pessoasDB.PessoaDao;
import control.produto.ProductException;
import control.produto.produtoDB.CategoriaDao;
import control.produto.produtoDB.EstoqueDao;
import control.produto.produtoDB.ProdutoDao;
import model.pessoas.Cliente;
import model.pessoas.Email;
import model.pessoas.Endereco;
import model.pessoas.Funcionario;
import model.pessoas.Gerente;
import model.pessoas.Telefone;
import model.produtos.Categoria;
import model.produtos.Estoque;
import model.produtos.Produto;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		Funcionario gustavo = new Funcionario();
		Cliente rafa = new Cliente();
		
		EnderecoDao endDB = new EnderecoDao();
		PessoaDao pesDB = new PessoaDao();
		ClienteDao cliDB = new ClienteDao();
		GerenteDao geDB = new GerenteDao();
		FuncionarioDao funcDB = new FuncionarioDao();
		EstoqueDao estDB = new EstoqueDao();
		CategoriaDao catDB = new CategoriaDao();
		ProdutoDao prodDB = new ProdutoDao();
		
		Gerente g1 = new Gerente();
		Estoque eletronicos1 = new Estoque("Eletro1", g1);
		
		Categoria c1 = new Categoria("1", "Computadores", eletronicos1);
		Categoria c2 = new Categoria("2", "Eletrodomésticos", eletronicos1);
		
		Endereco end1 = new Endereco("end1", "91141-412", 57, "", "");
		Endereco end2 = new Endereco("end2", "6074124", 151, "", "Ao lado da lojinha");
		Endereco end3 = new Endereco("end3", "6074124", 306, "B", "Esquina com a lojinha");
		
		try {
			g1.setNome("Alex Blue");
			g1.setIdade(36);
			g1.setEmail(new Email("blues", "work.com"));
			g1.setCpf("9103413341");
			g1.setRg("2597129743");
			g1.setEndereco(end1);
			g1.setTelefone(new Telefone(55, 85, 41113201));
			g1.setIdGerente(g1.getRg());
			
			gustavo.setNome("Gustavo de Souza");
			gustavo.setIdade(23);
			gustavo.setRg("30513143912");
			gustavo.setCpf("63302423421");
			gustavo.setTelefone(new Telefone(55, 85, 42134221));
			gustavo.setEmail(new Email("gustavo", "gmail.com"));
			gustavo.setEndereco(end2);
			gustavo.setMatricula("3112044");
			gustavo.setSetor("Eletrodomésticos");
			gustavo.setGerente(g1);
			
			rafa.setNome("Rafael Lima");
			rafa.setIdade(19);
			rafa.setRg("23435421290");
			rafa.setCpf("2353143214");
			rafa.setTelefone(new Telefone(55, 81, 55321267));
			rafa.setEmail(new Email("limarafa", "hotmail.com"));
			rafa.setEndereco(end3);
			rafa.setNomeUsuario("rafalm19");
			rafa.setSenhaUsuario("19@00.lima");
			rafa.setIdUsuario();
			
			Produto p1 = new Produto("3243", "TV", c2, eletronicos1, 10, 4, 5, 1000);
			Produto p2 = new Produto("134", "Teclado Gamer", c1, eletronicos1, 20, 12, 7, 150.02);
			Produto p3 = new Produto("11", "Mouse", c1, eletronicos1, 12, 2, 6, 35.40);
			
//			Mostrando
			System.out.println(gustavo);
//			CtrlGerente.addNovoFuncionario(g1, gustavo);
			System.out.println(g1);

//			System.out.println("Precisa repor: \n" + CtrlEstoque.precisaRepor(eletronicos1));
//			System.out.printf("Total no estoque de eletrônicos: %.2f\n", CtrlEstoque.calcularTotal(eletronicos1));
			
			endDB.insert(end1);
			endDB.insert(end2);
			endDB.insert(end3);
			System.out.println(endDB.readAll());
			
//			pesDB.delete(g1.getRg());
//			pesDB.insert(g1);
			System.out.println(pesDB.readAll());
			
			estDB.insert(eletronicos1);
			for(Estoque e : estDB.readAll()) {
				System.out.println(e);
			}
//			cliDB.delete("1");
//			cliDB.insert(rafa);
//			System.out.println(cliDB.readAll());
//			for(Cliente c : cliDB.readAll()) {
//				System.out.println(c.getSenhaUsuario());
//				System.out.println(c.getIdUsuario());
//			}
//			if(cliDB.login(rafa.getIdUsuario()) != null) {
//				System.out.println("Cliente Logado!!");
//			}
			
//			catDB.insert(c1);
//			catDB.insert(c2);
//			catDB.insert(c3);
			System.out.println(catDB.readAll());
//			System.out.println(c1.getEstoque());
			
//			geDB.insert(g1);
			System.out.println("Gerente " + geDB.findById(g1.getRg()));

			
//			pesDB.delete(gustavo.getRg());
//			funcDB.insert(gustavo);
			for(Funcionario f : funcDB.readAll()) {
				System.out.println(f);
			}
			
			prodDB.insert(p1);
			prodDB.insert(p2);
			prodDB.insert(p3);
			for(Produto p : prodDB.readAll()) {
				System.out.println(p.getEstoque());
			}
			
			System.out.println(estDB.findById("Eletro1"));
		} 
		catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			System.out.println("[-] Há caracteres que não são permitidos para a senha.");
		}
		catch (ProductException e) {
			System.out.println(e.getMessage());
		}
		catch (PessoaException e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println();
	}
}

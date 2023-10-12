package control.pessoas.pessoasDB;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.pessoas.CtrlGerente;
import control.produto.CtrlEstoque;
import control.produto.produtoDB.CategoriaDao;
import control.produto.produtoDB.EstoqueDao;
import control.produto.produtoDB.ProdutoDao;
import model.pessoas.Cliente;
import model.pessoas.Email;
import model.pessoas.Endereco;
import model.pessoas.Funcionario;
import model.pessoas.Gerente;
import model.pessoas.Pessoa;
import model.pessoas.Telefone;
import model.produtos.Categoria;
import model.produtos.Estoque;
import model.produtos.Produto;

public class ConstrutoresAuxil {
	
	public static Produto construirProduto(ResultSet rs) throws SQLException {
		CategoriaDao categDB = new CategoriaDao();
		EstoqueDao estoque = new EstoqueDao();
		
		String idProduto = rs.getString("IdProduto");
		String nomePrdt = rs.getString("NomePrdt");
		Categoria categ = categDB.findById(rs.getString("CategoriaPrdt"));
		int quantMax = rs.getInt("QuantMax");
		int quantAt = rs.getInt("QuantAt");
		int quantMin = rs.getInt("QuantMin");
		double valorUn = rs.getDouble("Valor");
		Estoque e = construirEstoque(rs);
		
		Produto p = new Produto(idProduto, nomePrdt, categ, e, quantMax, quantAt, quantMin, valorUn);
		
		return p;
	}
	
	public static Categoria construirCateg(ResultSet rs) throws SQLException {
		EstoqueDao estoque = new EstoqueDao();
		
		String idCategoria = rs.getString("IdCategoria");
		String nomeCtgr = rs.getString("NomeCtgr");
		
		Estoque estoqueCtgr = construirEstoque(rs);
		
		return new Categoria(idCategoria, nomeCtgr, estoqueCtgr);
	}
	
	public static Estoque construirEstoque(ResultSet rs) throws SQLException {
		String idEstoque = rs.getString("IdEstoque");
		Gerente g = construirGerente(rs);
		
		return new Estoque(idEstoque, g);
	}
	
	public static Estoque construirEstoqueWCategoriaWProduto(ResultSet rs) throws SQLException {
		CategoriaDao cDB = new CategoriaDao();
		ProdutoDao pDB = new ProdutoDao();
		String idEstoque = rs.getString("IdEstoque");
		Gerente g = construirGerente(rs);
		Estoque e = new Estoque(idEstoque, g);
		
		for(Categoria cat : cDB.readAll()) {
			if(cat.getEstoque().getIdEstoque().equals(e.getIdEstoque())) {
				CtrlEstoque.addCategoria(e, cat);
			}
		}
		
		for(Produto p : pDB.readAll()) {
			if(p.getEstoque().getIdEstoque().equals(e.getIdEstoque())) {
				CtrlEstoque.addNoEstoque(e, p);
			}
		}
		
		return e;
	}
	
	public static Gerente construirGerente(ResultSet rs) throws SQLException {
		String idGerente = rs.getString("IdGerente");
		Pessoa gerenteP = construirPessoa(rs);
		Gerente g = new Gerente(gerenteP.getNome(), gerenteP.getRg(), gerenteP.getCpf(), gerenteP.getEmail(), gerenteP.getTelefone(), gerenteP.getIdade(), gerenteP.getEndereco(), idGerente);
		
		return g;
	}
	
	public static Gerente construirGerenteWEstoque(ResultSet rs) throws SQLException {
		EstoqueDao estDB = new EstoqueDao();
		String idGerente = rs.getString("IdGerente");
		Pessoa gerenteP = construirPessoa(rs);
		Gerente g = new Gerente(gerenteP.getNome(), gerenteP.getRg(), gerenteP.getCpf(), gerenteP.getEmail(), gerenteP.getTelefone(), gerenteP.getIdade(), gerenteP.getEndereco(), idGerente);
		
		for(Estoque e : estDB.readAll()) {
			if(e.getGerente().getIdGerente().equals(idGerente)) {
				CtrlGerente.addNovoEstoque(g, e);
			}
		}
		
		return g;
	}
	
	public static Funcionario construirFuncionario(ResultSet rs) throws SQLException {
		GerenteDao gerente = new GerenteDao();
		String matricula = rs.getString("Matricula");
		String setor = rs.getString("Setor");
		Gerente g = gerente.findById(rs.getString("GerenteFncr"));
		Pessoa funcionarioP = construirPessoa(rs);
		
		Funcionario f = new Funcionario(funcionarioP.getNome(), funcionarioP.getRg(), funcionarioP.getCpf(), funcionarioP.getEmail(), funcionarioP.getTelefone(), funcionarioP.getIdade(), funcionarioP.getEndereco(), matricula, g, setor);
		CtrlGerente.addNovoFuncionario(g, f);
		
		return f;
	}
	
	public static Cliente construirCliente(ResultSet rs) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
		String idUsuario = rs.getString("IdUsuario");
		String nomeUsr = rs.getString("NomeUsr");
		String senhaUsr = rs.getString("SenhaUsr");
		Pessoa clienteP = construirPessoa(rs);
		
		return new Cliente(clienteP.getNome(), clienteP.getRg(), clienteP.getCpf(), clienteP.getEmail(), clienteP.getTelefone(), clienteP.getIdade(), clienteP.getEndereco(), nomeUsr, senhaUsr);
	}
	
	public static Pessoa construirPessoa(ResultSet rs) throws SQLException {
		String nome = rs.getString("NomePessoa");
		String rg = rs.getString("Rg");
		String cpf = rs.getString("Cpf");
		Email email = construirEmail(rs.getString("Email"));
		Telefone tel = construirTelefone(rs.getString("Telefone"));
		int idade = rs.getInt("IdadePessoa");
		Endereco end = construirEnd(rs);
		
		return new Pessoa(nome, rg, cpf, email, tel, idade, end);
	}
	
	public static Endereco construirEnd(ResultSet rs) throws SQLException {
		return new Endereco(rs.getString("IdEndereco"), rs.getString("NumCep"), rs.getInt("NumCasa"), rs.getString("Complemento"), rs.getString("PontoRef"));
	}
	
	public static Telefone construirTelefone(String s) throws SQLException {
		String tel = s.replaceAll("/+", "").replaceAll(" ", "");
		char[] digest = tel.toCharArray();
	
		String pais = digest[0] + "" + digest[1] + "" + digest[2];
		String ddd = digest[3] + "" + digest[4];
		String num = "";
	
		for(int i=5; i<digest.length; i++){
			num += digest[i] + "";
		}
		
		return new Telefone(Integer.parseInt(pais), Integer.parseInt(ddd), Integer.parseInt(num));
	}
	
	public static Email construirEmail(String s){
		String[] email = s.split("@");
		return new Email(email[0], email[1]);
	}
}

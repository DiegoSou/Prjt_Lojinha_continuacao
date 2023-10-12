package model.pessoas;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import control.pessoas.Utilidades;
import model.produtos.Produto;

public class Cliente extends Pessoa implements Comparable<Cliente> {
	private String idUsuario;
	private String nomeUsuario;
	private String senhaUsuario;
	private List<Produto> carrinho = new ArrayList<>();
	private List<Produto> historico = new ArrayList<>();
	private List<Produto> favoritos = new ArrayList<>();
	
	public Cliente() {
		super();
	}

	public Cliente(String nome, String rg, String cpf, Email email, Telefone telefone, int idade, Endereco endereco, String nomeUsuario, String senhaUsuario) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		super(nome, rg, cpf, email, telefone, idade, endereco);
		this.nomeUsuario = nomeUsuario;
		this.setSenhaUsuario(senhaUsuario);
		
		this.setIdUsuario();
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario() {
		this.idUsuario = (this.nomeUsuario + "" + this.senhaUsuario);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		this.senhaUsuario = Utilidades.criptografar(senhaUsuario);
	}

	public List<Produto> getCarrinho() {
		return carrinho;
	}

	public List<Produto> getHistorico() {
		return historico;
	}

	public List<Produto> getFavoritos() {
		return favoritos;
	}

	@Override
	public String toString() {
		return "Cliente: " + nomeUsuario + "\n";
	}

	@Override
	public int compareTo(Cliente o) {
		return this.idUsuario.compareTo(o.getIdUsuario());
	}	
}

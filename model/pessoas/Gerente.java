package model.pessoas;

import java.util.ArrayList;
import java.util.List;

import model.produtos.Estoque;

public class Gerente extends Pessoa {
//	Fica responsável por controlar o Estoque e consultas de funcionários
	private String idGerente;
	private List<Estoque> estoques = new ArrayList<>();
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Gerente() {
		
	}

	public Gerente(String nome, String rg, String cpf, Email email, Telefone telefone, int idade, Endereco endereco, String idGerente) {
		super(nome, rg, cpf, email, telefone, idade, endereco);
		this.idGerente = idGerente;
	}

	public String getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(String idGerente) {
		this.idGerente = idGerente;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	@Override
	public String toString() {
		return this.getNome() + "\n";
	}
}

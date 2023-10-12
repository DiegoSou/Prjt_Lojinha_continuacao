package model.pessoas;

import control.pessoas.PessoaException;
import control.pessoas.Utilidades;

public class Pessoa {
	private String nome;
	private String rg;
	private String cpf;
	private Email email;
	private Telefone telefone;
	private int idade;
	private Endereco endereco;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String rg, String cpf, Email email, Telefone telefone, int idade, Endereco endereco) {
		this.setNome(nome);
		this.setRg(rg);
		this.setCpf(cpf);
		this.email = email;
		this.telefone = telefone;
		this.idade = idade;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome.length() > 50 || Utilidades.possuiDigitos(nome)) {
			throw new PessoaException("[-] Nome Inválido");
		}else {
			this.nome = nome;
		}
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		if(rg.length() > 14 || rg.length() < 7 || Utilidades.possuiLetras(rg)) {
			throw new PessoaException("[-] Rg Inválido");
		}else {
			this.rg = rg.replaceAll("-", "");
		}
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		if(cpf.length() > 15 || cpf.length() < 10 || Utilidades.possuiLetras(cpf)) {
			throw new PessoaException("[-] Cpf Inválido");
		}else {
			this.cpf = cpf.replace(".", "").replaceAll("-", "");
		}
	}
	
	public Email getEmail() {
		return email;
	}
	
	public void setEmail(Email email) {
		this.email = email;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		if(idade > 99 || idade < 16) {
			throw new PessoaException("[-] Idade Inválida");
		}else {
			this.idade = idade;
		}
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + "\n"
				+ "Idade: " + this.idade + "\n"
				+ "Telefone: " + this.telefone + "\n"
				+ "Email: " + this.email + "\n";			
	}
	
}

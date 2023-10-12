package model.pessoas;

import control.pessoas.CtrlGerente;
import control.pessoas.PessoaException;

public class Funcionario extends Pessoa implements Comparable<Funcionario> {
	private String matricula;
	private Gerente gerente;
	private String setor;
	
	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String rg, String cpf, Email email, Telefone telefone, int idade, Endereco endereco, String matricula, Gerente gerente, String setor) {
		super(nome, rg, cpf, email, telefone, idade, endereco);
		this.matricula = matricula;
		this.setor = setor;
		this.gerente = gerente;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		if(matricula.length() > 15) {
			throw new PessoaException("[-] Matrícula do funcionário inválida.");
		}else {
			this.matricula = matricula;
		}
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@Override
	public String toString() {
		return super.toString() 
				+ "Matrícula: " + this.matricula + "\n"
				+ "Setor: " + this.setor + "\n"
				+ "Gerente: " + this.gerente + "\n";
	}

	@Override
	public int compareTo(Funcionario o) {
		return this.matricula.compareTo(o.getMatricula());
	}
}

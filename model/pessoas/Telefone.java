package model.pessoas;

public class Telefone {
	private int pais;
	private int ddd;
	private int numero;
	
	public Telefone() {
		
	}

	public Telefone(int pais, int ddd, int numero) {
		this.pais = pais;
		this.ddd = ddd;
		this.numero = numero;
	}
	
	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "+" + this.pais + " " + this.ddd + " " + this.numero;
	}
}

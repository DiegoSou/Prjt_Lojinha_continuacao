package model.pessoas;

public class Endereco {
	private String idEndereco;
	private String cep;
	private int numCasa;
	private String complemento;
	private String pontoRef;
	
	public Endereco() {
		
	}
	
	public Endereco(String idEndereco, String cep, int numCasa, String complemento, String pontoRef) {
		this.idEndereco = idEndereco;
		this.cep = cep;
		this.numCasa = numCasa;
		this.complemento = complemento;
		this.pontoRef = pontoRef;
	}
	
	public String getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(String idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getNumCasa() {
		return numCasa;
	}

	public void setNumCasa(int numCasa) {
		this.numCasa = numCasa;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPontoRef() {
		return pontoRef;
	}

	public void setPontoRef(String pontoRef) {
		this.pontoRef = pontoRef;
	}

	public String toString() {
		return "CEP: " + this.cep + "\n"
				+ " Casa: " + this.numCasa + "\n"
				+ " Complemento: " + this.complemento + "\n"
				+ " Ponto de Referência: " + this.pontoRef + "\n";
	}
}

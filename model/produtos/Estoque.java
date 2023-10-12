package model.produtos;

import java.util.ArrayList;
import java.util.List;

import control.pessoas.CtrlGerente;
import model.pessoas.Gerente;

public class Estoque {
	private String idEstoque;
	private Gerente gerente;
	private List<Produto> produtos = new ArrayList<>();
	private List<Categoria> categEmEstoque = new ArrayList<>();
	
	public Estoque() {
		
	}

	public Estoque(String idEstoque, Gerente gerente) {
		this.idEstoque = idEstoque;
		this.setGerente(gerente);
	}
	

	public String getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(String idEstoque) {
		this.idEstoque = idEstoque;
	}
	
	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
		CtrlGerente.addNovoEstoque(gerente, this);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public List<Categoria> getCategEmEstoque(){
		return categEmEstoque;
	}

	@Override
	public String toString() {
		return "Estoque: " + idEstoque + "\n"
				+ "Produtos: " + produtos + "\n";
	}
}

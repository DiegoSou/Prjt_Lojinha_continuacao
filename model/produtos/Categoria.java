package model.produtos;

import control.produto.CtrlEstoque;

public class Categoria {
	private String idCategoria;
	private String nome;
	private Estoque estoque;
	
	public Categoria() {
		
	}

	public Categoria(String idCategoria, String nome, Estoque estoque) {
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.setEstoque(estoque);
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
		CtrlEstoque.addCategoria(estoque, this);
	}

	@Override
	public String toString() {
		return this.nome;
	}
}

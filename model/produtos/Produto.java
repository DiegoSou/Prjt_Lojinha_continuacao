package model.produtos;

import control.produto.CtrlEstoque;
import control.produto.ProductException;

public class Produto implements Comparable<Produto> {
	private String idProduto;
	private String nome;
	private Categoria categoria;
	private Estoque estoque;
	private int quantMax;
	private int quantAt;
	private int quantMin;
	private double valorUn;
	
	public Produto() {
		
	}

	public Produto(String idProduto, String nome, Categoria categoria, Estoque estoque, int quantMax, int quantAt, int quantMin, double valorUn) throws ProductException {
		this.idProduto = idProduto;
		this.nome = nome;
		this.categoria = categoria;
		this.setQuantMax(quantMax);
		this.setQuantAt(quantAt);
		this.setQuantMin(quantMin);
		this.setValorUn(valorUn);
		this.setEstoque(estoque);
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
		CtrlEstoque.addNoEstoque(estoque, this);
	}

	public int getQuantMax() {
		return quantMax;
	}

	public void setQuantMax(int quantMax) throws ProductException {
		if(quantMax <= 0) {
			throw new ProductException("[-] Quantidade máxima do produto não pode ser menor ou igual a zero.");
		}else {
			this.quantMax = quantMax;
		}
	}

	public int getQuantAt() {
		return quantAt;
	}

	public void setQuantAt(int quantAt) {
		if(quantAt <= 0) {
			throw new ProductException("[-] Quantidade atual do produto não pode ser menor ou igual a zero.");
		}else if(quantAt > quantMax) {
			throw new ProductException("[-] Quantidade atual do produto não pode ser maior que a quantidade máxima.");
		}else {
			this.quantAt = quantAt;
		}
	}

	public int getQuantMin() {
		return quantMin;
	}

	public void setQuantMin(int quantMin) {
		if(quantMin <= 0) {
			throw new ProductException("[-] Quantidade mínima do produto não pode ser menor ou igual a zero.");
		}else {
			this.quantMin = quantMin;
		}
	}

	public double getValorUn() {
		return valorUn;
	}

	public void setValorUn(double valorUn) {
		if(valorUn <= 0) {
			throw new ProductException("[-] Valor da unidade do produto não pode ser menor ou igual a zero.");
		}else {
			this.valorUn = valorUn;
		}
	}

	public double getValorTotal() {
		return quantAt*valorUn;
	}

	@Override
	public String toString() {
		return "Produto: " + nome + "\n"
				+ "Categoria: " + categoria + "\n"
				+ "Quantidade: " + quantAt + "\n"
				+ "Valor da Unidade: " + String.format("%.2f", valorUn) + "\n";
	}

	@Override
	public int compareTo(Produto o) {
		return this.idProduto.compareTo(o.idProduto);
	}
}

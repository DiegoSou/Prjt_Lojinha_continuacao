package control.produto;

import java.util.ArrayList;
import java.util.List;

import model.produtos.Categoria;
import model.produtos.Estoque;
import model.produtos.Produto;

// Controle do estoque
public class CtrlEstoque {
	
	public static void addNoEstoque(Estoque e, Produto p) {
		e.getProdutos().add(p);
	}
	
	public static void removeDoEstoque(Estoque e, Produto p) {
		e.getProdutos().remove(p);
	}
	
	public static void addCategoria(Estoque e, Categoria c) {
		e.getCategEmEstoque().add(c);
	}
	
	public static void removeCategoria(Estoque e, Categoria c) {
		e.getCategEmEstoque().remove(c);
	}
	
	public static List<Produto> precisaRepor(Estoque e) {
		List<Produto> repostos = new ArrayList<>();
		for(Produto p : e.getProdutos()) {
			if(p.getQuantAt() < p.getQuantMin()) {
				repostos.add(p);
			}
		}
		
		return repostos;
	}
	
	public static double calcularTotal(Estoque e) {
		double total = 0.0;
		for(Produto p : e.getProdutos()) {
			total += p.getValorTotal();
		}
		
		return total;
	}
}

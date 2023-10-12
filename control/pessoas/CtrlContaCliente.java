package control.pessoas;

import model.pessoas.Cliente;
import model.produtos.Produto;

// Controle conta do cliente
public class CtrlContaCliente {

	public static void addEmCarrinho(Cliente c, Produto p) {
		c.getCarrinho().add(p);
	}
	
	public static void removeEmCarrinho(Cliente c, Produto p) {
		for(Produto prod : c.getCarrinho()) {
			if(prod.equals(p)) {
				c.getCarrinho().remove(prod);
			}
		}
	}
	
	public static double totalNoCarrinho(Cliente c) {
		double total = 0.0;
		for(Produto p : c.getCarrinho()) {
			total += p.getValorTotal();
		}
		return total;
	}
	
	public static void addEmHistorico(Cliente c, Produto p) {
		c.getCarrinho().add(p);
	}
	
	public static void removeEmHistorico(Cliente c, Produto p) {
		for(Produto prod : c.getHistorico()) {
			if(prod.equals(p)) {
				c.getHistorico().remove(prod);
			}
		}
	}
	
	public static void addEmFavoritos(Cliente c, Produto p) {
		c.getCarrinho().add(p);
	}
	
	public static void removeEmFavoritos(Cliente c, Produto p) {
		for(Produto prod : c.getFavoritos()) {
			if(prod.equals(p)) {
				c.getFavoritos().remove(prod);
			}
		}
	}
}

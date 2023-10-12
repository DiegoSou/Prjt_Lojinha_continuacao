package control.pessoas;

import model.pessoas.Funcionario;
import model.pessoas.Gerente;
import model.produtos.Categoria;
import model.produtos.Estoque;

public class CtrlGerente {
	
	public static void addNovoFuncionario(Gerente g, Funcionario f) {
		boolean add = false;
		for(Estoque e : g.getEstoques()) {
			System.out.println(e.getCategEmEstoque());
			if(add == true) {
				break;
			}else {
				for(Categoria c : e.getCategEmEstoque()) {
					if(c.getNome().equals(f.getSetor())) {
						g.getFuncionarios().add(f);
						add = true;
					}
				}
			}
		}
		if(add == false) {
			throw new PessoaException("[-] Categoria do funcionário não encontrada no estoque.");
		}
	}
	
	public static void removeFuncionario(Gerente g, Funcionario f) {
		g.getFuncionarios().remove(f);
	}
	
	public static void addNovoEstoque(Gerente g, Estoque e) {
		g.getEstoques().add(e);
	}
	
	public static void removeEstoque(Gerente g, Estoque e) {
		g.getEstoques().remove(e);
	}
}

package control.produto.produtoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import control.pessoas.pessoasDB.ConstrutoresAuxil;
import control.pessoas.pessoasDB.GerenteDao;
import model.pessoas.Gerente;
import model.produtos.Estoque;

public class EstoqueDao {
	
	Conexao conn = new Conexao();
	
	public void insert(Estoque est) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("INSERT INTO Estoque VALUES(?, ?)");
			
			ps.setString(1, est.getIdEstoque());
			ps.setString(2, est.getGerente().getIdGerente());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Novo estoque adicionado!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Estoque> readAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		GerenteDao gDao = new GerenteDao();
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Estoque.*, Gerente.*, Pessoa.*, Endereco.* FROM Estoque"
					+ " INNER JOIN Gerente ON Estoque.GerenteEstq = Gerente.IdGerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			
			rs = ps.executeQuery();
			List<Estoque> lista = new ArrayList<>();
			
			while(rs.next()) {
				Estoque est = ConstrutoresAuxil.construirEstoqueWCategoriaWProduto(rs);
				lista.add(est);
			}
			
			return lista;
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharResultSet(rs);
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public Estoque findById(String idEstoque) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Estoque est = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Estoque.*, Gerente.*, Pessoa.*, Endereco.* FROM Estoque"
					+ " INNER JOIN Gerente ON Estoque.GerenteEstq = Gerente.IdGerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco"
					+ " WHERE IdEstoque = ?");
			
			ps.setString(1, idEstoque);
			rs = ps.executeQuery();

			while(rs.next()) {
				est = ConstrutoresAuxil.construirEstoqueWCategoriaWProduto(rs);
			}
			
			return est;
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharResultSet(rs);
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void update(Estoque est) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("UPDATE Estoque SET GerenteEstq=? WHERE IdEstoque=?");
			
			ps.setString(1, est.getGerente().getIdGerente());
			ps.setString(2, est.getIdEstoque());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Estoque atualizado!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void delete(String idEstoque) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("DELETE FROM Estoque WHERE IdEstoque=?");

			ps.setString(1, idEstoque);
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Estoque removido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

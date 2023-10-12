package control.produto.produtoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import control.pessoas.pessoasDB.ConstrutoresAuxil;
import model.produtos.Categoria;

public class CategoriaDao {

	Conexao conn = new Conexao();
	
	public void insert(Categoria cat) {
		PreparedStatement ps = null;
		EstoqueDao estDB = new EstoqueDao();
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("INSERT INTO Categoria VALUES(?, ?, ?)");
			
			ps.setString(1, cat.getIdCategoria());
			ps.setString(2, cat.getNome());
			ps.setString(3, estDB.findById(cat.getEstoque().getIdEstoque()).getIdEstoque());
			
			int linhasAfet = ps.executeUpdate();
			if(linhasAfet > 0) {
				System.out.println("Categoria adicionada!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Categoria> readAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Categoria.*, Estoque.*, Gerente.*, Pessoa.*, Endereco.* FROM Categoria"
					+ " INNER JOIN Estoque ON Categoria.EstoqueCtgr = Estoque.IdEstoque"
					+ " INNER JOIN Gerente ON Estoque.GerenteEstq = Gerente.IdGerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			

			
			rs = ps.executeQuery();
			List<Categoria> lista = new ArrayList<>();
			
			while(rs.next()) {
				Categoria cat = ConstrutoresAuxil.construirCateg(rs);
				lista.add(cat);
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
	
	public Categoria findById(String idCategoria) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria cat = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Categoria.*, Estoque.*, Gerente.*, Pessoa.*, Endereco.* FROM Categoria"
					+ " INNER JOIN Estoque ON Categoria.EstoqueCtgr = Estoque.IdEstoque"
					+ " INNER JOIN Gerente ON Estoque.GerenteEstq = Gerente.IdGerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco"
					+ " WHERE IdCategoria=?");
		
			ps.setString(1, idCategoria);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cat = ConstrutoresAuxil.construirCateg(rs);
			}
			
			return cat;
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharResultSet(rs);
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void update(Categoria cat) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"UPDATE Categoria SET NomeCtgr=?, EstoqueCtrg=?"
					+ " WHERE IdCategoria=?");
			
			ps.setString(1, cat.getNome());
			ps.setString(2, cat.getEstoque().getIdEstoque());
			ps.setString(3, cat.getIdCategoria());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Categoria atualizada!");
			} 
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void delete(String s) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"DELETE FROM Categoria"
					+ " WHERE IdCategoria=?");
			
			ps.setString(3, s);
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Categoria removida!");
			} 
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

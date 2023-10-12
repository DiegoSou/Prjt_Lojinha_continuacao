package control.produto.produtoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import control.pessoas.pessoasDB.ConstrutoresAuxil;
import model.produtos.Produto;

public class ProdutoDao {

	Conexao conn = new Conexao();
	
	public void insert(Produto p) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"INSERT INTO Produto "
					+ "VALUES(?,?,?,?,?,?,?,?)");
			
			ps.setString(1, p.getIdProduto());
			ps.setString(2, p.getNome());
			ps.setString(3, p.getCategoria().getIdCategoria());
			ps.setInt(4, p.getQuantMax());
			ps.setInt(5, p.getQuantAt());
			ps.setInt(6, p.getQuantMin());
			ps.setDouble(7, p.getValorUn());
			ps.setString(8, p.getEstoque().getIdEstoque());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Produto inserido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Produto> readAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Produto.*, Estoque.*, Gerente.*, Pessoa.*, Endereco.* FROM Produto"
					+ " INNER JOIN Estoque ON Produto.EstoquePrdt = Estoque.IdEstoque"
					+ " INNER JOIN Gerente ON Estoque.GerenteEstq = Gerente.IdGerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			
			rs = ps.executeQuery();
			List<Produto> lista = new ArrayList<>();
			
			while(rs.next()) {
				Produto prod = ConstrutoresAuxil.construirProduto(rs);
				lista.add(prod);
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
	
	public void update(Produto p) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"UPDATE Produto SET"
					+ " NomePrdt=?"
					+ " QuantMax=?"
					+ " QuantAt=?"
					+ " QuantMin=?"
					+ " Valor=?"
					+ " WHERE IdProduto=?");
			
			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQuantMax());
			ps.setInt(3, p.getQuantAt());
			ps.setInt(4, p.getQuantMin());
			ps.setDouble(5, p.getValorUn());
			ps.setString(6, p.getIdProduto());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Produto atualizado!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	
	public void delete(String idProduto) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"DELETE FROM Produto"
					+ " WHERE IdProduto=?");
			
			ps.setString(1, idProduto);
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Produto removido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

package control.pessoas.pessoasDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import model.pessoas.Gerente;
import model.pessoas.Pessoa;

public class GerenteDao {
	
	Conexao conn = new Conexao();
	
	public void insert(Gerente g) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("INSERT INTO Gerente VALUES(?, ?)");
			
			ps.setString(1, g.getIdGerente());
			ps.setString(2, new Pessoa(g.getNome(), g.getRg(), g.getCpf(), g.getEmail(), g.getTelefone(), g.getIdade(), g.getEndereco()).getRg());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Gerente inserido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Gerente> readAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Gerente.*, Pessoa.*, Endereco.* FROM Gerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			
			rs = ps.executeQuery();
			List<Gerente> lista = new ArrayList<>();
			while(rs.next()) {
				Gerente g = ConstrutoresAuxil.construirGerenteWEstoque(rs);
				lista.add(g);
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
	
	public Gerente findById(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gerente g = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Gerente.*, Pessoa.*, Endereco.* FROM Gerente"
					+ " INNER JOIN Pessoa ON Gerente.GerenteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco"
					+ " WHERE Gerente.IdGerente = ?");
			
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				g = ConstrutoresAuxil.construirGerenteWEstoque(rs);
			}
			
			return g;
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void update(Gerente g) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("UPDATE Gerente SET GerenteP=? WHERE IdGerente=?");
			
			ps.setString(1, g.getRg());
			ps.setString(2, g.getIdGerente());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Gerente atualizado!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void delete(String id) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("DELETE FROM Gerente WHERE IdGerente=?");
			
			ps.setString(1, id);
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Gerente removido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

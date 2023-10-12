package control.pessoas.pessoasDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import model.pessoas.Email;
import model.pessoas.Endereco;
import model.pessoas.Pessoa;
import model.pessoas.Telefone;

public class PessoaDao {
	
	Conexao conn = new Conexao();
			
	public void insert(Pessoa p) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("INSERT INTO Pessoa VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, p.getNome());
			ps.setString(2, p.getRg());
			ps.setString(3, p.getCpf());
			ps.setString(4, p.getEmail().toString());
			ps.setString(5, p.getTelefone().toString());
			ps.setInt(6, p.getIdade());
			ps.setString(7, p.getEndereco().getIdEndereco());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Pessoa inserida!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Pessoa> readAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Pessoa.*, Endereco.* FROM Pessoa"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			
			rs = ps.executeQuery();		
			List<Pessoa> lista = new ArrayList<>();
			
			while(rs.next()) {
				Pessoa p = ConstrutoresAuxil.construirPessoa(rs); 
				lista.add(p);
			}
			
			return lista;
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharStatement(ps);
			conn.fecharResultSet(rs);
			conn.fecharConexao();
		}
	}
	
	public Pessoa findById(String rg) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pessoa p = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("SELECT Pessoa.*, Endereco.* FROM Pessoa"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco"
					+ " WHERE Rg=?");
			
			ps.setString(1, rg);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				p = ConstrutoresAuxil.construirPessoa(rs);
			}
			
			return p;
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharStatement(ps);
			conn.fecharResultSet(rs);
			conn.fecharConexao();
		}
	}
	
	public void update(Pessoa p) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"UPDATE Pessoa SET "
					+ "NomePessoa=?, "
					+ "Email=?, "
					+ "Telefone=?, "
					+ "IdadePessoa=?, "
					+ "EnderecoPessoa=?, "
					+ "WHERE Rg=?");
			
			ps.setString(1, p.getNome());
			ps.setString(2, p.getEmail().toString());
			ps.setString(3, p.getTelefone().toString());
			ps.setInt(4, p.getIdade());
			ps.setString(5, p.getEndereco().getIdEndereco());
			ps.setString(6, p.getRg());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Pessoa atualizada!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void delete(String rg) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("DELETE FROM Pessoa WHERE Rg=?");
			
			ps.setString(1, rg);
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Pessoa removida!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

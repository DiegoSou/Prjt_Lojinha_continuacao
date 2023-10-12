package control.pessoas.pessoasDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import model.pessoas.Endereco;

public class EnderecoDao {
	
	Conexao conn = new Conexao();
	
	public void insert(Endereco end) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"INSERT INTO Endereco(IdEndereco, NumCep, NumCasa, Complemento, PontoRef) "
					+ "VALUES(?, ?, ?, ?, ?)");
			
			ps.setString(1, end.getIdEndereco());
			ps.setString(2, end.getCep());
			ps.setInt(3, end.getNumCasa());
			ps.setString(4, end.getComplemento());
			ps.setString(5, end.getPontoRef());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Endereço inserido!");
			}
			
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Endereco> readAll(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("SELECT * FROM Endereco");
			
			rs = ps.executeQuery();
			List<Endereco> lista = new ArrayList<>();
			
			while(rs.next()) {
				Endereco end = ConstrutoresAuxil.construirEnd(rs);
				lista.add(end);
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
	
	public void update(Endereco end) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"UPDATE Endereco SET "
					+ "NumCep = ?, "
					+ "NumCasa = ?, "
					+ "Complemento = ?, "
					+ "PontoRef = ? "
					+ "WHERE IdEndereco = ?");
			
			ps.setString(1, end.getCep());
			ps.setInt(2, end.getNumCasa());
			ps.setString(3, end.getComplemento());
			ps.setString(4, end.getPontoRef());
			ps.setString(5, end.getIdEndereco());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Endereço atualizado!");
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
			ps = conn.getConn().prepareStatement("DELETE FROM Endereco WHERE IdEndereco=?");
			
			ps.setString(1, id);
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Endereço deletado!");
			}
			
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

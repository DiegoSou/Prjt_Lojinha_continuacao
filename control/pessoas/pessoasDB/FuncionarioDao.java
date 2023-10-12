package control.pessoas.pessoasDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import model.pessoas.Funcionario;
import model.pessoas.Pessoa;

public class FuncionarioDao {
	Conexao conn = new Conexao();
	
	public void insert(Funcionario f) {
		PreparedStatement ps = null;
		PessoaDao pes = new PessoaDao();
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("INSERT INTO Funcionario VALUES(?, ?, ?, ?)");
			
			ps.setString(1, f.getMatricula());
			ps.setString(2, f.getSetor());
			ps.setString(3, f.getGerente().getIdGerente());
			
			Pessoa p = pes.findById(f.getRg());
			if(p == null) {
				pes.insert(new Pessoa(f.getNome(), f.getRg(), f.getCpf(), f.getEmail(), f.getTelefone(), f.getIdade(), f.getEndereco()));
			}
			
			ps.setString(4, pes.findById(f.getRg()).getRg());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Funcionario inserido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Funcionario> readAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Funcionario.*, Gerente.*, Pessoa.*, Endereco.* FROM Funcionario"
					+ " INNER JOIN Gerente ON Funcionario.GerenteFncr = Gerente.IdGerente"
					+ " INNER JOIN Pessoa ON Funcionario.FuncionarioP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			
			rs = ps.executeQuery();
			List<Funcionario> lista = new ArrayList<>();
			
			while(rs.next()) {
				Funcionario f = ConstrutoresAuxil.construirFuncionario(rs);
				lista.add(f);
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
	
	public void update(Funcionario f) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("UPDATE Funcionario SET "
					+ "Setor= ?, "
					+ "GerenteFncr= ?, "
					+ "WHERE Maricula= ?");
			
			ps.setString(1, f.getSetor());
			ps.setString(2, f.getGerente().getIdGerente());
			ps.setString(3, f.getMatricula());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Funcionário atualizado!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public void delete(String matricula) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("DELETE FROM Funcionario WHERE Matricula=?");
			
			ps.setString(1, matricula);
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Funcionário removido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

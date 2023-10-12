package control.pessoas.pessoasDB;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dbConn.Conexao;
import control.dbConn.ConexaoException;
import model.pessoas.Cliente;
import model.pessoas.Pessoa;

public class ClienteDao {
	
	Conexao conn = new Conexao();
	
	public void insert(Cliente cli) {
		PreparedStatement ps = null;
		PessoaDao pDao = new PessoaDao();
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("INSERT INTO Cliente VALUES(?, ?, ?, ?)");
			
			ps.setString(1, cli.getIdUsuario());
			ps.setString(2, cli.getNomeUsuario());
			ps.setString(3, cli.getSenhaUsuario());
			
			Pessoa p = pDao.findById(cli.getRg());
			if(p == null) {
				pDao.insert(new Pessoa(cli.getNome(), cli.getRg(), cli.getCpf(), cli.getEmail(), cli.getTelefone(), cli.getIdade(), cli.getEndereco()));
			}
			
			ps.setString(4, pDao.findById(p.getRg()).getRg());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Cliente inserido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public List<Cliente> readAll(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Cliente.*, Pessoa.*, Endereco.* FROM Cliente"
					+ " INNER JOIN Pessoa ON Cliente.ClienteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco");
			
			rs = ps.executeQuery();
			List<Cliente> lista = new ArrayList<>();
			
			while(rs.next()) {
				Cliente cli = ConstrutoresAuxil.construirCliente(rs);
				lista.add(cli);
			}
			
			return lista;
		} catch (ConexaoException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			conn.fecharStatement(ps);
			conn.fecharResultSet(rs);
			conn.fecharConexao();
		}
	}
	
	public  void update(Cliente cli) {
		PreparedStatement ps = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement("UPDATE Cliente SET "
					+ "NomeUsr= ?, "
					+ "SenhaUsr= ?, "
					+ "WHERE IdUsuario= ?");
			
			ps.setString(1, cli.getIdUsuario());
			ps.setString(2, cli.getNomeUsuario());
			ps.setString(3, cli.getIdUsuario());
			
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Cliente atualizado!");
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
			ps = conn.getConn().prepareStatement("DELETE FROM Cliente WHERE IdUsuario=?");
			
			ps.setString(1, id);
			int linhasAfet = ps.executeUpdate();
			
			if(linhasAfet > 0) {
				System.out.println("Cliente removido!");
			}
		} catch (ConexaoException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
	
	public Cliente login(String idUsuario) throws ConexaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente usuario = null;
		
		try {
			boolean c = conn.abrirConexao();
			ps = conn.getConn().prepareStatement(
					"SELECT Cliente.*, Pessoa.*, Endereco.* FROM Cliente"
					+ " INNER JOIN Pessoa ON Cliente.ClienteP = Pessoa.Rg"
					+ " INNER JOIN Endereco ON Pessoa.EnderecoPessoa = Endereco.IdEndereco"
					+ " WHERE IdUsuario = ?");
			
			ps.setString(1, idUsuario);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				usuario = ConstrutoresAuxil.construirCliente(rs);
			}else {
				throw new SQLException("Usuário não conseguiu logar.");
			}
		
			return usuario;
		} catch (ConexaoException | SQLException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new ConexaoException(e.getMessage());
		} finally {
			conn.fecharStatement(ps);
			conn.fecharConexao();
		}
	}
}

package control.dbConn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conexao {
	
	private Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) throws ConexaoException {
		if(conn == null) {
			this.conn = conn;
		}else {
			throw new ConexaoException("[-] Conexão já existe.");
		}
	}
	
	public boolean abrirConexao() throws ConexaoException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties props = carregarProperties();
			String dburl = props.getProperty("dburl");
			conn = DriverManager.getConnection(dburl, props);
			return true;
		}
		catch(Exception e) {
			throw new ConexaoException(e.getMessage());
		}
	}
	
	public void fecharConexao() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			System.out.println("[-] Erro ao fechar a conexão.");
		}
	}
	
	public void fecharStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			System.out.println("[-] Erro ao fechar statement.");
		}
	}
	
	public void fecharResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println("[-] Erro ao fechar resultset.");
		}
	}
	
	public Properties carregarProperties() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		FileInputStream fs = new FileInputStream("dbConn.properties");
		props.load(fs);
		return props;
	}
}

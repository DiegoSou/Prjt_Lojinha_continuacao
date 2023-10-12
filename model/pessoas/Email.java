package model.pessoas;

public class Email {
	private String usuario;
	private String servidor;
	
	public Email() {
		
	}
	
	public Email(String usuario, String servidor) {
		this.usuario = usuario;
		this.servidor = servidor;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	@Override
	public String toString() {
		return this.usuario + "@" + this.servidor;
	}
}

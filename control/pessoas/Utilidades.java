package control.pessoas;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Métodos funcionais
public class Utilidades {
	public static boolean possuiDigitos(String str) {
		char[] charArray = str.toCharArray();
		for(char c : charArray) {
			if(Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean possuiLetras(String str) {
		char[] charArray = str.toCharArray();
		for(char c : charArray) {
			if(!Character.isDigit(c)) {
				if((c != '.') && (c != '-')) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static String criptografar(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] senhaBy = senha.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		
		byte[] senhaCript = md.digest(senhaBy);
		return senhaCript.toString();
	}
}

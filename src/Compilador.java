import java.util.ArrayList;
import java.util.Hashtable;

public class Compilador {
	public static void main(String args[]) throws Exception{
		Token.iniciarHash();
		AnalisadorLexico al = AnalisadorLexico.getInstance();
		AnalisadorSintatico as = AnalisadorSintatico.getInstance();
		ArrayList<Token> tokens = al.analisa();
		System.out.println(tokens);
		
		Hashtable<String, Simbolo> tabelaSimbolo = as.analisa(tokens);
		System.out.println(tabelaSimbolo);
		
		
	}
}

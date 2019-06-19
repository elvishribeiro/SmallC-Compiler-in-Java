package analisador;
import arvore.Astnode;
import java.util.ArrayList;
import java.util.Hashtable;

public class Compilador {
	public static void main(String args[]) throws Exception{
		Token.iniciarHash();
		AnalisadorLexico al = AnalisadorLexico.getInstance();
		AnalisadorSintatico as = AnalisadorSintatico.getInstance();
		ArrayList<Token> tokens = al.analisa();
		printTokens(tokens);
		Astnode raiz = as.analisa(tokens);
		System.out.println(raiz.geraArvore(0));
		System.out.println(raiz.geraCodigo(0));
	}
	
	public static void printTokens(ArrayList<Token> tokens) {
		System.out.printf("       Token       |   No da Linha   |  Lexema  \n");
		for (Token t : tokens) {
			System.out.println(String.format("%"+ (9 - t.getNome().length()/2) +"s"," ") + 
					t.getNome() + 
					String.format("%"+(9 - t.getNome().length()/2)+"s"," ")+
			
					String.format("%"+(7 - Integer.toString(t.getLinha()).length()/2)+"s"," ") + 
					t.getLinha() + 
					String.format("%"+(7 - Integer.toString(t.getLinha()).length()/2)+"s"," ") +
					
					String.format("%"+(7 - t.getLexema().length()/2)+"s"," ") + 
					t.getLexema() + 
					String.format("%"+(5 - t.getLexema().length()/2)+"s"," "));

		}
	}
}

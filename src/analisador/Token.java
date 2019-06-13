package analisador;

import java.util.*; 

public class Token {
	private String nome;
	private int linha;
	private String lexema;
	private static Hashtable<String, String> h = new Hashtable<String, String>(); 
	
	public Token(String nome, int linha, String lexema) {
		this.setNome(nome);
		this.setLinha(linha);
		this.setLexema(lexema);
	}
	
	public static String lexemaToToken(String lexema) {
		return h.get(lexema);
	}
	
	public static void iniciarHash() {
		h.put("main", "MAIN"); 
	    h.put("int", "INT"); 
	    h.put("float", "FLOAT");
	    h.put("if", "IF");
	    h.put("else", "ELSE");
	    h.put("while", "WHILE");
	    h.put("for", "FOR");
	    h.put("read", "READ");
	    h.put("print", "PRINT");
	    h.put("(", "LBRACKET");
	    h.put(")", "RBRACKET");
	    h.put("{", "LBRACE");
	    h.put("}", "RBRACE");
	    h.put(",", "COMMA");
	    h.put(";", "PCOMMA");
	    h.put("=", "ATTR");
	    h.put("<", "LT");
	    h.put("<=", "LE");
	    h.put(">", "GT");
	    h.put(">=", "GE");
	    h.put("+", "PLUS");
	    h.put("-", "MINUS");
	    h.put("*", "MULT");
	    h.put("/", "DIV");
	    h.put("[", "LCOL");
	    h.put("]", "RCOL");
	    h.put("==", "EQ");
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	public String toString() {
		return "\"Nome: " + nome + 
				" Linha: " + linha + 
				" Lexema: " + lexema + "\n\"";
	}
	

}

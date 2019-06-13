import java.util.ArrayList;
import java.util.Hashtable;

public class AnalisadorSintatico {
	private static AnalisadorSintatico instance = new AnalisadorSintatico();
	
	private ArrayList<Token> tokens;
	private static Hashtable<String, Simbolo> tabelaSimbolos = new Hashtable<String, Simbolo>(); 
	
	private Token tokenEntrada;
	private Integer i = 0;
	private String tipo;

	private AnalisadorSintatico() {}

	public static AnalisadorSintatico getInstance() {
		return instance;
	}

	public Hashtable<String,Simbolo> analisa(ArrayList<Token> tokens) {
		this.tokens = tokens;
		tokenEntrada = this.tokens.get(0);
		programa();
		return tabelaSimbolos;
	}

	private void programa() {
		casa("INT");
		casa("MAIN");
		casa("LBRACKET");
		casa("RBRACKET");
		casa("LBRACE");
		decl_Comando();
		casa("RBRACE");
	}
	
	private void declaracao(){
		//printf("Declaracao-> Tipo ID Decl2-> "); //remover
		tipo = tipo();
		Token tokenBackup = tokenEntrada;
		//insere na tabela de simbolos

		if (casa("ID")){
			if (!tabelaSimbolos.containsKey(tokenBackup.getLexema())){
					Simbolo s = new Simbolo(tipo, tokenBackup.getLexema(), tokenBackup.getLinha());
					tabelaSimbolos.put(s.getLexema(), s);
			}else{
				System.out.println("REDECLARACAO DE " + tokenBackup.getLexema());
			}
		}
		decl2();
		tipo = null;
	}

	private void decl_Comando() {
		// printf("Decl_Comando-> "); //remover
		if (tokenEntrada.getNome().equals("INT") || tokenEntrada.getNome().equals("FLOAT")) {
			declaracao();
			decl_Comando();
		} else if (tokenEntrada.getNome().equals("LBRACE") || tokenEntrada.getNome().equals("ID")
				|| tokenEntrada.getNome().equals("IF") || tokenEntrada.getNome().equals("WHILE")
				|| tokenEntrada.getNome().equals("READ") || tokenEntrada.getNome().equals("PRINT")
				|| tokenEntrada.getNome().equals("FOR")) {
			comando();
			decl_Comando();
		} else {}
	}
	
	private void decl2(){
		//printf("Decl2-> "); //remover
		if (tokenEntrada.getNome().equals("COMMA")){
			casa("COMMA");
			Token tokenBackup = tokenEntrada;

			if (casa("ID")){
				if (!tabelaSimbolos.containsKey(tokenBackup.getLexema())){
					Simbolo s = new Simbolo(tipo, tokenBackup.getLexema(), tokenBackup.getLinha());
					tabelaSimbolos.put(s.getLexema(), s);
				}else{
					System.out.println("REDECLARACAO DE " + tokenBackup.getLexema());
				}
			}
			decl2();
		}else if(tokenEntrada.getNome().equals("PCOMMA")){
			casa("PCOMMA");
		}else if(tokenEntrada.getNome().equals("ATTR")){
			casa("ATTR");
			expressao();
			decl2();
		}else{
			//printf("Missing identifier before ->");
		}
	}
	
	private String tipo(){
		//printf("TIPO-> "); //remover
		if (tokenEntrada.getNome().equals("INT")){
			casa("INT");
			return "INT";
		}
		else if (tokenEntrada.getNome().equals("FLOAT")){
			casa("FLOAT");
			return "FLOAT";
		}
		return null;
	}
	
	private void comando(){
		//printf("Comando->"); //remover
		if (tokenEntrada.getNome().equals("LBRACE"))
			bloco();
		else if (tokenEntrada.getNome().equals("ID"))
			atribuicao();
		else if (tokenEntrada.getNome().equals("IF"))
			comandoSe();
		else if (tokenEntrada.getNome().equals("WHILE"))
			comandoEnquanto();
		else if (tokenEntrada.getNome().equals("READ"))
			comandoRead();
		else if (tokenEntrada.getNome().equals("PRINT"))
			comandoPrint();
		else if (tokenEntrada.getNome().equals("FOR"))
			comandoFor();
	}
	private void bloco(){
		//printf("Bloco-> "); //remover
		casa("LBRACE");
		decl_Comando();
		casa("RBRACE");
	}

	private void atribuicao(){
		//printf("Atribuicao-> "); //remover
		casa("ID");
		casa("ATTR");
		expressao();
		casa("PCOMMA");
	}

	private void comandoSe(){
		//printf("ComandoSe-> "); //remover
		casa("IF");
		casa("LBRACKET");
		expressao();
		casa("RBRACKET");
		comando();
		comandoSenao();
	}

	private void comandoSenao(){
		//printf("ComandoSenao-> "); //remover
		if (tokenEntrada.getNome().equals("ELSE")){
			casa("ELSE");
			comando();
		}
	}

	private void comandoEnquanto(){
		//printf("ComandoEnquanto-> "); //remover
		casa("WHILE");
		casa("LBRACKET");
		expressao();
		casa("RBRACKET");
		comando();
	}

	private void comandoRead(){
		//printf("ComandoRead-> "); //remover
		casa("READ");
		casa("ID");
		casa("PCOMMA");
	}

	private void comandoPrint(){
		//printf("ComandoPrint-> "); //remover
		casa("PRINT");
		casa("LBRACKET");
		expressao();
		casa("RBRACKET");
		casa("PCOMMA");
	}

	private void comandoFor(){
		//printf("ComandoFor-> "); //remover
		casa("FOR");
		casa("LBRACKET");
		atribuicaoFor();
		casa("PCOMMA");
		expressao();
		casa("PCOMMA");
		atribuicaoFor();
		casa("RBRACKET");
		comando();
	}
	
	private void atribuicaoFor(){
		//printf("AtribuicaoFor-> "); //remover
		casa("ID");
		casa("ATTR");
		expressao();
	}

	private void expressao(){
		//printf("Expressao ->"); //remover
		adicao();
		relacaoOpc();
	}
	
	private void relacaoOpc(){
		if (tokenEntrada.getNome().equals("LT") || tokenEntrada.getNome().equals("LE") || 
			tokenEntrada.getNome().equals("GT") || tokenEntrada.getNome().equals("GE") ||
			tokenEntrada.getNome().equals("EQ")){
			opRel();
			adicao();
			relacaoOpc();
		}else{}
	}

	private void opRel(){
		if (tokenEntrada.getNome().equals("LT"))
			casa("LT");
		else if (tokenEntrada.getNome().equals("LE"))
			casa("LE");
		else if (tokenEntrada.getNome().equals("GT"))
			casa("GT");
		else if (tokenEntrada.getNome().equals("GE"))
			casa("GE");
		else if (tokenEntrada.getNome().equals("EQ"))
			casa("EQ");
	}
	
	public void adicao(){
		termo();
		adicaoOpc();
	}

	public void adicaoOpc(){
		if (tokenEntrada.getNome().equals("PLUS") || tokenEntrada.getNome().equals("MINUS")){
			opAdicao();
			termo();
			adicaoOpc();
		}else{}
	}

	public void opAdicao(){
		if (tokenEntrada.getNome().equals("PLUS"))
			casa("PLUS");
		else if (tokenEntrada.getNome().equals("MINUS"))
			casa("MINUS");
	}

	public void termo(){
		fator();
		termoOpc();
	}
	
	public void termoOpc(){
		if (tokenEntrada.getNome().equals("MULT") || tokenEntrada.getNome().equals("DIV")){
			opMult();
			fator();
			termoOpc();
		}else{}
	}

	public void opMult(){
		if (tokenEntrada.getNome().equals("MULT"))
			casa("MULT");
		else if (tokenEntrada.getNome().equals("DIV"))
			casa("DIV");
	}

	public void fator(){
		if (tokenEntrada.getNome().equals("ID"))
			casa("ID");
		else if (tokenEntrada.getNome().equals("INTEGER_CONST"))
			casa("INTEGER_CONST");
		else if (tokenEntrada.getNome().equals("FLOAT_CONST"))
			casa("FLOAT_CONST");
		else if (tokenEntrada.getNome().equals("LBRACKET")){
			casa("LBRACKET");
			expressao();
			casa("RBRACKET");
		}
	}
	
	private boolean casa(String tokenEsperado) {
		if (tokenEntrada.getNome().equals(tokenEsperado)) {
			//System.out.println("Casou " + tokenEsperado);
			i++;
			if (i < tokens.size())
				tokenEntrada = tokens.get(i);

			return true;
		} else {
			System.out.println("ESPERADO " + tokenEsperado + " ANTES DE " + tokenEntrada.getNome() + ".\n");
			return false;
		}
	}

}

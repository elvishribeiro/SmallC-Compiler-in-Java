package analisador;
import arvore.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class AnalisadorSintatico {
	private static AnalisadorSintatico instance = new AnalisadorSintatico();
	
	private ArrayList<Token> tokens;
	private static Hashtable<String, Simbolo> tabelaSimbolos = new Hashtable<String, Simbolo>(); 
	
	private Token tokenEntrada;
	private Integer i = 0;
	private String tipo;
	private Astnode raiz;
	

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
		raiz = new Astnode();
		decl_Comando(raiz);
		casa("RBRACE");
	}
	
	private void decl_Comando(Astnode pai) {
		// printf("Decl_Comando-> "); //remover
		if (tokenEntrada.getNome().equals("INT") || tokenEntrada.getNome().equals("FLOAT")) {
			declaracao(pai);
			decl_Comando(pai);
		} else if (tokenEntrada.getNome().equals("LBRACE") || tokenEntrada.getNome().equals("ID")
				|| tokenEntrada.getNome().equals("IF") || tokenEntrada.getNome().equals("WHILE")
				|| tokenEntrada.getNome().equals("READ") || tokenEntrada.getNome().equals("PRINT")
				|| tokenEntrada.getNome().equals("FOR")) {
			comando(pai);
			decl_Comando(pai);
		} else {}
	}
	
	private void declaracao(Astnode pai){
		//printf("Declaracao-> Tipo ID Decl2-> "); //remover
		tipo = tipo();
		
		Token tokenBackup = tokenEntrada;
		//insere na tabela de simbolos
		Id noId = null;
		if (casa("ID")){
			if (!tabelaSimbolos.containsKey(tokenBackup.getLexema())){
					Simbolo s = new Simbolo(tipo, tokenBackup.getLexema(), tokenBackup.getLinha());
					tabelaSimbolos.put(s.getLexema(), s);
					noId = new Id(s);
			}else{
				System.out.println("REDECLARACAO DE " + tokenBackup.getLexema());
			}
		}
		decl2(pai, noId);
		tipo = null;
	}

	
	private void decl2(Astnode pai, Id noId){
		//printf("Decl2-> "); //remover
		if (tokenEntrada.getNome().equals("COMMA")){
			casa("COMMA");
			Token tokenBackup = tokenEntrada;
			
			if (casa("ID")){
				if (!tabelaSimbolos.containsKey(tokenBackup.getLexema())){
					Simbolo s = new Simbolo(tipo, tokenBackup.getLexema(), tokenBackup.getLinha());
					tabelaSimbolos.put(s.getLexema(), s);
					noId.setSimbolo(s);
				}else{
					System.out.println("REDECLARACAO DE " + tokenBackup.getLexema());
				}
			}
			decl2(pai, noId);
		}else if(tokenEntrada.getNome().equals("PCOMMA")){
			casa("PCOMMA");
		}else if(tokenEntrada.getNome().equals("ATTR")){
			casa("ATTR");
			Expr e = expressao();
			Attr a = new Attr(noId, e);
			pai.addFilho(a);
			decl2(pai, noId);
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
	
	private void comando(Astnode pai){
		//printf("Comando->"); //remover
		if (tokenEntrada.getNome().equals("LBRACE"))
			bloco(pai);
		else if (tokenEntrada.getNome().equals("ID"))
			atribuicao(pai);
		else if (tokenEntrada.getNome().equals("IF"))
			comandoSe(pai);
		else if (tokenEntrada.getNome().equals("WHILE"))
			comandoEnquanto(pai);
		else if (tokenEntrada.getNome().equals("READ"))
			comandoRead(pai);
		else if (tokenEntrada.getNome().equals("PRINT"))
			comandoPrint(pai);
		else if (tokenEntrada.getNome().equals("FOR"))
			comandoFor(pai);
	}
	
	private void bloco(Astnode pai){
		//printf("Bloco-> "); //remover
		casa("LBRACE");
		Astnode bloco = new Astnode("Bloco");
		decl_Comando(bloco);
		casa("RBRACE");
		pai.addFilho(bloco);
	}

	private void atribuicao(Astnode pai){
		//printf("Atribuicao-> "); //remover
		Simbolo s = tabelaSimbolos.get(tokenEntrada.getLexema());
		casa("ID");
		Id noId = new Id(s);
		casa("ATTR");
		Expr e = expressao();
		casa("PCOMMA");
		Attr a = new Attr(noId, e);
		pai.addFilho(a);
	}

	private void comandoSe(Astnode pai){
		//printf("ComandoSe-> "); //remover
		casa("IF");
		casa("LBRACKET");
		If noIf = new If();
		Expr e = expressao();
		casa("RBRACKET");
		noIf.setE(e);
		comando(noIf);
		comandoSenao(noIf);
		pai.addFilho(noIf);
	}

	private void comandoSenao(Astnode pai){
		//printf("ComandoSenao-> "); //remover
		if (tokenEntrada.getNome().equals("ELSE")){
			casa("ELSE");
			comando(pai);
		}
	}

	private void comandoEnquanto(Astnode pai){
		//printf("ComandoEnquanto-> "); //remover
		casa("WHILE");
		casa("LBRACKET");
		While noWhile = new While();
		Expr e = expressao();
		noWhile.setE(e);
		casa("RBRACKET");
		comando(noWhile);
		pai.addFilho(noWhile);		
	}

	private void comandoRead(Astnode pai){
		//printf("ComandoRead-> "); //remover
		Simbolo s = tabelaSimbolos.get(tokenEntrada.getLexema());

		casa("READ");
		Read noRead = new Read();
		Id noId = new Id();
		noId.setSimbolo(s);
		casa("ID");
		noRead.setId(noId);
		casa("PCOMMA");
		pai.addFilho(noRead);
	}

	private void comandoPrint(Astnode pai){
		//printf("ComandoPrint-> "); //remover
		Print noPrint = new Print();
		casa("PRINT");
		casa("LBRACKET");
		Expr e = expressao();
		casa("RBRACKET");
		// noPrint.setE(e);
		casa("PCOMMA");
		pai.addFilho(noPrint);
	}

	private void comandoFor(Astnode pai){
		//printf("ComandoFor-> "); //remover
		For noFor = new For();
		casa("FOR");
		casa("LBRACKET");
		Attr a = atribuicaoFor();
		casa("PCOMMA");
		noFor.setIni(a);

		Expr e = expressao();
		casa("PCOMMA");
		noFor.setCondicao(e);

		a = atribuicaoFor();
		casa("RBRACKET");
		noFor.setInc(a);

		comando(noFor);
	}
	
	private Attr atribuicaoFor(){
		//printf("AtribuicaoFor-> "); //remover
		Simbolo s = tabelaSimbolos.get(tokenEntrada.getLexema());
		Id noId = new Id();
		casa("ID");
		noId.setSimbolo(s);
		casa("ATTR");
		Expr e = expressao();
		Attr a = new Attr(noId, e);
		return a;
	}

	private Expr expressao(){
		//printf("Expressao ->"); //remover
		ArithOp noArithOp = adicao();
		RelOp noRelOp = new RelOp();
		noRelOp.setExpr1(noArithOp);
		noRelOp.setExpr2(relacaoOpc(noRelOp));
		if (noRelOp != null) {
		    return noRelOp; 
		}
		return noArithOp;
	}
	
	public ArithOp adicao(){
		ArithOp noArithOp = new ArithOp();
		noArithOp.setExpr1(termo());
		noArithOp.setExpr2(adicaoOpc(noArithOp));
		return noArithOp;
	}

	
	private RelOp relacaoOpc(RelOp pai){
		if (tokenEntrada.getNome().equals("LT") || tokenEntrada.getNome().equals("LE") || 
			tokenEntrada.getNome().equals("GT") || tokenEntrada.getNome().equals("GE") ||
			tokenEntrada.getNome().equals("EQ")){
			opRel(pai);
			RelOp noRelOp = new RelOp();
			noRelOp.setExpr1(adicao());
			noRelOp.setExpr2(relacaoOpc(noRelOp));
			return noRelOp;
		}else{return null;}
	}

	private void opRel(RelOp pai){
		if (tokenEntrada.getNome().equals("LT")) {
			casa("LT");
		    pai.setOp("<");
		}
		else if (tokenEntrada.getNome().equals("LE")) {
			casa("LE");
			pai.setOp("<=");
		}
		else if (tokenEntrada.getNome().equals("GT")) {
			casa("GT");
			pai.setOp(">");
		}
		else if (tokenEntrada.getNome().equals("GE")) {
			casa("GE");
			pai.setOp(">=");
		}
		else if (tokenEntrada.getNome().equals("EQ")) {
			casa("EQ");
			pai.setOp("==");
		}
	}
	
	public ArithOp adicaoOpc(ArithOp pai){
		if (tokenEntrada.getNome().equals("PLUS") || tokenEntrada.getNome().equals("MINUS")){
			opAdicao(pai);
			ArithOp noArithOp = new ArithOp();
			noArithOp.setExpr1(termo());
			noArithOp.setExpr2(adicaoOpc(noArithOp));
			return noArithOp;
		}else{}
		return null;
	}

	public void opAdicao(ArithOp pai){
		if (tokenEntrada.getNome().equals("PLUS")) {
			casa("PLUS");
			pai.setOp("+");
		}
		else if (tokenEntrada.getNome().equals("MINUS")) {
			casa("MINUS");
		    pai.setOp("i");
		}
	}

	public ArithOp termo(){
		ArithOp noArithOp = new ArithOp();
		noArithOp.setExpr1(fator());
		noArithOp.setExpr2(termoOpc(noArithOp));
		return noArithOp;
	}
	
	public ArithOp termoOpc(ArithOp pai){
		if (tokenEntrada.getNome().equals("MULT") || tokenEntrada.getNome().equals("DIV")){
			opMult(pai);
			ArithOp noArithOp = new ArithOp();
			noArithOp.setExpr1(fator());
			noArithOp.setExpr2(termoOpc(noArithOp));
			return noArithOp;
		}else{}
		return null;
	}

	public void opMult(ArithOp pai){
		if (tokenEntrada.getNome().equals("MULT")) {
			casa("MULT");
			pai.setOp("*");
		}
		else if (tokenEntrada.getNome().equals("DIV")) {
			casa("DIV");
		    pai.setOp("/");
		}
	}

	public Expr fator(){
		
		if (tokenEntrada.getNome().equals("ID")) {
			Simbolo simbolo;
			simbolo = tabelaSimbolos.get(tokenEntrada.getLexema());
			casa("ID");
			Id noId = new Id(simbolo);
			return noId;
		}
		else if (tokenEntrada.getNome().equals("INTEGER_CONST")) {
			float valor = Float.parseFloat(tokenEntrada.getLexema());
			casa("INTEGER_CONST");
			Num noNum = new Num(valor, 0);
			return noNum;
		}
		else if (tokenEntrada.getNome().equals("FLOAT_CONST")) {
			float valor = Float.parseFloat(tokenEntrada.getLexema());
			casa("FLOAT_CONST");
			Num noNum = new Num(valor, 1);
			return noNum;
		}
		else if (tokenEntrada.getNome().equals("LBRACKET")){
			casa("LBRACKET");
			Expr e = expressao();
			casa("RBRACKET");
			return e;
		}
		return null;
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

package analisador;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


//singleton
public class AnalisadorLexico {
	private static AnalisadorLexico instance = new AnalisadorLexico();
	
	
	private AnalisadorLexico() {
	}
	
	public static AnalisadorLexico getInstance() {
		return instance;
	}
	
	public ArrayList<Token> analisa(String arqNome) throws Exception{
		
		File file = new File(arqNome); 
		  
		Scanner reader = new Scanner(file);
		char c = '\0';
		boolean ler = true;
		int linha = 1;
		int state = 0;
		String lexema = "";
		ArrayList<Token> tokens = new ArrayList<Token>();
		reader.useDelimiter("");
		
		while (reader.hasNext()) {
			if (ler)
				c = reader.next().charAt(0);
			else 
				ler = true;
			
			if (c == '\n') {
				linha++;
			}
			
			switch (state){
			case 0:
				lexema = "" + c;
				if (c == ';'){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == ','){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '('){
					
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == ')'){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '{'){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '}'){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '='){
					state = 7;
				}
				else if (c == '<'){
					state = 4;
				}
				else if (c == '>'){
					state = 5;
				}
				else if (c == '+'){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '-'){		
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '*'){		
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '/'){		
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == '['){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (c == ']'){
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				else if (lexema.matches("[0-9]")){
					state = 1;
				}
				else if (lexema.matches("[a-zA-z]")){
					state = 6;
				}
				else if (!lexema.matches("\\s")){
					state = 8;
				}
				
				break;


			//estados para detecção de numeros

			//analisa inteiros
			case 1:
				if (Character.isDigit(c)){
					lexema += c;
				}
				else if (c == '.'){
					lexema += c;
					state = 2;
				}else{
					tokens.add(new Token("INTEGER_CONST", linha, lexema));
					ler = false;								//evita de avançar um caractere
					state = 0;	
				}
				break;
			
			//analisa floats
			case 2:
				if (Character.isDigit(c)){
					lexema += c;
					state = 3;
				}else{
					//erro de sintaxe
					state = 0;
				}
				break;
			
			//casas depois do ponto do float
			case 3:
				if (Character.isDigit(c)){
					lexema += c;
				}else{
					tokens.add(new Token("FLOAT_CONST", linha, lexema));
					ler = false;
					state = 0;
				}
				break;
			
			//estados para classificação de <, <=
			case 4:
				if (c == '=') {
					lexema += c;
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}else{
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				state = 0;
				ler = false;
				break;
			
			case 5:
				if (c == '=') {
					lexema += c;
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}else{
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				state = 0;
				ler = false;
				break;
			
			//Detecção de ID ou comando
			case 6:
				if (Character.isLetter(c) | Character.isDigit(c) | c == '_'){
					lexema += c;
				}else {
					if (Token.lexemaToToken(lexema) != null)		//se for palavra reservada
						tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
					else {
						tokens.add(new Token("ID", linha, lexema));
					}
					ler = false;
					state = 0;
				}
				break;
			
			//EQ ou attr
			case 7:
				if (c == '=') {
					lexema += c;
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}else{
					tokens.add(new Token(Token.lexemaToToken(lexema), linha, lexema));
				}
				state = 0;
				break;
				
			//tokens desconhecidos
			case 8: 
				if (c == ' ' || c == '\n'){
					//novoToken(tokens, numTokens, -1, lexema, linha);
					System.out.println("line "+linha +": error: stray ‘" + lexema +"’ in program");

					ler = false;
					state = 0;
				}else{
					lexema += c;
				}
			}
		}
		
		reader.close();
		return tokens;
	}
}

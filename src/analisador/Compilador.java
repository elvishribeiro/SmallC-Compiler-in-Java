package analisador;
import arvore.Astnode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Compilador {
	public static void main(String args[]) throws Exception{
		Token.iniciarHash();
		AnalisadorLexico al = AnalisadorLexico.getInstance();
		AnalisadorSintatico as = AnalisadorSintatico.getInstance();
		/*File dir = new File("testes_TP2");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	if (child.getName().contains(".c")) {
		    	System.out.println("testes_TP2/"+child.getName());
		    	ArrayList<Token> tokens = al.analisa("testes_TP2/"+child.getName());
				printTokens(tokens);
				Astnode raiz = as.analisa(tokens);
				System.out.println(raiz.geraArvore(0));
				System.out.println(raiz.geraCodigo(0));
		    	}
		    	//ArrayList<Token> tokens = al.analisa(child.getName());
				// printTokens(tokens);
				//Astnode raiz = as.analisa(tokens);
				//System.out.println(raiz.geraArvore(0));
				//System.out.println(raiz.geraCodigo(0));
		    }
		  }*/
		ArrayList<Token> tokens = al.analisa("src/testes_TP2/teste5.c");
		//ArrayList<Token> tokens = al.analisa(args[0]);
		printTokens(tokens);
		Astnode raiz = as.analisa(tokens);
		System.out.println(raiz.geraArvore(0));
		String codigo = raiz.geraCodigo(0);
		//codigo = codigo.replaceAll("\n\n", "\n");
		System.out.println(codigo);
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
	
	//Caso um dia seja implementada a visão gráfica da árvore
		/*public static void geraArvoreImagem(Astnode raiz){
			String type = "dot";
			File out = new File("ASA." + type); 
			try {
			FileWriter fw = new FileWriter(out.getAbsoluteFile());
		    BufferedWriter bw = new BufferedWriter(fw);
		        bw.write("digraph a{");
		        String dots = raiz.geraDot(0,0);
		        bw.write(dots);
		        bw.write("}");
		        bw.close();
			}catch(Exception EX){}
			
		}*/

}

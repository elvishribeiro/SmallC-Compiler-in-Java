import analisador.*;

import arvore.Astnode;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Compilador {
	public static void main(String args[]) throws Exception{
		String nomeArquivo = "";
		Scanner teclado = new Scanner(System.in);
		Token.iniciarHash();
		AnalisadorLexico al = AnalisadorLexico.getInstance();
		AnalisadorSintatico as = AnalisadorSintatico.getInstance();
		
		if (args.length == 0){								//passagem do nome do arquivo por parâmetro
			System.out.print("Digite o nome do arquivo: ");
			nomeArquivo = teclado.nextLine();
		}else{
			nomeArquivo = args[0];
		}
		
		File f = new File(nomeArquivo);
		if(!f.exists()) { 
			System.out.println("Arquivo \"" + nomeArquivo +"\" Inexistente!");
			teclado.close();
			return;
		}
		if(f.isDirectory()) {
			System.out.println("\""+nomeArquivo + "\" é um diretório!");
			teclado.close();
			return;
		}
		
		
		ArrayList<Token> tokens = al.analisa(nomeArquivo);
		printTokens(tokens);
		Astnode raiz = as.analisa(tokens);
		System.out.println(raiz.geraArvore(0));
		String codigo = raiz.geraCodigo(0);
		codigo = codigo.replaceAll("\n\n", "\n");
		System.out.println(codigo);
		teclado.close();
		
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

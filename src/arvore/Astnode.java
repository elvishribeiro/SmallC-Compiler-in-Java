package arvore;

import java.util.ArrayList;

public class Astnode {
	private ArrayList<Astnode> filhos = new ArrayList<Astnode>();
	protected String nome;

	public Astnode() {
		this.nome = "AST";
	}

	public Astnode(String nome) {
		this.nome = nome;
	}

	public ArrayList<Astnode> getFilhos() {
		return filhos;
	}
	public void setFilhos(ArrayList<Astnode> filhos) {
		this.filhos = filhos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addFilho(Astnode filho) {
		filhos.add(filho);
	}
	public String toString() {
		String retorno = nome + "->" ;
		for (Astnode a : filhos) {
			retorno += a.toString() + " - ";
		}
		retorno += "\n";
		return retorno;
	}

	public String geraArvore(int nivel){
		String arvore = "";
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "<"+nome+">\n";
		for (Astnode a: filhos){
			arvore += a.geraArvore(nivel + 1);
		}
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "</"+nome+">\n";
		return arvore;
	}

	public String geraCodigo(int nivel){
		String codigo = "";
		for (Astnode a: filhos){
			codigo+= a.geraCodigo(nivel);
				codigo += "\n";
		}
		//codigo+="\n";
		return codigo;
	}

	
	//Caso um dia seja implementada a visão gráfica da árvore
	/*public String geraDot(int nivel, int num){
		String dot = "";
		
		int Fnum = 0;
		for (Astnode a: filhos){
			dot+= "Astnode" + nivel + num+" -> " + a.nome + ""+(nivel+1)+Fnum +"\n";
			Fnum++;
		}
		Fnum=0;
		for (Astnode a: filhos){
			dot += a.geraDot(nivel + 1, Fnum++);
		}

		return dot;
	}*/
}

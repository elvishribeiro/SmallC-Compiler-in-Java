package arvore;

import java.util.ArrayList;

public class Astnode {
	private ArrayList<Astnode> filhos = new ArrayList<Astnode>();
	protected String nome;
	
	public Astnode() {
		this.nome = "Astnode";
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
}

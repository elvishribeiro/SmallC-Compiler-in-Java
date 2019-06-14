package arvore;

import java.util.ArrayList;

public class Astnode {
	private ArrayList<Astnode> filhos = new ArrayList<Astnode>();
	protected String nome;
	protected String op;
	protected int tipo;
	protected String lexema;
	protected float valor;
	
	public Astnode() {
		this.nome = "Astnode";
		this.op = null;
		this.tipo = 0;
		this.lexema = null;
		this.valor = 0;
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
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
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

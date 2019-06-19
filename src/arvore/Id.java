package arvore;
import analisador.Simbolo;

public class Id extends Expr{
	private Simbolo simbolo;
	
	public Id() {
		this.nome = "Id";
		this.simbolo = null;
	}
	
	public Id(Simbolo simbolo) {
		this.nome = this.getClass().getName();
		this.simbolo = simbolo;
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(Simbolo simbolo) {
		this.simbolo = simbolo;
	}
	
	public String toString() {
		return nome;
	}

	public String geraArvore(int nivel){
		String arvore = "";
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "<Id lexema='"+ simbolo.getLexema() + "' type='" +simbolo.getTipo() + "'/>\n";
		return arvore;
	}

	public String geraCodigo(int nivel){
		String codigo = "";
		for (int i = 0; i < nivel*4; i++) codigo+=" ";
		codigo += simbolo.getLexema();
		return codigo;

	}
}

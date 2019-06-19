package arvore;

public class While extends Astnode{
	private Expr e;
	private Astnode c_true;

	public While(){
		this.nome = "While";
		this.e = null;
		this.c_true = null;
	}

	public Expr getE() {
		return e;
	}
	public void setE(Expr e) {
		this.e = e;
	}
	public Astnode getC_true() {
		return c_true;
	}
	public void setC_true(Astnode c_true) {
		this.c_true = c_true;
	}

	public void addFilho(Astnode filho){
		if (this.c_true == null){
			this.c_true = filho;
		}
	}
	
	public String toString() {
		String resultado = "";
		resultado += e + " - " + c_true;
		return resultado;
	}

	public String geraArvore(int nivel){
		String arvore = "";
		for(int i = 0; i < nivel*4; i++) arvore+= " ";
		arvore += "<While>\n";
		arvore += e.geraArvore(nivel + 1);
		arvore += c_true.geraArvore(nivel + 1);
		for(int i = 0; i < nivel*4; i++) arvore+= " ";
		arvore += "</While>\n";
		
		return arvore;
	}
}

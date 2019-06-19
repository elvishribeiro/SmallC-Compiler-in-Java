package arvore;

public class Print extends Astnode{
	private Expr expr;

	public Print(){
		this.expr = null;
		this.nome = "Print";
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	
	public String geraArvore(int nivel){
		String arvore = "";
		for(int i = 0; i < nivel*4; i++) arvore+= " ";
		arvore += "<Print>\n";
		arvore += expr.geraArvore(nivel + 1);
		for(int i = 0; i < nivel*4; i++) arvore+= " ";
		arvore += "</Print>\n";

		return arvore;
	}

}

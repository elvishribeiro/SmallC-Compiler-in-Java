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
	
	public String geraCodigo(int nivel) {
		String codigo = "";
		for (int i = 0; i < nivel*4; i++)codigo += " ";      //identacao
		if (expr.getNome().equals("Id")) {
			codigo += "print(\"Valor da variável " + expr.getLexema() +
					":\" str("+ expr.getLexema() + "))";
		}else {
			codigo += "print(str("+ expr.geraCodigo(0) + "))";
		}

		return codigo;
	}

}

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
}

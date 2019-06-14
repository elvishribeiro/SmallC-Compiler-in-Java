package arvore;

public class RelOp extends Expr{
	private Expr expr1;
	private Expr expr2;
	
	public RelOp(){
		this.expr1 = null;
		this.expr2 = null;
		this.nome = "RelOp";
	}
	
	public Expr getExpr1() {
		return expr1;
	}
	public void setExpr1(Expr expr1) {
		this.expr1 = expr1;
	}
	public Expr getExpr2() {
		return expr2;
	}
	public void setExpr2(Expr expr2) {
		this.expr2 = expr2;
	}
}

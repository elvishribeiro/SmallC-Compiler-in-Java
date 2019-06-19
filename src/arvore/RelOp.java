package arvore;

public class RelOp extends Expr{
	private Expr expr1;
	private Expr expr2;
	protected String op;

	
	public RelOp(){
		this.expr1 = null;
		this.expr2 = null;
		this.nome = "RelOp";
		this.op = null;
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
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}

	public String geraArvore(int nivel){
		String arvore = "";
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "<RelOp op='"+op+"'>\n";
		arvore+= expr1.geraArvore(nivel + 1);
		arvore+= expr2.geraArvore(nivel + 1);
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "</RelOp>\n";
		return arvore;
	}

	public String geraCodigo(int nivel){
		String codigo = "";
		for (int i = 0; i < nivel*4; i++)codigo += " ";      //identacao

		if (expr1 instanceof Id || expr1 instanceof Num)       //se for id ou num nao precisa colocar parenteses
			codigo += expr1.geraCodigo(0);	
		else
			codigo += "(" + expr1.geraCodigo(0) +")";
			
		codigo += " " + op + " ";

		if (expr2 instanceof Id || expr2 instanceof Num)
			codigo += expr2.geraCodigo(0);	
		else
		codigo += "(" + expr2.geraCodigo(0) +")";
		return codigo;
	}
}

package arvore;

public class If extends Astnode{
	private Expr e;
	private Astnode c_true;
	private Astnode c_false;
	
	
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
	public Astnode getC_false() {
		return c_false;
	}
	public void setC_false(Astnode c_false) {
		this.c_false = c_false;
	}
}

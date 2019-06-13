package arvore;

public class Attr extends Astnode{
	private Id id;
	private Expr e;
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Expr getE() {
		return e;
	}
	public void setE(Expr e) {
		this.e = e;
	}
}

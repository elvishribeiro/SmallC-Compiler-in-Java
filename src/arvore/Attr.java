package arvore;

public class Attr extends Astnode{
	private Id id;
	private Expr e;
	
	public Attr() {
		this.nome = "Attr";
		this.id = null;
		this.e = null;

	}
	
	public Attr(Id id, Expr e) {
		this.nome = this.getClass().getName();
		this.id = id;
		this.e = e;
	}
	
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

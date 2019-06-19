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

	public String geraArvore(int nivel){
		String arvore = "";
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "<ATTR>\n";
		arvore+= id.geraArvore(nivel + 1);
		arvore+= e.geraArvore(nivel + 1);
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "</ATTR>\n";
		return arvore;
	}
	
	
}

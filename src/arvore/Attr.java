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
		this.nome = "Attr";
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
	
	public String geraCodigo(int nivel){
		String codigo = "";
		for (int i = 0; i < nivel*4; i++)codigo += " ";      //identacao
		codigo += id.geraCodigo(0) + " = " + e.geraCodigo(0);
		return codigo;
	}

	//Caso um dia seja implementada a visão gráfica da árvore
	/*public String geraDot(int nivel, int num){
		int Fnum = 0;
		String dot = "Attr" + nivel + num+" -> Id" + (nivel+1)+Fnum+"()\n";
		Fnum++;
		dot += "Attr" + nivel + num + " -> " + e.nome + ""+ (nivel+1)+Fnum+"\n"; 
		return dot;
	}*/
	
}

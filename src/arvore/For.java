package arvore;

public class For extends Astnode {
	private Attr ini;
	private Expr condicao;
	private Attr inc;
	private Astnode c_true;

	public For(){
		this.nome = "For";
		this.ini = null;
		this.condicao = null;
		this.inc = null;
		this.c_true = null;

	}

	public Attr getIni() {
		return ini;
	}

	public void setIni(Attr ini) {
		this.ini = ini;
	}

	public Expr getCondicao() {
		return condicao;
	}

	public void setCondicao(Expr condicao) {
		this.condicao = condicao;
	}

	public Attr getInc() {
		return inc;
	}

	public void setInc(Attr inc) {
		this.inc = inc;
	}

	public Astnode getC_true() {
		return c_true;
	}

	public void setC_true(Astnode c_true) {
		this.c_true = c_true;
	}

	public void addFilho(Astnode filho){
		this.c_true = filho;
	}

	public String geraArvore(int nivel){
		String arvore = "";
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "<For>\n";
		arvore+= ini.geraArvore(nivel + 1);
		arvore+= condicao.geraArvore(nivel + 1);
		arvore+= inc.geraArvore(nivel + 1);
		arvore+=c_true.geraArvore(nivel + 1);
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		arvore+= "</For>\n";
		return arvore;
	}

	public String toString(){
		return "" + ini +" "+ condicao +" "+ inc +" "+ c_true;
	}
	
	public String geraCodigo(int nivel){
		String codigo = "";
		for (int i = 0; i < nivel*4; i++)codigo += " ";      //identacao
		codigo += ini.geraCodigo(nivel) + "\n";
		codigo += "while " + condicao.geraCodigo(0) +":\n";
		codigo += c_true.geraCodigo(nivel + 1); 
		codigo += inc.geraCodigo(nivel + 1);

		return codigo;
	}
}

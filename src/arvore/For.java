package arvore;

public class For extends Astnode {
	private Attr ini;
	private Expr condicao;
	private Attr inc;
	private Astnode c_true;

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
}

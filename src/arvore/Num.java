package arvore;

public class Num extends Expr{
	protected float valor;
	protected int tipo;
	
    public Num(){
        this.nome = "Num";
        this.valor = 0;
        this.tipo = 0;
    }
    
    public Num(Float valor, int tipo) {
    	this.valor = valor;
    	this.tipo = tipo;
    	this.nome = "Num";
    }

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String geraArvore(int nivel){
		String arvore = "";
		String tipoString = "";
		for (int i = 0; i < nivel*4; i++) arvore+=" ";
		if (tipo == 0) tipoString = "INT";
		else tipoString = "FLOAT";
		arvore+= "<Num value="+ valor+" type='"+tipoString+"' />\n";
		return arvore;
	}

	public String geraCodigo(int nivel){
		String codigo = "";
		for (int i = 0; i < nivel*4; i++) codigo+=" ";
		codigo += valor;
		return codigo;
	}
}

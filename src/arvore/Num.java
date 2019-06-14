package arvore;

public class Num extends Expr{
    
    public Num(){
        this.nome = "Num";
        this.valor = 0;
        this.tipo = 0;
    }
    
    public Num(Float valor, int tipo) {
    	this.valor = valor;
    	this.tipo = tipo;
    }
}

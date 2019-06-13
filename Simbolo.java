
public class Simbolo {
	private String tipo;
	private String lexema;
	private Integer linha;
	
	public Simbolo() {}
	public Simbolo(String tipo, String lexema, Integer linha) {
		this.tipo = tipo;
		this.lexema = lexema;
		this.linha = linha;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public Integer getLinha() {
		return linha;
	}
	public void setLinha(Integer linha) {
		this.linha = linha;
	}
	
	public String toString() {
		return String.format("Lexema: %-10s Tipo: %-6s Linha:%-5d\n", 
				lexema, tipo, linha);
	}
	
}

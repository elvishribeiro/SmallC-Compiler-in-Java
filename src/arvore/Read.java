package arvore;

public class Read extends Astnode{
	private Id id;

	public Read(){
		this.id = null;
		this.nome = "Read";
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
}

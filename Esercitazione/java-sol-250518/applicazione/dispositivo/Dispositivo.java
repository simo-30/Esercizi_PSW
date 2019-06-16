package applicazione.dispositivo;

public abstract class Dispositivo {
	
	protected final String nome;
	
	public Dispositivo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}

public class Progetto {
	private final String nome;
	private final int budget;
	
	public Progetto (String n, int b) {
		nome=n;
		budget=b;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getBudget() {
		return budget;
	}
}

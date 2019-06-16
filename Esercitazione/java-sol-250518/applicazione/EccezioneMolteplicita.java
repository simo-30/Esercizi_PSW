package applicazione;

@SuppressWarnings("serial")
public class EccezioneMolteplicita extends RuntimeException {
	String messaggio;
	
	public EccezioneMolteplicita(String m) {
		messaggio = m;
	}
	
	public String toString() {
		return messaggio;
	}
}

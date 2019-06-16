package applicazione;

@SuppressWarnings("serial")
public class EccezionePrecondizioni extends RuntimeException {
	String messaggio;
	
	public EccezionePrecondizioni(String m) {
		messaggio = m;
	}
	
	public String toString() {
		return messaggio;
	}
}

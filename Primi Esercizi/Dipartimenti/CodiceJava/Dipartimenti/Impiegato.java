import java.util.*

public class Impiegato {
	private final String nome;
	private int eta, stipendio;
	private TipoLinkAfferisce dipartimento;
	private HashSet<Progetto> progetti;
	
	public Impiegato(String n, int e, int s, TipoLinkAfferisce d) {
		nome=n;
		eta=e;
		stipendio=s;
		dipartimento=d;
		progetti=new HashSet<Progetto>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getEta() {
		return eta;
	}
	
	public int getStipendio() {
		return stipendio;
	}
	
	public TipoLinkAfferisce getLinkAfferisce() {
		return dipartimento;
	}
	
	public Set<Progetto> getProgetti() {
		return (Set<Progetto>)progetti.clone();
	}
	
	public void changeDipartimento(TipoLinkAfferisce link) {
		if (link!=null && link.getImpiegato==this) {
			dipartimento=link;
		}
	}
	
	public void inserisciProgetto(Progetto p) {
		if (p!=null) {
			progetti.add(p);
		}
	}
	
	public void eliminaProgetto(Progetto p) {
		if (p!=null) {
			progetti.remove(p);
		}
	}
	
	public void setStipendio(int s) {
		stipendio=s;
	}
	
	public void incrementaEta() {
		eta++;
	}
}

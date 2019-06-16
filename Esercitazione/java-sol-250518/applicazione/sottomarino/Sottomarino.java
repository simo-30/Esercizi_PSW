package applicazione.sottomarino;

import java.util.HashSet;
import java.util.Set;

import _framework.TaskExecutor;
import _gestioneeventi.Evento;
import _gestioneeventi.Listener;
import applicazione.EccezioneMolteplicita;
import applicazione.ManagerContiene;
import applicazione.ManagerInclude;
import applicazione.TipoLinkContiene;
import applicazione.TipoLinkInclude;
import applicazione.batiscafo.Batiscafo;
import applicazione.dispositivo.Dispositivo;

public class Sottomarino extends Dispositivo implements Listener{
	
	public static final int MOLT_MIN_INCLUDE = 1;

	protected int profonditaMax;
	protected TipoLinkContiene contiene;
	protected HashSet<TipoLinkInclude> include;
	
	public Sottomarino(String nome, int profonditaMax) {
		super(nome);
		this.profonditaMax = profonditaMax;
		this.include = new HashSet<>();
	}

	public int getProfonditaMax() {
		return profonditaMax;
	}

	public void setProfonditaMax(int profonditaMax) {
		this.profonditaMax = profonditaMax;
	}

	public void inserisciLinkContiene(TipoLinkContiene tlc) {
		if(tlc != null && tlc.getSottomarino() == this) {
			ManagerContiene.inserisci(tlc);
		}
	}

	public void inserisciPerManagerContiene(ManagerContiene m) {
		if(m != null) {
			contiene = m.getLink();
		}
	}
	
	public void eliminaLinkContiene(TipoLinkContiene tlc) {
		if(tlc != null && tlc.getSottomarino() == this) {
			ManagerContiene.elimina(tlc);
		}
	}

	public void eliminaPerManagerContiene(ManagerContiene m) {
		if (m != null)
			contiene = null;
	}
	
	public int quantiContiene() {
		return (contiene == null)? 0 : 1;
	}
	
	public TipoLinkContiene getLinkContiene() {
		if(contiene == null)
			throw new EccezioneMolteplicita("Violata molteplicita' in " + this.getClass());
		else return contiene;
	}
	
	public void inserisciLinkInclude(TipoLinkInclude tli) {
		if(tli != null && tli.getSottomarino() == this) {
			ManagerInclude.inserisci(tli);
		}
	}
	
	public void inserisciPerManagerInclude(ManagerInclude m) {
		if(m != null) {
			this.include.add(m.getLink());
		}
	}
	
	public void eliminaPerManagerInclude(ManagerInclude m) {
		if(m != null) {
			this.include.remove(m.getLink());
		}
	}
	
	public int quantiInlcude() {
		return include.size();
	}
	
	@SuppressWarnings("unchecked")
	public Set<TipoLinkInclude> getLinkInclude(){
		if(include.size() < MOLT_MIN_INCLUDE)
			throw new EccezioneMolteplicita("Violata molteplicita' in " + this.getClass());
		else return (Set<TipoLinkInclude>) include.clone();
	}

	// Gestione Stato
	
	public static enum Stato {
		A_BORDO, REG_BATISCAFO, REG_AUTONOMA, ATTENDI_BATISCAFO
	}

	Stato statocorrente = Stato.A_BORDO; // nota visibilita' package
	int profonditaAttuale = 0;

	Batiscafo selezionato = null; // nota visibilita' package

	public Stato getStato() {
		return statocorrente;
	}

	public void fired(Evento e) {
		TaskExecutor.getInstance().perform(new SottomarinoFired(this, e));
	}
}

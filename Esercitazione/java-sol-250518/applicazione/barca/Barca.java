package applicazione.barca;

import java.util.HashSet;
import java.util.Set;

import _framework.TaskExecutor;
import _gestioneeventi.Evento;
import _gestioneeventi.Listener;
import applicazione.EccezioneMolteplicita;
import applicazione.ManagerContiene;
import applicazione.TipoLinkContiene;
import applicazione.dispositivo.Dispositivo;

public class Barca extends Dispositivo implements Listener{
	
	public static final int MOLT_MIN = 1;
	
	protected double velocita;
	protected HashSet<TipoLinkContiene> contiene;
	
	public Barca(String nome, double velocita) {
		super(nome);
		this.velocita = velocita;
		this.contiene = new HashSet<>();
	}

	public double getVelocita() {
		return velocita;
	}

	public void setVelocita(double velocita) {
		this.velocita = velocita;
	}

	public void inserisciLinkContiene(TipoLinkContiene tlc) {
		if(tlc != null && tlc.getBarca() == this) {
			ManagerContiene.inserisci(tlc);
		}
	}

	public void inserisciPerManagerContiene(ManagerContiene m) {
		if(m != null) {
			contiene.add(m.getLink());
		}
	}
	
	public void eliminaLinkContiene(TipoLinkContiene tlc) {
		if(tlc != null && tlc.getBarca() == this) {
			ManagerContiene.elimina(tlc);
		}
	}

	public void eliminaPerManagerContiene(ManagerContiene m) {
		if (m != null)
			contiene.remove(m.getLink());
	}
	
	public int quantiContiene() {
		return contiene.size();
	}
	
	@SuppressWarnings("unchecked")
	public Set<TipoLinkContiene> getLinkContiene(){
		if(contiene.size() < MOLT_MIN)
			throw new EccezioneMolteplicita("Violata molteplicita' in " + this.getClass());
		else return (Set<TipoLinkContiene>) contiene.clone();
	}
	
	// Gestione Stato
	
	public static enum Stato{
		PRONTO
	};
	
	Stato statocorrente = Stato.PRONTO;
	
	public Stato getStato() {
		return statocorrente;
	}

	@Override
	public void fired(Evento e) {
		TaskExecutor.getInstance().perform(new BarcaFired(this, e));
	}
}

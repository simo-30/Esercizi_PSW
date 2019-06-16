package applicazione.batiscafo;

import _framework.TaskExecutor;
import _gestioneeventi.Evento;
import _gestioneeventi.Listener;
import applicazione.EccezioneMolteplicita;
import applicazione.ManagerInclude;
import applicazione.TipoLinkInclude;
import applicazione.dispositivo.Dispositivo;

public class Batiscafo extends Dispositivo implements Listener{
	
	protected double peso;
	protected TipoLinkInclude include;

	public Batiscafo(String nome, double peso) {
		super(nome);
		this.peso = peso;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void inserisciLinkInclude(TipoLinkInclude tli) {
		if(tli != null && tli.getBatiscafo() == this) {
			ManagerInclude.inserisci(tli);
		}
	}
	
	public void inserisciPerManagerInclude(ManagerInclude m) {
		if(m != null) {
			this.include = m.getLink();
		}
	}
	
	public void eliminaPerManagerInclude(ManagerInclude m) {
		if(m != null) {
			this.include = null;
		}
	}
	
	public int quantiInlcude() {
		return (include == null)? 0 : 1;
	}
	
	public TipoLinkInclude getLinkInclude(){
		if(include == null)
			throw new EccezioneMolteplicita("Violata molteplicita' in " + this.getClass());
		else return include;
	}

	// Gestione Stato
	
	public static enum Stato{
		PRONTO, REGISTRAZIONE
	}
	
	Stato statoattuale = Stato.PRONTO;
	int profonditaAttuale = 0;
	
	public void setProfonditaAttuale(int p) {
		profonditaAttuale = p;
	}
	
	public Stato getStato() {
		return statoattuale;
	}
	
	public void fired(Evento e) {
		TaskExecutor.getInstance().perform(new BatiscafoFired(this, e));
	}
}

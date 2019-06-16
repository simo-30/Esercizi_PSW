package applicazione.eventi;

import _gestioneeventi.Evento;
import _gestioneeventi.Listener;

public class Registra extends Evento {

	public int profondita;
	
	public Registra(Listener m, Listener d, int profondita) {
		super(m, d);
		this.profondita = profondita;
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

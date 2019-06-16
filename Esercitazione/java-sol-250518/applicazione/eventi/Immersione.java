package applicazione.eventi;

import _gestioneeventi.Evento;
import _gestioneeventi.Listener;

public class Immersione extends Evento {
	
	public int profondita;

	public Immersione(Listener m, Listener d, int p) {
		super(m, d);
		profondita = p;
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

package applicazione.eventi;

import _gestioneeventi.Evento;
import _gestioneeventi.Listener;
import applicazione.sottomarino.Sottomarino;

public class InvioImmersione extends Evento {
	
	public int profondita;
	public Sottomarino sottomarino;
	
	public InvioImmersione(Listener m, Listener d, Sottomarino s, int p) {
		super(m, d);
		profondita = p;
		this.sottomarino = s;
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

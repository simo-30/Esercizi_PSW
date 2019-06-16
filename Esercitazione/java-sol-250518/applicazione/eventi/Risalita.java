package applicazione.eventi;

import _gestioneeventi.Evento;
import _gestioneeventi.Listener;

public class Risalita extends Evento {

	public Risalita(Listener m, Listener d) {
		super(m, d);
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

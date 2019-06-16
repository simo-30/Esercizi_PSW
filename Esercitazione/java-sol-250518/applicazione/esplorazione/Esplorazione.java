package applicazione.esplorazione;

import java.util.HashSet;
import java.util.Set;

import applicazione.EccezioneMolteplicita;
import applicazione.dispositivo.Dispositivo;
import applicazione.zonamare.ZonaMare;

public class Esplorazione {
	
	protected final String nome;
	protected HashSet<Dispositivo> associato;
	protected ZonaMare riguarda;

	public Esplorazione(String nome) {
		this.nome = nome;
		associato = new HashSet<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public int quantiRiguarda() {
		return (riguarda == null)? 0 : 1;
	}
	
	public void inserisciRiguarda(ZonaMare zm) {
		if(zm != null)
			this.riguarda = zm;
	}
	
	public void eliminaRiguarda() {
		riguarda = null;
	}
	
	public ZonaMare getRiguarda() {
		if(riguarda == null)
			throw new EccezioneMolteplicita("Violata molteplicita' in " + this.getClass());
		else return riguarda;
	}
	
	public void inserisciAssociato(Dispositivo d) {
		if(d != null)
			associato.add(d);
	}
	
	public void eliminaAssociato(Dispositivo d) {
		if(d != null)
			associato.remove(d);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Dispositivo> getAssociato(){
		return (Set<Dispositivo>) this.associato.clone();
	}
}

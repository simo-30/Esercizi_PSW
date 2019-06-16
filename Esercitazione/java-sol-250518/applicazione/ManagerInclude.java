package applicazione;

public class ManagerInclude {
	
	private TipoLinkInclude link;
	
	private ManagerInclude(TipoLinkInclude tli) {
		link = tli;
	}
	
	public static void inserisci(TipoLinkInclude tli) {
		if(tli != null) {
			ManagerInclude m = new ManagerInclude(tli);
			tli.getSottomarino().inserisciPerManagerInclude(m);
			tli.getBatiscafo().inserisciPerManagerInclude(m);
		}
	}
	
	public static void elimina(TipoLinkInclude tli) {
		if(tli != null) {
			ManagerInclude m = new ManagerInclude(tli);
			tli.getSottomarino().eliminaPerManagerInclude(m);
			tli.getBatiscafo().eliminaPerManagerInclude(m);
		}
	}
	
	public TipoLinkInclude getLink() {
		return link;
	}
}

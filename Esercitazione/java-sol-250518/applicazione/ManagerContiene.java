package applicazione;

public class ManagerContiene {
	
	private final TipoLinkContiene link;
	
	private ManagerContiene(TipoLinkContiene l) {
		link = l;
	}
	
	public static void inserisci(TipoLinkContiene l) {
		if(l != null) {
			ManagerContiene m = new ManagerContiene(l);
			l.getBarca().inserisciPerManagerContiene(m);
			l.getSottomarino().inserisciPerManagerContiene(m);
		}
	}
	
	public TipoLinkContiene getLink() {
		return link;
	}

	public static void elimina(TipoLinkContiene tlc) {
		if(tlc != null) {
			ManagerContiene m = new ManagerContiene(tlc);
			tlc.getBarca().eliminaPerManagerContiene(m);
			tlc.getSottomarino().eliminaPerManagerContiene(m);
		}
	}
}

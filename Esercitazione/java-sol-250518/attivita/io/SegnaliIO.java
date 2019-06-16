package attivita.io;

import java.awt.Dialog.ModalityType;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import applicazione.barca.Barca;
import attivita.atomiche.VerificaDisponibili.DaContare;

public final class SegnaliIO {
	
	private SegnaliIO() { }
	
	public static void attendiStop() {
		JOptionPane pane = new JOptionPane("Premi Ok per interrompere l'esplorazione");
		JDialog dialog = pane.createDialog(null, "Stop Esplorazione");
		dialog.setModalityType(ModalityType.DOCUMENT_MODAL);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	public static void stampaFine() {
		JOptionPane.showMessageDialog(null, "Fine Esplorazione!");
	}
	
	public static void stampaReport(HashMap<Barca, HashMap<DaContare, Integer>> count) {
		StringBuffer sb = new StringBuffer();
		for(Barca b : count.keySet()) {
			sb.append("    Barca: " + b.getNome() + "\n");
			for(DaContare tipo : count.get(b).keySet()) {
				sb.append("\t");
				sb.append((tipo == DaContare.SOTTOMARINO)? "Sottomarini: " : "Batiscafi: ");
				sb.append("\t");
				sb.append(count.get(b).get(tipo) + "\n");
			}
			sb.append("\n");
		}
		
		JDialog dialog = new JDialog(new JFrame(), "REPORT - Dispositivi non in uso");
		dialog.setModalityType(ModalityType.MODELESS);
		JTextArea tArea = new JTextArea(sb.toString());
		tArea.setEditable(false);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.add(tArea);
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
}

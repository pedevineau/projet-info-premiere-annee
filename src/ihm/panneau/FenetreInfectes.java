package ihm.panneau;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FenetreInfectes extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	
	static final int LARGEUR = 300;
	static final int HAUTEUR = 500;
	
	public List liste = new List();
	
	
	public FenetreInfectes(ArrayList<String> listeQuartiersInfectes){
				
		addWindowListener(this);

		
		setSize(300,500);
		setResizable(false);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteurEcran = (int) dimension.getHeight();
		int largeurEcran = (int) dimension.getWidth();
		setLocation((largeurEcran - LARGEUR) / 2, (hauteurEcran - HAUTEUR) / 2);
	}

	public void actualiseFenetresInfectes(ArrayList<String> listeQuartiersInfectes){
		for(int i = 0; i < listeQuartiersInfectes.size(); i++){
			if(!isDansListe(listeQuartiersInfectes.get(i))){
				liste.add(listeQuartiersInfectes.get(i));
			}
		}
		add(liste);
	}
	
	public boolean isDansListe(String string){
		String[] tableau = liste.getItems();
		for(int i = 0; i < tableau.length; i++){
			if(tableau[i].equals(string)){
				return true;
			}
		}
		return false;
	}

	
	@Override
	public void windowActivated(WindowEvent e) {
		liste.requestFocus();		
	}


	@Override
	public void windowClosed(WindowEvent e) {
	}


	@Override
	public void windowClosing(WindowEvent e) {
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
	}


	@Override
	public void windowIconified(WindowEvent e) {
	}


	@Override
	public void windowOpened(WindowEvent e) {
	}

	
}

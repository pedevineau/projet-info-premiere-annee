package ihm.menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controleurs.Pandemia;
import utils.YAML.LectureYAML;

public class MenuSauvegarderPartie extends JFrame  {
	
	// ce menu est particulier car c'est le seul auquel
	// on accède en cours de partie. A cette fin, c'est un JFrame.

	private static final long serialVersionUID = 1L;
	
	final static int LARGEUR = 800;
	final static int HAUTEUR = 600; 
	
	JLabel sauvegardesAnterieures;
	JLabel nomNouvelleSauvegarde;
	public JTextField nomSauvegarde;
	public JButton sauvegarder;
	public JButton retour;
	public JButton quitter;
	
	static String chaine;
	
	public MenuSauvegarderPartie(){
		setLayout(new GridLayout(6,1));

		chaine = "";
		
		nomNouvelleSauvegarde = new JLabel("Entrez un nom pour votre sauvegarde");
		nomNouvelleSauvegarde.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(nomNouvelleSauvegarde);
		nomSauvegarde = new JTextField();
		add(nomSauvegarde);
		
		sauvegardesAnterieures = new JLabel();
		sauvegardesAnterieures.setText("Sauvegardes antérieures : " + getStringSauvegardes(Pandemia.URL_SAUVEGARDES));
		sauvegardesAnterieures.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(sauvegardesAnterieures);

		
		sauvegarder = new JButton("Sauvegarder");
		add(sauvegarder);
		
		retour = new JButton("Retour au jeu");
		add(retour);
		
		quitter = new JButton("Quitter Pandemia");
		add(quitter);
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteurEcran = (int)dimension.getHeight();
		int largeurEcran = (int)dimension.getWidth();
		setSize(LARGEUR, HAUTEUR);
		setLocation((largeurEcran-LARGEUR)/2, (hauteurEcran-HAUTEUR)/2);
	}
	
	public static String getStringSauvegardes(String urlYAML){
		@SuppressWarnings("unchecked")
		Map<String, String> sauvegardes = (Map<String, String>) LectureYAML.getConfigs(Pandemia.URL_SAUVEGARDES);
		if(sauvegardes != null){
			for (Map.Entry<String, String> entree : sauvegardes.entrySet()) {
				chaine += entree.getKey() + ". ";
			}
		}
	    return chaine;
	}	
}

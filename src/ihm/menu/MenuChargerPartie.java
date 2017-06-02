package ihm.menu;
import java.awt.GridLayout;
import java.awt.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import controleurs.Pandemia;
import utils.YAML.LectureYAML;

public class MenuChargerPartie extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	final static int LARGEUR = 800;
	final static int HAUTEUR = 600;
	
	public List listeSauvegardes = new List();
	public JButton charger = new JButton("Charger sauvegarde");
	public JButton retour = new JButton("Retour");
	
	public String sauvegardeChoisie;
	
	public void actualiseListeSauvegardes(){
		@SuppressWarnings("unchecked")
		Map<String, String> sauvegardes = (Map<String, String>) LectureYAML.getConfigs(Pandemia.URL_SAUVEGARDES);
		if(sauvegardes != null){
			for (Map.Entry<String, String> entree : sauvegardes.entrySet()) {
				listeSauvegardes.add(entree.getKey());
			}
		}
	}
	
	public MenuChargerPartie() {
		setLayout(new GridLayout(3, 1));
		actualiseListeSauvegardes();
		add(listeSauvegardes);
		add(charger);
		add(retour);
		
	}
	
}

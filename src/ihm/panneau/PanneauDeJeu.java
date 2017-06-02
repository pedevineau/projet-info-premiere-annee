package ihm.panneau;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import modeles.Metropole;

public class PanneauDeJeu extends JPanel {

	private static final long serialVersionUID = 1L;

	public BandeauSuperieur bds;
	public BandeauAlertes bda;
	public Carte carte;
	public BandeauInferieur bdi;
	public Metropole metropole;
	public int indexSelectionne;
	
	final static int LARGEUR_BANDEAU_SUPERIEUR = 1000;
	final static int HAUTEUR_BANDEAU_SUPERIEUR = 40;	
	final static int LARGEUR_BANDEAU_ALERTES = 200;
	final static int HAUTEUR_BANDEAU_ALERTES = 500;	
	final static int LARGEUR_CARTE = 800;
	final static int HAUTEUR_CARTE = 500;
	final static int LARGEUR_BANDEAU_INFERIEUR = 1000;
	final static int HAUTEUR_BANDEAU_INFERIEUR = 120;	
	final static int LARGEUR_QUARTIER = 160;
	final static int HAUTEUR_QUARTIER = 100;

	public PanneauDeJeu(Metropole metropole, int indexSelectionne, String urlCarte) {
		super();
		this.metropole = metropole;
		this.indexSelectionne = indexSelectionne;
		bds = new BandeauSuperieur(metropole);
		bda = new BandeauAlertes();
		try {
			carte = new Carte(new File(urlCarte), new Dimension(LARGEUR_CARTE, HAUTEUR_CARTE), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bdi = new BandeauInferieur(metropole, indexSelectionne);
		
		setLayout(null);
		bds.setBounds(0, 0, LARGEUR_BANDEAU_SUPERIEUR, HAUTEUR_BANDEAU_SUPERIEUR);
		bda.setBounds(0, HAUTEUR_BANDEAU_SUPERIEUR, LARGEUR_BANDEAU_ALERTES, HAUTEUR_BANDEAU_ALERTES);
		carte.setBounds(LARGEUR_BANDEAU_ALERTES, HAUTEUR_BANDEAU_SUPERIEUR, LARGEUR_CARTE, HAUTEUR_CARTE);
		bdi.setBounds(0, HAUTEUR_BANDEAU_SUPERIEUR + HAUTEUR_BANDEAU_ALERTES, LARGEUR_BANDEAU_INFERIEUR, HAUTEUR_BANDEAU_INFERIEUR);
		add(bds);
		add(bda);
		add(carte);
		add(bdi);
	}
	
	public int getIndexSelectionne(){
		return indexSelectionne;
	}
	
	public void setIndexSelectionne(int index){
		indexSelectionne = index;
	}
	
	public void actualisePanneauDeJeu(){
		bds.actualiserBandeauSuperieur();
		bda.repaint();	
		bdi.actualiseBandeauInferieur(metropole, metropole.ville[indexSelectionne]);
		carte.repaint();
	}

}

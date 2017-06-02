package ihm.panneau;

import java.awt.GridLayout;

import javax.swing.JPanel;

import modeles.Metropole;
import modeles.Quartier;
import modeles.Technologie;

public class BandeauInferieur extends JPanel {

	private static final long serialVersionUID = 1L;

	final static int INFORMATIONS_LARGEUR_HISTO = 200;
	final static int INFORMATIONS_LARGEUR_QUADRUPLE = 185;
	final static int INFORMATIONS_HAUTEUR_TEXTE = 25;
	final static int INFORMATIONS_HAUTEUR_CONTENU = 100;
	final static int ACTIONS_LARGEUR = 615;
	final static int ACTIONS_HAUTEUR_TEXTE = 25;
	final static int ACTIONS_HAUTEUR_CONTENU = 0;


	Metropole metropole;
	Quartier quartierSelectionne;
	
	public PanneauInformations informations;
	public PanneauActions actions;
	
	public BandeauInferieur(Metropole metropole, int indexSelectionne) {
		super();
		this.metropole = metropole;
		this.quartierSelectionne = metropole.ville[indexSelectionne];
		
		informations = new PanneauInformations(metropole, quartierSelectionne);		
		actions = new PanneauActions(metropole, quartierSelectionne);

		setLayout(new GridLayout(1,2));		
		informations.setBounds(0, 0, INFORMATIONS_LARGEUR_HISTO + INFORMATIONS_LARGEUR_QUADRUPLE,
				INFORMATIONS_HAUTEUR_TEXTE + INFORMATIONS_HAUTEUR_CONTENU);
		actions.setBounds(0, 0, ACTIONS_LARGEUR, ACTIONS_HAUTEUR_CONTENU + ACTIONS_HAUTEUR_TEXTE);
		
		add(informations);
		add(actions);
	}
	
	public void actualiseInformations(){
		informations.rectangles.actualiseHistogramme(quartierSelectionne.getSains(), quartierSelectionne.getImmunises(),
				quartierSelectionne.getMalades(), quartierSelectionne.getMorts());
		informations.informationsLabel.setText("     Quartier " + quartierSelectionne.getNom());
		informations.medecinsLabel.setText(quartierSelectionne.getMedecins()+"");
		informations.militairesLabel.setText(quartierSelectionne.getMilitaires()+"");
		informations.populationLabel.setText(quartierSelectionne.getPopulationActuelle()+"");
		//troncature de tauxExode pour affichage
		double tauxExode = ((int) (quartierSelectionne.getTauxExode()*1000)) / 1000.0;
		informations.tauxExodeLabel.setText(tauxExode +"");
	}
	
	public void actualiseActions(){
		actions.envoyerMedecins.setEnabled(metropole.getMedecinsDisponibles() >= 10 && !quartierSelectionne.isQuarantaineActivee());
		actions.rappelerMedecins.setEnabled(quartierSelectionne.getMedecins() >= 10 && !quartierSelectionne.isQuarantaineActivee());
		actions.envoyerMilitaires.setEnabled(metropole.getMilitairesDisponibles() >= 10 && !quartierSelectionne.isQuarantaineActivee());
		actions.rappelerMilitaires.setEnabled(quartierSelectionne.getMilitaires() >= 10 && !quartierSelectionne.isQuarantaineActivee());
		actions.declencherQuarantaine.setVisible(!quartierSelectionne.isQuarantaineActivee());
		actions.arreterQuarantaine.setVisible(quartierSelectionne.isQuarantaineActivee());
		actions.declencherQuarantaine.setEnabled(quartierSelectionne.getMilitaires() >= 
				(int) (Quartier.nombreMilitairesDeBasePourQuarantaine * Technologie.getModificateurGlobalMilitairesPourQuarantaine()));
		//TODO implementer condition de mise en quarantaine
		//actions.declencherQuarantaine.setEnabled();
	}
	
	
	public void actualiseBandeauInferieur(Metropole metropole, Quartier quartierSelectionne) {
		this.metropole = metropole;
		this.quartierSelectionne = quartierSelectionne;
		actualiseActions();
		actualiseInformations();
	}

/*	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("images/seringue.gif");
		Cursor monCurseur = tk.createCustomCursor(img, new Point(16, 16), "seringue");
		Quartier newAtlas = new Quartier("New Atlas", 200000, 0);
		newAtlas.setImmunises(50000);
		newAtlas.setMalades(100000);
		newAtlas.setMorts(50000);
		Quartier platonTown = new Quartier("Platon Town", 100000, 0);
		Quartier[] ville = { newAtlas, platonTown };
		Metropole atlantide = new Metropole("Atlantide", ville, 20, 20);
		BandeauInferieur bdi = new BandeauInferieur(atlantide, newAtlas);
		bdi.setCursor(monCurseur);
		// bdi.setSize(new Dimension(1200, 220));
		bdi.setLocation(0, 0);
		bdi.setVisible(true);
	} */

}

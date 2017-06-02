package controleurs;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.UnsupportedLookAndFeelException;

import ihm.menu.IHMControleur;
import ihm.menu.MenuFinPartie;
import modeles.Maladie;
import modeles.Metropole;
import modeles.Quartier;
import modeles.Technologie;
import utils.Musique;
import utils.YAML.EcritureYAML;
import utils.YAML.LectureYAML;

public class Pandemia implements ActionListener, MouseListener, ItemListener, KeyListener {
	
	public final static String URL_CARTES = "fichiersYAML/modeles/CartesDisponibles.yaml";
	public final static String URL_MALADIES = "fichiersYAML/modeles/MaladiesDisponibles.yaml";
	public final static String URL_MESSAGES = "fichiersYAML/modeles/MessagesAleatoires.yaml";
	public final static String URL_SAUVEGARDES = "fichiersYAML/modeles/Sauvegardes.yaml";
	
	ThreadLogique threadLogique;
	ThreadAlertes threadAlertes;
	
	public IHMControleur ihm;
	
	public String pseudo;
	public Maladie maladie;
	public Metropole metropole;
	public String urlCarte;
	
	public Quartier quartierSelectionne;
	
	public Boolean partieTerminee = false;
	public Boolean partieGagnee = false;


	public Pandemia(){
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, String> choixCarte = (Map) LectureYAML.getConfigs(URL_CARTES);
		String urlYAML = choixCarte.get("Test");
		
		metropole = new Metropole(urlYAML);
		@SuppressWarnings("unchecked")
		Map<String, Object> configs = (Map<String, Object>) LectureYAML.getConfigs(urlYAML);
		urlCarte = LectureYAML.geturlCarte(configs);
		
	}
	
	public Metropole getMetropole(){
		return metropole;
	}
	
	public Maladie getMaladie(){
		return maladie;
	}
	
	public String getPseudo(){
		return pseudo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == ihm.getMenuPrincipal().nouvellePartie) {
			ihm.principalNouvellePartie();
		}
		if (event.getSource() == ihm.getMenuPrincipal().editeurDeMetropole) {
			ihm.principalEditeurPartie();
		}
		if (event.getSource() == ihm.getMenuPrincipal().chargerPartie) {
			ihm.principalChargerPartie();
		}
		if (event.getSource() == ihm.getMenuPrincipal().quitter) {
			partieTerminee = true;
			ihm.principalQuitter();
		}
		if (event.getSource() == ihm.getMenuChargerPartie().charger) {

			Map<String, Object> configs = (Map<String, Object>) ((Map<String, Object>) LectureYAML.getConfigs(URL_SAUVEGARDES)).get(ihm.getMenuChargerPartie().sauvegardeChoisie);
			pseudo = (String) configs.get("Pseudo");
			maladie = new Maladie((Map<String, Object>)configs.get("Maladie"));
			Technologie.initialiseTechnologies((ArrayList<Object>) configs.get("ArbreTechnologique"));
			metropole = new Metropole((Map<String, Object>) configs);
			urlCarte = (String) configs.get("urlCarte");

			ihm.initialiserPanneauDeJeu(metropole, urlCarte);
								
			initaliseThreads();
			
		}
		if (event.getSource() == ihm.getMenuChargerPartie().retour) {
			ihm.chargerPartieRetour();
		}
		if (event.getSource() == ihm.getMenuNouvellePartie().retour) {
			ihm.getMenuNouvellePartie().setVisible(false);
			ihm.getMenuPrincipal().setVisible(true);
		}
		if (event.getSource() == ihm.getMenuNouvellePartie().lancerPartie) {
			
			pseudo = ihm.getMenuNouvellePartie().nomJoueur.getText();
			
			String nomMetropole = ihm.getMenuNouvellePartie().choixMetropole.getSelectedItem();
			String nomMaladie = ihm.getMenuNouvellePartie().choixMaladie.getSelectedItem();
						
			maladie = new Maladie((Map)((Map)(LectureYAML.getConfigs(URL_MALADIES))).get(nomMaladie));
			String urlYAML = (String) ((Map) LectureYAML.getConfigs(URL_CARTES)).get(nomMetropole);
			metropole = new Metropole(urlYAML);
			urlCarte = LectureYAML.geturlCarte((Map) LectureYAML.getConfigs(urlYAML));
			
			Technologie.initialiseTechnologies((ArrayList<Object>) ((Map<String, Object>) LectureYAML.getConfigs(Technologie.URL_TECHNOLOGIES)).get("ArbreTechnologique"));
			metropole.initialiseEpidemie(maladie);

			ihm.initialiserPanneauDeJeu(metropole, urlCarte);
			
			initaliseThreads();
			
		}
		if (event.getSource() == ihm.getMenuEditeur().retour) {
			ihm.editeurRetour();
		}
		
		if (event.getSource() == ihm.getMenuEditeur().envoyer) {
			String nom = ihm.getMenuEditeur().nomTextField.getText();
			String nouvelleUrl = ihm.getMenuEditeur().urlTextField.getText();
			EcritureYAML.addData("fichiersYAML/modeles/CartesDisponibles.yaml", nom, nouvelleUrl);
			ihm.editeurEnvoyer();
		}
		if(event.getSource() == ihm.getMenuSauvegarderPartie().sauvegarder){
			String nom = ihm.getMenuSauvegarderPartie().nomSauvegarde.getText();
			Map<String, Object> sauvegarde = EcritureYAML.makeSauvegarde(pseudo, metropole, maladie);
			EcritureYAML.addData(Pandemia.URL_SAUVEGARDES, nom, sauvegarde);
			ihm.sauvegarderPartieRetour();
		}
		if (event.getSource() == ihm.getMenuSauvegarderPartie().retour){
			ihm.sauvegarderPartieRetour();
		}
		if (event.getSource() == ihm.getMenuSauvegarderPartie().quitter){
			partieTerminee = true;
			ihm.sauvegarderPartieQuitter();
		}
		if (event.getSource() == ihm.getPanneauDeJeu().bds.menu){
			ihm.menuPanneauDeJeu();
		}
		
		if (event.getSource() == ihm.getPanneauDeJeu().bds.recherche){
			ihm.recherchePanneauDeJeu();
		}

		if (event.getSource() == ihm.getPanneauDeJeu().bds.plusInformations){
			actualiseFenetreInfectes();
			ihm.plusInformationsPanneauDeJeu();
		}
		
		if (event.getSource() == ihm.getPanneauDeJeu().bdi.actions.envoyerMedecins) {
			metropole.depecherMedecins(metropole.ville[ihm.getPanneauDeJeu().indexSelectionne], 10);

		}
		if (event.getSource() == ihm.getPanneauDeJeu().bdi.actions.rappelerMedecins) {
			metropole.rappelerMedecins(metropole.ville[ihm.getPanneauDeJeu().indexSelectionne], 10);
		}
		
		if (event.getSource() == ihm.getPanneauDeJeu().bdi.actions.envoyerMilitaires) {
			metropole.depecherMilitaires(metropole.ville[ihm.getPanneauDeJeu().indexSelectionne], 10);
		}
		
		if (event.getSource() == ihm.getPanneauDeJeu().bdi.actions.rappelerMilitaires) {
			metropole.rappelerMilitaires(metropole.ville[ihm.getPanneauDeJeu().indexSelectionne], 10);
		}
		
		//les conséquences de la quarantaine sont gérées par la méthode actualiserQuarantaine() qui vient juste après
		
		if (event.getSource() == ihm.getPanneauDeJeu().bdi.actions.declencherQuarantaine) {
			quartierSelectionne.setQuarantaineActivee(true);
		}
		
		if (event.getSource() == ihm.getPanneauDeJeu().bdi.actions.arreterQuarantaine) {
			quartierSelectionne.setQuarantaineActivee(false);
		}
		
		//quelle que soit l'action, on actualise le panneau de jeu après toutes les autres opérations		
		ihm.getPanneauDeJeu().actualisePanneauDeJeu();
		//et on rend le focus à la carte (pour les raccourcis claviers)
		ihm.getPanneauDeJeu().carte.requestFocus();

	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if(event.getSource() == ihm.getMenuChargerPartie().listeSauvegardes){
			ihm.getMenuChargerPartie().sauvegardeChoisie = ihm.getMenuChargerPartie().listeSauvegardes.getSelectedItem();
		}
		if(event.getSource() == ihm.getFenetreInfectes().liste){
			ihm.getPanneauDeJeu().indexSelectionne = metropole.getIndiceByQuartier(metropole.getQuartierByNom(ihm.getFenetreInfectes().liste.getSelectedItem()));
			ihm.getPanneauDeJeu().actualisePanneauDeJeu();
		}
	}
		
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getSource() == ihm.getPanneauDeJeu().carte){
			int hauteurQuartier = ihm.getPanneauDeJeu().carte.getHeight() / 5;  // 100
			int largeurQuartier = ihm.getPanneauDeJeu().carte.getWidth() / 5;   // 160
			int indexQuartier = (event.getX() / largeurQuartier) + 5 * (event.getY() / hauteurQuartier);			
			ihm.getPanneauDeJeu().indexSelectionne = indexQuartier;
			ihm.getPanneauDeJeu().actualisePanneauDeJeu();
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {		
	}

	public void initaliseThreads() {
		threadLogique = new ThreadLogique(this);
		threadLogique.start();
		
		threadAlertes = new ThreadAlertes(this);
		threadAlertes.start();
	}

	public void actualiseFenetreInfectes() {
		ArrayList<String> listeQuartiersInfectes = new ArrayList<>();
		for(int i = 0; i < metropole.ville.length; i++){
			if(metropole.ville[i].getMalades() > 0){
				listeQuartiersInfectes.add(metropole.ville[i].getNom());
			}
		}
		ihm.getFenetreInfectes().actualiseFenetresInfectes(listeQuartiersInfectes);
		ihm.getFenetreInfectes().liste.addItemListener(this);
	}
	
	
	public void actualisePartieTerminee(){
		if(metropole.getMaladesTotal() == 0 || metropole.getPopulationActuelleTotal() < metropole.getPopulationInitialeTotal() * Metropole.pourcentageDefaite){
			partieTerminee = true;
			if(metropole.getMaladesTotal() == 0){
				partieGagnee = true;
			} if(metropole.getPopulationActuelleTotal() == 0){
				partieGagnee = false;
			}
			actualiseAlertes();
			MenuFinPartie stats = new MenuFinPartie(this);
			stats.setVisible(true);
		}
	}
	
	//actualise messages d'alertes
	public void actualiseAlertes(){
		
		if(partieTerminee){
			ihm.getPanneauDeJeu().bda.addAlerte("Le virus a été éradiqué de toute la métropole !");
		}
		
		for(int i = 0; i < metropole.ville.length; i++){

			Quartier quartier = metropole.ville[i];
			int populationQuartier = quartier.getSains() + quartier.getImmunises() + quartier.getMalades();
			String alerteMaladie = "Le quartier " + quartier.getNom() + " a été infecté par un virus !";
			if(quartier.getMalades() > 0){
				if(!ihm.getPanneauDeJeu().bda.isInAlertes(alerteMaladie)){
					ihm.getPanneauDeJeu().bda.addAlerte(alerteMaladie);
				}
			} else {
				if(ihm.getPanneauDeJeu().bda.isInAlertes(alerteMaladie)){
					String finMaladie = "Le quartier " + quartier.getNom() + " a été délivré de l'infection !";
					if(!ihm.getPanneauDeJeu().bda.isInAlertes(finMaladie)){
						ihm.getPanneauDeJeu().bda.addAlerte(finMaladie);
					}
				}
			}
			if(populationQuartier < quartier.getPopulationInitiale()/2){
				String alertePopulation = "50 % de la population de " + quartier.getNom() + " a été décimée !";
				if(!ihm.getPanneauDeJeu().bda.isInAlertes(alertePopulation)){
					ihm.getPanneauDeJeu().bda.addAlerte(alertePopulation);
				}
			}
		
		}
		
	}
	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()){
		case KeyEvent.VK_A:
			actualiseFenetreInfectes();
			ihm.getFenetreInfectes().setVisible(!ihm.getFenetreInfectes().isVisible());
			break;
		case KeyEvent.VK_T:
			ihm.getFenetreArbreTechnologie().setVisible(!ihm.getFenetreArbreTechnologie().isVisible());
			break;
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
		

	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	IllegalAccessException, UnsupportedLookAndFeelException, IOException {

		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("images/seringue.gif");
		Cursor curseurSeringue = toolkit.createCustomCursor(image, new Point(0, 0), "seringue");
		
		Pandemia pandemia = new Pandemia();
		pandemia.ihm = new IHMControleur(pandemia);
		pandemia.ihm.setSize(IHMControleur.LARGEUR, IHMControleur.HAUTEUR);
		pandemia.ihm.setCursor(curseurSeringue);
		pandemia.ihm.setResizable(false);
		pandemia.ihm.setVisible(true);	

		Musique musique = new Musique(pandemia);
		musique.start();

	}

}

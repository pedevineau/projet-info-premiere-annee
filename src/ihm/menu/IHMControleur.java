package ihm.menu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import controleurs.Pandemia;
import ihm.panneau.FenetreArbreTechnologique;
import ihm.panneau.FenetreInfectes;
import ihm.panneau.PanneauDeJeu;
import modeles.Metropole;
import utils.PandemiaColors;

public class IHMControleur extends JFrame implements ActionListener, WindowListener, MouseListener, KeyListener, ItemListener {
	
	private static final long serialVersionUID = 1L;
	
	public final static int LARGEUR = 1000;
	public final static int HAUTEUR = 680;
	
	Pandemia pandemia;
	
	MenuPrincipal menuPrincipal;
	MenuNouvellePartie menuPartie;
	MenuChargerPartie menuChargerPartie;
	MenuSauvegarderPartie menuSauvegarderPartie;
	MenuEditeur menuEditeur;
	PanneauDeJeu panneauDeJeu;
	FenetreArbreTechnologique tech;
	FenetreInfectes fenetreInfectes;
	
	
	public IHMControleur(Pandemia pandemia) throws IOException {

		super("PANDEMIA");
		
		this.pandemia = pandemia;

		UIManager.put("Button.foreground", PandemiaColors.couleurFondPanneauMenu);
		UIManager.put("Button.border", BorderFactory.createLineBorder(PandemiaColors.couleurFondPanneauMenu));
		UIManager.put("Button.font", PandemiaColors.policeBoutons);
		UIManager.put("Button.background", PandemiaColors.couleurFondBouttonMenu);
		UIManager.put("Button.disabled", new ColorUIResource(PandemiaColors.couleurFondBouttonMenu));
		UIManager.put("Panel.background", PandemiaColors.couleurFondPanneauMenu);
		UIManager.put("Label.foreground", PandemiaColors.couleurTexteMenu);
		UIManager.put("Label.font", PandemiaColors.policeLabel);	
		UIManager.put("List.background", PandemiaColors.couleurTexteMenu);
		UIManager.put("List.foreground", PandemiaColors.couleurTextePanneauActions);	

		addWindowListener(this);
		
		menuPrincipal = new MenuPrincipal();
		menuPrincipal.setVisible(true);
		menuPrincipal.setSize(LARGEUR, HAUTEUR);
		menuPrincipal.nouvellePartie.addActionListener(pandemia);
		menuPrincipal.chargerPartie.addActionListener(pandemia);
		menuPrincipal.editeurDeMetropole.addActionListener(pandemia);
		menuPrincipal.quitter.addActionListener(pandemia);
		add(menuPrincipal);

		menuEditeur = new MenuEditeur();
		menuEditeur.setVisible(false);
		menuEditeur.setSize(LARGEUR, HAUTEUR);
		menuEditeur.envoyer.addActionListener(pandemia);
		menuEditeur.retour.addActionListener(pandemia);
		add(menuEditeur);

		menuPartie = new MenuNouvellePartie();
		menuPartie.setVisible(false);
		menuPartie.setSize(LARGEUR, HAUTEUR);
		menuPartie.lancerPartie.addActionListener(pandemia);
		menuPartie.retour.addActionListener(pandemia);
		add(menuPartie);
		
		menuSauvegarderPartie = new MenuSauvegarderPartie();

		
		menuChargerPartie = new MenuChargerPartie();
		menuChargerPartie.setVisible(false);
		menuChargerPartie.charger.addActionListener(pandemia);
		menuChargerPartie.retour.addActionListener(pandemia);
		menuChargerPartie.listeSauvegardes.addItemListener(pandemia);
		add(menuChargerPartie);
		
		panneauDeJeu = new PanneauDeJeu(pandemia.metropole, 0, pandemia.urlCarte);


		//on initialise ici une ArrayList !!!

		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteurEcran = (int) dimension.getHeight();
		int largeurEcran = (int) dimension.getWidth();
		setLocation((largeurEcran - LARGEUR) / 2, (hauteurEcran - HAUTEUR) / 2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}
	
	public MenuNouvellePartie getMenuNouvellePartie() {
		return menuPartie;
	}
	
	public MenuEditeur getMenuEditeur() {
		return menuEditeur;
	}	
	
	public MenuChargerPartie getMenuChargerPartie() {
		return menuChargerPartie;
	}
	
	public MenuSauvegarderPartie getMenuSauvegarderPartie() {
		return menuSauvegarderPartie;
	}
	
	public PanneauDeJeu getPanneauDeJeu(){
		return panneauDeJeu;
	}
	
	public FenetreInfectes getFenetreInfectes(){
		return fenetreInfectes;
	}
	
	public FenetreArbreTechnologique getFenetreArbreTechnologie(){
		return tech;
	}
	
	public void chargerPartieRetour(){
		menuChargerPartie.setVisible(false);
		menuPrincipal.setVisible(true);
	}
	
	
	
	public void principalNouvellePartie(){
		menuPrincipal.setVisible(false);
		menuPartie.setVisible(true);
	}
	
	public void principalEditeurPartie(){
		menuPrincipal.setVisible(false);
		menuEditeur.setVisible(true);
	}
	
	public void principalChargerPartie(){
		menuChargerPartie.setVisible(true);
		menuPrincipal.setVisible(false);
	}
	
	public void principalQuitter(){
		dispose();
	}
	
	public void editeurRetour(){
		menuEditeur.setVisible(false);
		menuPrincipal.setVisible(true);
	}
	
	public void editeurEnvoyer(){
		menuEditeur.setVisible(false);
		menuEditeur.setVisible(true);
	}

	public void sauvegarderPartieRetour(){
		menuSauvegarderPartie.dispose();
	}
	
	public void sauvegarderPartieQuitter(){
		menuSauvegarderPartie.dispose();
		dispose();
	}
	
	public void menuPanneauDeJeu(){
		menuSauvegarderPartie = new MenuSauvegarderPartie();
		menuSauvegarderPartie.setVisible(true);
		menuSauvegarderPartie.sauvegarder.addActionListener(pandemia);
		menuSauvegarderPartie.retour.addActionListener(pandemia);
		menuSauvegarderPartie.quitter.addActionListener(pandemia);
	}
	
	public void recherchePanneauDeJeu(){
		tech.setVisible(true);
	}
	
	public void plusInformationsPanneauDeJeu(){
		fenetreInfectes.setVisible(true);
	}
	

	public void initialiserPanneauDeJeu(Metropole metropole, String urlCarte){
		menuChargerPartie.setVisible(false);
		
		fenetreInfectes = new FenetreInfectes(new ArrayList<>());
		tech = new FenetreArbreTechnologique();
		
		menuPartie.setVisible(false);
		menuChargerPartie.setVisible(false);
		
		
		panneauDeJeu = new PanneauDeJeu(metropole, 0, urlCarte);
		panneauDeJeu.bds.menu.addActionListener(pandemia);
		panneauDeJeu.bds.recherche.addActionListener(pandemia);
		panneauDeJeu.bds.plusInformations.addActionListener(pandemia);
		panneauDeJeu.bdi.actions.envoyerMedecins.addActionListener(pandemia);
		panneauDeJeu.bdi.actions.rappelerMedecins.addActionListener(pandemia);
		panneauDeJeu.bdi.actions.envoyerMilitaires.addActionListener(pandemia);
		panneauDeJeu.bdi.actions.rappelerMilitaires.addActionListener(pandemia);
		panneauDeJeu.bdi.actions.declencherQuarantaine.addActionListener(pandemia);
		panneauDeJeu.bdi.actions.arreterQuarantaine.addActionListener(pandemia);
		panneauDeJeu.carte.addMouseListener(pandemia);
		fenetreInfectes.liste.addKeyListener(pandemia);
		panneauDeJeu.bds.plusInformations.addActionListener(pandemia);
		tech.addKeyListener(pandemia);
		panneauDeJeu.carte.addKeyListener(pandemia);
		add(panneauDeJeu);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		panneauDeJeu.carte.requestFocus();
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

	@Override
	public void itemStateChanged(ItemEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	
}

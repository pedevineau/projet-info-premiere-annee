package ihm.menu;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controleurs.Pandemia;
import utils.YAML.LectureYAML;

public class MenuNouvellePartie extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;
	
	
	
	public JPanel panelPseudo;
	public JLabel labelPseudo;
	public JTextField nomJoueur;
	
	public JPanel panelMetropole;
	public JLabel labelMetropole;
	public Choice choixMetropole;
	public String nomMetropole;
	
	public JPanel panelMaladie;
	public JLabel labelMaladie;
	public Choice choixMaladie;
	public String maladie;
	
	public JButton lancerPartie;
	public JButton retour;
	
	public MenuNouvellePartie() {
		super();
		
		panelPseudo = new JPanel();
		labelPseudo = new JLabel("Pseudo");
		nomJoueur = new JTextField("Entrez votre pseudo ici...");
		
		panelMetropole = new JPanel();
		labelMetropole = new JLabel("Metropole ");
		choixMetropole = new Choice();
		
		panelMaladie = new JPanel();
		labelMaladie = new JLabel("Maladie ");
		choixMaladie = new Choice();
		
		lancerPartie = new JButton("Commencez !");
		retour = new JButton("Retour");
		
		lancerPartie.requestFocus();

		Border bordureVideAvecMarge = BorderFactory.createEmptyBorder(10, 15, 10, 15);
		nomJoueur.setBorder(bordureVideAvecMarge);
		
		setLayout(new GridLayout(5,1));
		
		panelPseudo.add(labelPseudo);
		panelPseudo.add(nomJoueur);
		panelMetropole.add(labelMetropole);
		panelMetropole.add(choixMetropole);
		
		choixMetropole();

		panelMaladie.add(labelMaladie);
		panelMaladie.add(choixMaladie);

		choixMaladie();
		
		add(panelPseudo);
		add(panelMetropole);
		add(panelMaladie);
		add(lancerPartie);
		add(retour);
		
		Dimension d = new Dimension(200,100);
		lancerPartie.setPreferredSize(d);
		retour.setPreferredSize(d);
		choixMetropole.addItemListener(this);
		choixMaladie.addItemListener(this);
		
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	public void choixMetropole(){
		LinkedList<String> listeNoms = LectureYAML.getListeNoms((Map<String, String>) LectureYAML.getConfigs(Pandemia.URL_CARTES));
		for (int i = 0; i < listeNoms.size(); i++){
			choixMetropole.add(listeNoms.get(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void choixMaladie(){
		LinkedList<String> listeNoms = LectureYAML.getListeNoms((Map<String, String>) LectureYAML.getConfigs(Pandemia.URL_MALADIES));
		for (int i = 0; i < listeNoms.size(); i++){
			choixMaladie.add(listeNoms.get(i));
		}
	}
	
/*	public static void colorier(Color couleur, MenuNouvellePartie menuPartie){
		menuPartie.panelPseudo.setBackground(couleur);
		menuPartie.panelMetropole.setBackground(couleur);
		menuPartie.panelMaladie.setBackground(couleur);
	}
*/

	@Override
	public void itemStateChanged(ItemEvent event) {
/*		Color couleur = new Color(0, 127, 255);
		if (event.getSource() == choixMetropole){
			if (choixMetropole.getSelectedItem() == "Babylone")
				couleur = new Color(224,205,169);
			if (choixMetropole.getSelectedItem() == "ElDorado")
				couleur = new Color(255,215,0);
			colorier(couleur, this);
		}
*/	
	}

}

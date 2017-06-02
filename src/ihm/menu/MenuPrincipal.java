package ihm.menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import ihm.panneau.PanneauImage;

public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	public JPanel panneauMenu = new JPanel(new GridLayout(4,1));
	public PanneauImage  panneauGauche;
	public PanneauImage panneauDroit;
	public JButton nouvellePartie;
	public JButton chargerPartie;
	public JButton editeurDeMetropole;
	public JButton quitter;

	public MenuPrincipal() throws IOException {
	//	super(new File("images/plague.png"));
		setLayout(new GridLayout(1,3));
		
		nouvellePartie = new JButton("Nouvelle Partie");
		chargerPartie = new JButton("Charger Partie");
		editeurDeMetropole = new JButton("Editeur de monde");
		quitter = new JButton("Quitter");
		
		// Pour supprimer la bordure des boutons :
		// Border emptyBorder = BorderFactory.createEmptyBorder();
		// nouvellePartie.setBorder(emptyBorder);
		// chargerPartie.setBorder(emptyBorder);
		// editeurDeMetropole.setBorder(emptyBorder);
		// quitter.setBorder(emptyBorder);
		
		nouvellePartie.requestFocus();

		nouvellePartie.setLocation(50, 60);
		nouvellePartie.setPreferredSize(new Dimension(200, 100));
		panneauMenu.add(nouvellePartie);

		chargerPartie.setLocation(50, 200);
		chargerPartie.setPreferredSize(new Dimension(200, 100));
		panneauMenu.add(chargerPartie);

		editeurDeMetropole.setLocation(50, 340);
		editeurDeMetropole.setPreferredSize(new Dimension(200, 100));
		panneauMenu.add(editeurDeMetropole);

		quitter.setLocation(50, 340);
		quitter.setPreferredSize(new Dimension(200, 100));
		panneauMenu.add(quitter);

		try {
			panneauGauche = new PanneauImage(new File("images/plagueGauche.png"));
			panneauDroit = new PanneauImage(new File("images/plagueDroit.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(panneauGauche);
		add(panneauMenu);
		add(panneauDroit);

	}
}

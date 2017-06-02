package ihm.menu;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controleurs.Pandemia;
import ihm.panneau.Histogramme;

public class MenuFinPartie extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	static final int HAUTEUR = 500;
	static final int LARGEUR = 600;
	
	JLabel label = new JLabel();
	Histogramme histogramme;
	JButton retour = new JButton("Retour");
	
	public MenuFinPartie(Pandemia pandemia){
		super("Statistiques");
		
		int sains = pandemia.metropole.getSainsTotal();
		int immunises = pandemia.metropole.getImmunisesTotal();
		int malades = pandemia.metropole.getMaladesTotal();
		int morts = pandemia.metropole.getMortsTotal();
		
		setLayout(new GridLayout(3,1));
		
		if(pandemia.partieGagnee){
			label.setText("Vous avez vaincu l'épidémie ! La Victoire est totale !");
		} else {
			label.setText("L'humanité est traumatisée ! Le désastre est complet !");
		}
		label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(label);

		histogramme = new Histogramme(sains, immunises, malades, morts);
		histogramme.setSize(200, 200);
		histogramme.setAlignmentY(CENTER_ALIGNMENT);
		add(histogramme);

		retour.addActionListener(this);
		add(retour);
		
		
		setForeground(utils.PandemiaColors.couleurTexteMenu);
		setBackground(utils.PandemiaColors.couleurFondPanneauMenu);
		setSize(LARGEUR, HAUTEUR);
		setResizable(false);
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteurEcran = (int) dimension.getHeight();
		int largeurEcran = (int) dimension.getWidth();
		setLocation((largeurEcran - LARGEUR) / 2, (hauteurEcran - HAUTEUR) / 2);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == retour){
			setVisible(false);
		}
	}
}

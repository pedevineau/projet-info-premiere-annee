package ihm.panneau;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.PandemiaColors;

public class Histogramme extends JPanel {
	
	private static final long serialVersionUID = 1L;

	final static int HAUTEUR_COLONNE = 80;
	
	int sains, immunises, malades, morts;

	public Histogramme(int sains, int immunises, int malades, int morts) {
		this.sains = sains;
		this.immunises = immunises;
		this.malades = malades;
		this.morts = morts;
	}

	public void paint(Graphics gr) {
				
		int total = sains + immunises + malades + morts;
		double tauxSains = sains/(float) total;
		double tauxImmunises = immunises/(float) total;
		double tauxMalades = malades/(float) total;
		double tauxMorts = morts/(float) total;
		
		// fond
		gr.setColor(PandemiaColors.couleurFondPanneauHistogramme);
		gr.fillRect(0, 0, getWidth(), getHeight());
		
		// rectangle representant la population saine
		gr.setColor(PandemiaColors.couleurSains);
		gr.fillRect(100, (int) (getHeight() * (1-tauxSains)), HAUTEUR_COLONNE/4, (int) (getHeight()*tauxSains));
		
		// population immunisee
		gr.setColor(PandemiaColors.couleurImmunises);
		gr.fillRect(100+HAUTEUR_COLONNE/4, (int) (getHeight() * (1-tauxImmunises)), HAUTEUR_COLONNE/4, (int) (getHeight()*tauxImmunises));
		
		// population malade
		gr.setColor(PandemiaColors.couleurMalades);
		gr.fillRect(100+2*HAUTEUR_COLONNE/4, (int) (getHeight() * (1-tauxMalades)), HAUTEUR_COLONNE/4, (int) (getHeight()*tauxMalades));
		
		// population decedee
		gr.setColor(PandemiaColors.couleurMorts);
		gr.fillRect(100+3*HAUTEUR_COLONNE/4, (int) (getHeight() * (1-tauxMorts)), HAUTEUR_COLONNE/4, (int) (getHeight()*tauxMorts));
		
		// fond
		
	}
	
	public void actualiseHistogramme(int sains, int immunises, int malades, int morts){
		this.sains = sains;
		this.malades = malades;
		this.immunises = immunises;
		this.morts = morts;
		repaint();
	}

	public static void main(String[] args) {
		JFrame fenetre = new JFrame("testCanvas");
		Histogramme rect = new Histogramme(125, 20, 75, 45);
		fenetre.add(rect);
		fenetre.setSize(100, 100);
		fenetre.setVisible(true);
	}

}

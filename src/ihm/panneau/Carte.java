package ihm.panneau;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import modeles.Quartier;

public class Carte extends PanneauImage implements KeyListener, MouseListener {

	static public PanneauImage cercles;
	public float opaciteSelection = 0.65f;
	public float opaciteNonSelectionne = 0.15f;
	PanneauDeJeu panneauDeJeu;
	Quartier quartierSelectionne;
	boolean estInitialise = false;
	
	private static final long serialVersionUID = 1L;

	public Carte(File file, Dimension dimension, PanneauDeJeu panneauDeJeu) throws IOException {
		super(file, dimension);
		this.panneauDeJeu = panneauDeJeu;
		addKeyListener(this);
	}

	public void paint(Graphics g) {
		if(!estInitialise){
			requestFocus();
			estInitialise = true;
		}
		int indexSelectionne = panneauDeJeu.getIndexSelectionne();
		quartierSelectionne = panneauDeJeu.metropole.ville[indexSelectionne];
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Arial", Font.PLAIN, 16);
		FontMetrics fm = g2d.getFontMetrics();
		g2d.setFont(font);
		// Carte
		g2d.drawImage(this.image, 0, 0, null);
		// Rectangle de fond du quartier sélectionné
		g2d.setColor(getCouleurQuartier(quartierSelectionne));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaciteSelection));
		g2d.fillRect((indexSelectionne % 5) * 160, (indexSelectionne / 5) * 100, 160, 100);
		// Nom quartier sélectionné
		if (quartierSelectionne != null) {
			String nomDuQuartier = quartierSelectionne.getNom();
			int x = 80 + (indexSelectionne % 5) * 160 - fm.stringWidth(nomDuQuartier) / 2;
			int y = 50 + (indexSelectionne / 5) * 100 - fm.getHeight() / 2 + fm.getAscent();
			g2d.setColor(Color.WHITE);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			RenderingHints renderingHintsAntiAliasingOn = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setRenderingHints(renderingHintsAntiAliasingOn);
			g2d.drawString(nomDuQuartier, x, y);
		}
		for (int index = 0; index < panneauDeJeu.metropole.ville.length; index++) {
			if (index != indexSelectionne) {
				g2d.setColor(getCouleurQuartier(panneauDeJeu.metropole.ville[index]));
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaciteNonSelectionne));
				g2d.fillRect((index % 5) * 160, (index / 5) * 100, 160, 100);
			}
		}
	}

	public Color getCouleurQuartier(Quartier quartier) {
		if (quartier.getMalades() > quartier.getPopulationActuelle() * .20) {
			return Color.RED;
		}
		if (quartier.getMalades() > 60) {
			return Color.ORANGE;
		}
		return Color.GREEN;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int indexSelectionne = panneauDeJeu.getIndexSelectionne();
		switch (event.getKeyCode()) {
			case 37: // fleche gauche
				if (indexSelectionne % 5 != 0) {
					indexSelectionne -= 1;
				}
				break;
			case 38: // fleche haut
				if (indexSelectionne > 4) {
					indexSelectionne -= 5;
				}
				break;
			case 39: // fleche droite
				if (indexSelectionne % 5 != 4) {
					indexSelectionne += 1;
				}
				break;
			case 40: // fleche bas
				if (indexSelectionne < 20) {
					indexSelectionne += 5;
				}
				break;
			default:
				break;
		}
		panneauDeJeu.setIndexSelectionne(indexSelectionne);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setFocusable(true);
	//	requestFocus = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
	//	requestFocus = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}


}

package ihm.panneau;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import utils.DateUtils;
import utils.PandemiaColors;

public class BandeauAlertes extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> alertes = new ArrayList<String>();
	private static int MARGE_GAUCHE = 10; // marge à gauche et à droite
	private static int MARGE_HAUT = 20;   // marge entre la bordure et la ligne de base de la 1ere alerte
	private static int ECART_TEXTE = 15;  // écart entre deux alertes successives
	
	public BandeauAlertes(){
	}
	
	public void addAlerte(String alerte) {
		// La string est ajoutée au début de l' ArrayList ( à l'index 0)
		// On ajoute l'heure actuelle au début de la string
		this.alertes.add(0, "[" + DateUtils.getHHmm() + "] " + alerte);
	}
	
	public boolean isInAlertes(String alerte){
		for(String x:alertes){
			//les 8 premiers caractères d'une alerte correspondent à l'heure (cf ci-dessus)
			//pour la comparaison des String, utiliser un equals (et pas de ==) !
			if(x.substring(8).equals(alerte)){
				return true;
			}
		}
		return false;
	}
	
	// dessine un texte sur plusieurs lignes si nécessaire et
	// renvoie la hauteur du texte ainsi dessiné
	// (adapté de http://stackoverflow.com/a/19864657)
	private static int dessinerStringMultiligne(Graphics2D g, String text, int lineWidth, int x, int y) {
		RenderingHints renderingHintsAntiAliasingOn = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.setRenderingHints(renderingHintsAntiAliasingOn);
		int totalHeight = 0;
	    FontMetrics fontMetrics = g.getFontMetrics();
	    if(fontMetrics.stringWidth(text) < lineWidth) {
	        g.drawString(text, x, y);
	        totalHeight += fontMetrics.getHeight();
	    } else {
	        String[] words = text.split(" ");
	        String currentLine = words[0];
	        for(int i = 1; i < words.length; i++) {
	            if(fontMetrics.stringWidth(currentLine+words[i]) < lineWidth) {
	                currentLine += " "+words[i];
	            } else {
	                g.drawString(currentLine, x, y);
	                y += fontMetrics.getHeight();
	                currentLine = words[i];
	                totalHeight += fontMetrics.getHeight();
	            }
	        }
	        if(currentLine.trim().length() > 0) {
	            g.drawString(currentLine, x, y);
	            totalHeight += fontMetrics.getHeight();
	        }
	    }
	    return totalHeight;
	}
	
	public void paintComponent(Graphics graphics){
		Graphics2D g2 = (Graphics2D) graphics;
		
		// fond
		g2.setColor(PandemiaColors.couleurFondBandeauAlertes);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// textes d'alerte
		g2.setColor(PandemiaColors.couleurTexteBandeauAlertes);
		int ecartTexteTotal = MARGE_HAUT;  // ordonnée du dernier texte dessiné
		
		int index = 0;
		for ( String alerte : alertes) {
			// Dessiner uniquement les texte qui s'affichent à l'écran
			if (ecartTexteTotal < getHeight()){
				ecartTexteTotal += dessinerStringMultiligne(g2, alerte,
						getWidth() - 2 * MARGE_GAUCHE, MARGE_GAUCHE,
						ecartTexteTotal + ECART_TEXTE * index);				
			}
			index++;
		}

		// Dégradé au-dessus du tout
		Color couleurFondTransparent = new Color(PandemiaColors.couleurFondBandeauAlertes.getRed(),
				PandemiaColors.couleurFondBandeauAlertes.getGreen(), PandemiaColors.couleurFondBandeauAlertes.getBlue(), 0);
		GradientPaint gradientPaint = new GradientPaint(
				0, 0, couleurFondTransparent,
				0, getHeight(), PandemiaColors.couleurFondBandeauAlertes);
		g2.setPaint(gradientPaint);
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

	public ArrayList<String> getAlertes() {
		return alertes;
	}
	
}

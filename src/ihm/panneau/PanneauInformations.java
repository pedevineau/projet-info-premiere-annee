package ihm.panneau;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modeles.Metropole;
import modeles.Quartier;
import utils.PandemiaColors;

public class PanneauInformations extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel rectanglesPanel, quadruplePanel;
	Histogramme rectangles;
	JPanel populationPanel, tauxExodePanel, medecinsPanel, militairesPanel, informationsPanel = new JPanel();
	JLabel populationLabel, tauxExodeLabel, medecinsLabel, militairesLabel, informationsLabel;
	PanneauImage populationIcone;
	PanneauImage tauxExodeIcone;
	PanneauImage medecinsIcone;
	PanneauImage militairesIcone;

	public PanneauInformations(Metropole metropole, Quartier quartierSelectionne) {

		try {
			populationIcone = new PanneauImage(new File("images/seringue.gif"));
			tauxExodeIcone = new PanneauImage(new File("images/seringue.gif"));
			medecinsIcone = new PanneauImage(new File("images/seringue.gif"));
			militairesIcone = new PanneauImage(new File("images/seringue.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension dInfos = new Dimension(93, 60);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		informationsLabel = new JLabel("Quartier : " + quartierSelectionne.getNom());
		JPanel panelInformations = new JPanel();

		informationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informationsLabel.setVerticalAlignment(SwingConstants.CENTER);

		setBackground(PandemiaColors.couleurFondTitrePanneauInformations);
		setForeground(PandemiaColors.couleurTexteTitrePanneauInformations);
		informationsLabel.setBackground(PandemiaColors.couleurFondTitrePanneauInformations);
		informationsLabel.setForeground(PandemiaColors.couleurTexteTitrePanneauInformations);
		panelInformations.setBackground(PandemiaColors.couleurFondTitrePanneauInformations);
		panelInformations.setForeground(PandemiaColors.couleurTexteTitrePanneauInformations);

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 10, 0);

		add(informationsLabel, c);

		rectangles = new Histogramme(quartierSelectionne.getSains(), quartierSelectionne.getImmunises(),
				quartierSelectionne.getMalades(), quartierSelectionne.getMorts());
		// rectangles.setBounds(0, INFORMATIONS_HAUTEUR_TEXTE,
		// INFORMATIONS_LARGEUR_HISTO + INFORMATIONS_LARGEUR_QUADRUPLE,
		// INFORMATIONS_HAUTEUR_CONTENU);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		add(rectangles, c);

		populationPanel = new JPanel(new GridLayout(1, 2));
		populationLabel = new JLabel("" + quartierSelectionne.getPopulationActuelle());
		populationLabel.setForeground(PandemiaColors.couleurTextePanneauActions);
		populationPanel.add(populationIcone);
		populationPanel.add(populationLabel);
		populationPanel.setSize(dInfos);
		populationPanel.setBackground(PandemiaColors.couleurFondPanneauQuadruple);

		tauxExodePanel = new JPanel(new GridLayout(1, 2));
		tauxExodeLabel = new JLabel("" + quartierSelectionne.getTauxExode());
		tauxExodeLabel.setForeground(PandemiaColors.couleurTextePanneauActions);
		tauxExodePanel.add(tauxExodeIcone);
		tauxExodePanel.add(tauxExodeLabel);
		tauxExodePanel.setSize(dInfos);
		tauxExodePanel.setBackground(PandemiaColors.couleurFondPanneauQuadruple);

		medecinsPanel = new JPanel(new GridLayout(1, 2));
		medecinsLabel = new JLabel("" + quartierSelectionne.getMedecins());
		medecinsLabel.setForeground(PandemiaColors.couleurTextePanneauActions);
		medecinsPanel.add(medecinsIcone);
		medecinsPanel.add(medecinsLabel);
		medecinsPanel.setSize(dInfos);
		medecinsPanel.setBackground(PandemiaColors.couleurFondPanneauQuadruple);

		militairesPanel = new JPanel(new GridLayout(1, 2));
		militairesLabel = new JLabel("" + quartierSelectionne.getMilitaires());
		militairesLabel.setForeground(PandemiaColors.couleurTextePanneauActions);
		militairesPanel.add(militairesIcone);
		militairesPanel.add(militairesLabel);
		militairesPanel.setSize(dInfos);
		militairesPanel.setBackground(PandemiaColors.couleurFondPanneauQuadruple);

		quadruplePanel = new JPanel(new GridLayout(2, 2));
		quadruplePanel.add(populationPanel);
		quadruplePanel.add(tauxExodePanel);
		quadruplePanel.add(medecinsPanel);
		quadruplePanel.add(militairesPanel);

		// quadruplePanel.setBounds(INFORMATIONS_LARGEUR_HISTO,
		// INFORMATIONS_HAUTEUR_TEXTE,
		// INFORMATIONS_LARGEUR_QUADRUPLE, INFORMATIONS_HAUTEUR_CONTENU);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;

		add(quadruplePanel, c);

		// add(informationsLabel);
		// add(informationsPanel);

	}
}

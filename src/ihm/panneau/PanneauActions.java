package ihm.panneau;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modeles.Metropole;
import modeles.Quartier;
import modeles.Technologie;
import utils.PandemiaColors;

public class PanneauActions extends JPanel {

	private static final long serialVersionUID = 1L;

	ImageIcon ambulanceVerte = new ImageIcon("images/ambulance verte.jpg");
	ImageIcon ambulanceRouge = new ImageIcon("images/ambulance rouge.jpg");
	ImageIcon charVert = new ImageIcon("images/ambulance verte.jpg");
	ImageIcon charRouge = new ImageIcon("images/ambulance rouge.jpg");
	ImageIcon debutQuarantaine = new ImageIcon("images/ambulance verte.jpg");
	ImageIcon finQuarantaine = new ImageIcon("images/ambulance rouge.jpg");

	public JLabel labelActions;
	public JPanel panelActions;
	public JButton envoyerMedecins;
	public JButton rappelerMedecins;
	public JButton envoyerMilitaires;
	public JButton rappelerMilitaires;
	public JButton declencherQuarantaine;
	public JButton arreterQuarantaine;

	public PanneauActions(Metropole metropole, Quartier quartierSelectionne) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		labelActions = new JLabel("Actions");
		panelActions = new JPanel();

		labelActions.setHorizontalAlignment(SwingConstants.CENTER);
		labelActions.setVerticalAlignment(SwingConstants.CENTER);

		labelActions.setBackground(PandemiaColors.couleurFondTitrePanneauActions);
		labelActions.setForeground(PandemiaColors.couleurTexteTitrePanneauActions);

		Dimension dActions = new Dimension(120, 120);
		
		envoyerMedecins = new JButton(ambulanceVerte);
		envoyerMedecins.setBackground(PandemiaColors.couleurFondBouttonActions);
		envoyerMedecins.setSize(dActions);
		envoyerMedecins.setLocation(200, 10);
		envoyerMedecins.setEnabled(metropole.getMedecinsDisponibles() >= 10);
		
		rappelerMedecins = new JButton(ambulanceRouge);
		rappelerMedecins.setBackground(PandemiaColors.couleurFondBouttonActions);
		rappelerMedecins.setSize(dActions);
		rappelerMedecins.setLocation(300, 10);
		rappelerMedecins.setEnabled(quartierSelectionne.getMedecins() >= 10);
		
		envoyerMilitaires = new JButton(charVert);
		envoyerMilitaires.setBackground(PandemiaColors.couleurFondBouttonActions);
		envoyerMilitaires.setSize(dActions);
		envoyerMilitaires.setLocation(400, 10);
		
		envoyerMilitaires.setEnabled(metropole.getMilitairesDisponibles() >= 10);
		rappelerMilitaires = new JButton(charRouge);
		rappelerMilitaires.setBackground(PandemiaColors.couleurFondBouttonActions);
		rappelerMilitaires.setSize(dActions);
		rappelerMilitaires.setLocation(500, 10);
		rappelerMilitaires.setEnabled(quartierSelectionne.getMilitaires() >= 10);
		
		declencherQuarantaine = new JButton(debutQuarantaine);
		declencherQuarantaine.setBackground(PandemiaColors.couleurFondBouttonQuarantaine);
		declencherQuarantaine.setSize(dActions);
		declencherQuarantaine.setLocation(600, 10);
		declencherQuarantaine.setVisible(!quartierSelectionne.isQuarantaineActivee());
		declencherQuarantaine.setEnabled(quartierSelectionne.getMilitaires() >= 
				(int) (Quartier.nombreMilitairesDeBasePourQuarantaine * Technologie.getModificateurGlobalMilitairesPourQuarantaine()));

		arreterQuarantaine = new JButton(finQuarantaine);
		arreterQuarantaine.setBackground(PandemiaColors.couleurFondBouttonQuarantaine);
		arreterQuarantaine.setSize(dActions);
		arreterQuarantaine.setLocation(600, 10);
		arreterQuarantaine.setVisible(!quartierSelectionne.isQuarantaineActivee());

		
		panelActions.add(envoyerMedecins);
		panelActions.add(rappelerMedecins);
		panelActions.add(envoyerMilitaires);
		panelActions.add(rappelerMilitaires);
		panelActions.add(declencherQuarantaine);
		panelActions.add(arreterQuarantaine);

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 10, 0); // marges
		add(labelActions, c);

		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		add(panelActions, c);

	}

}

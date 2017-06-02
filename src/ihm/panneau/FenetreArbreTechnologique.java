package ihm.panneau;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import modeles.Technologie;
import utils.PandemiaColors;

public class FenetreArbreTechnologique extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;

	public JButton retour = new JButton("Retour");
	
	JPanel medicalPanel, militairePanel, medicalBoutonsPanel = new JPanel(), militaireBoutonsPanel = new JPanel();
	JLabel medicalLabel = new JLabel("Arbre Technologique Medical");
	JLabel militaireLabel = new JLabel("Arbre Technologique Militaire");
	JPanel planteMedicinalePanel, anesthesiantPanel, savonPanel, stethoscopePanel,
	vaccins1Panel, vaccins2Panel, vaccins3Panel, penicillinePanel;
	PanneauImage planteImage, anesthesiantImage, savonImage, stethoscopeImage, vaccins1Image, vaccins2Image, vaccins3Image, penicillineImage, barbelesImage;
	JButton planteBouton, anesthesiantBouton, savonBouton, stethoscopeBouton, vaccins1Bouton, vaccins2Bouton, vaccins3Bouton, penicillineBouton, barbelesBouton;
	JProgressBar planteBar, anesthesiantBar, savonBar, stethoscopeBar, vaccins1Bar, vaccins2Bar, vaccins3Bar, penicillineBar, barbelesBar;
	JPanel masques1Panel, masques2Panel, masques3Panel, barbelesPanel;
	
	public FenetreArbreTechnologique(){
		
		addWindowListener(this);
		
		
		setLayout(new BorderLayout());
		
		medicalPanel = new JPanel();
		medicalPanel.setLayout(new BorderLayout());
		medicalLabel.setAlignmentY(CENTER_ALIGNMENT);
		medicalPanel.add(medicalLabel, BorderLayout.NORTH);
		
		medicalBoutonsPanel.setLayout(new GridLayout(1,6));
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 10, 0);

		
		try {
			planteImage = new PanneauImage(new File("images/plante.jpg"));
			anesthesiantImage = new PanneauImage(new File("images/seringue.gif"));
			savonImage = new PanneauImage(new File("images/savon.jpg"));
			stethoscopeImage = new PanneauImage(new File("images/seringue.gif"));
			vaccins1Image = new PanneauImage(new File("images/vaccins1.jpg"));
			vaccins2Image = new PanneauImage(new File("images/vaccins2.jpg"));;
			vaccins3Image = new PanneauImage(new File("images/vaccins3.jpg"));
			penicillineImage = new PanneauImage(new File("images/seringue.gif"));
			barbelesImage = new PanneauImage(new File("images/barbeles.jpg"));
		} catch (Exception e){
			e.printStackTrace();
		}
		planteMedicinalePanel = new JPanel();
		planteMedicinalePanel.setLayout(new GridBagLayout());
		planteImage.setSize(150,150);
		planteBouton = new JButton("Plante médicinale");
		planteBouton.setSize(150,50);
		planteBouton.addActionListener(this);
		planteBar = new JProgressBar(0, Technologie.getTechnologieByNom("planteMedicinale").getCoutDeLaRecherche());
		planteBar.setSize(150, 30);
		planteMedicinalePanel.add(planteImage, c);
		
		c.gridy = 2;
		
		planteMedicinalePanel.add(planteBar, BorderLayout.CENTER);
		planteMedicinalePanel.add(planteBouton, BorderLayout.SOUTH);
		medicalBoutonsPanel.add(planteMedicinalePanel);
		
		anesthesiantPanel = new JPanel();
		anesthesiantPanel.setLayout(new BorderLayout(0,5));
		anesthesiantBouton = new JButton("Anesthésiant");
		anesthesiantBouton.addActionListener(this);
		anesthesiantBar = new JProgressBar(0, Technologie.getTechnologieByNom("anesthesiant").getCoutDeLaRecherche());
		anesthesiantPanel.add(anesthesiantImage);
		anesthesiantPanel.add(anesthesiantBar);
		anesthesiantPanel.add(anesthesiantBouton);
		medicalBoutonsPanel.add(anesthesiantPanel);
		
		savonPanel = new JPanel();
		savonPanel.setLayout(new BorderLayout(0,5));
		savonBouton = new JButton("Savon");
		savonBouton.addActionListener(this);
		savonBar = new JProgressBar(0, Technologie.getTechnologieByNom("savon").getCoutDeLaRecherche());
		savonPanel.add(savonImage);
		savonPanel.add(savonBar);
		savonPanel.add(savonBouton);
		medicalBoutonsPanel.add(savonPanel);
		
		vaccins1Panel = new JPanel();
		vaccins1Panel.setLayout(new BorderLayout(0,5));
		vaccins1Bouton = new JButton("Vaccins 1");
		vaccins1Bouton.addActionListener(this);
		vaccins1Bar = new JProgressBar(0, Technologie.getTechnologieByNom("vaccins1").getCoutDeLaRecherche());
		vaccins1Panel.add(vaccins1Image);
		vaccins1Panel.add(vaccins1Bar);
		vaccins1Panel.add(vaccins1Bouton);
		medicalBoutonsPanel.add(vaccins1Panel);
		
		vaccins2Panel = new JPanel();
		vaccins2Panel.setLayout(new BorderLayout(0,5));
		vaccins2Bouton = new JButton("Vaccins 2");
		vaccins2Bouton.addActionListener(this);
		vaccins2Bar = new JProgressBar(0, Technologie.getTechnologieByNom("vaccins2").getCoutDeLaRecherche());
		vaccins2Panel.add(vaccins2Image);
		vaccins2Panel.add(vaccins2Bar);
		vaccins2Panel.add(vaccins2Bouton);
		Boolean vaccins2Disponible = Technologie.getTechnologieByNom("vaccins1").getAvancementDeLaRecherche() == Technologie.getTechnologieByNom("vaccins1").getCoutDeLaRecherche();
		vaccins2Panel.setVisible(vaccins2Disponible);
		medicalBoutonsPanel.add(vaccins2Panel);
		
		vaccins3Panel = new JPanel();
		vaccins3Panel.setLayout(new BorderLayout(0,5));
		vaccins3Bouton = new JButton("Vaccins 3");
		vaccins3Bouton.addActionListener(this);
		vaccins3Bar = new JProgressBar(0, Technologie.getTechnologieByNom("vaccins3").getCoutDeLaRecherche());
		vaccins3Panel.add(vaccins3Image);
		vaccins3Panel.add(vaccins3Bar);
		vaccins3Panel.add(vaccins3Bouton);
		Boolean vaccins3Disponible = Technologie.getTechnologieByNom("vaccins2").getAvancementDeLaRecherche() == Technologie.getTechnologieByNom("vaccins2").getCoutDeLaRecherche();
		vaccins3Panel.setVisible(vaccins3Disponible);
		medicalBoutonsPanel.add(vaccins3Panel);
		
		medicalPanel.add(medicalBoutonsPanel, BorderLayout.CENTER);
		
		militairePanel = new JPanel();
		militairePanel.setLayout(new BorderLayout());
		militairePanel.add(militaireLabel, BorderLayout.NORTH);
		
		barbelesPanel = new JPanel();
		barbelesPanel.setLayout(new BorderLayout(0,5));
		barbelesBouton = new JButton("Barbelés");
		barbelesBouton.addActionListener(this);
		barbelesBar = new JProgressBar(0, Technologie.getTechnologieByNom("barbeles").getCoutDeLaRecherche());
		barbelesPanel.add(barbelesImage);
		barbelesPanel.add(barbelesBar);
		barbelesPanel.add(barbelesBouton);
		militaireBoutonsPanel.add(barbelesPanel);
		
		militairePanel.add(militaireBoutonsPanel);
		
		add(new JLabel("Arbre Technologique"), BorderLayout.NORTH);
		add(medicalPanel, BorderLayout.WEST);
		add(militairePanel, BorderLayout.EAST);
		retour.addActionListener(this);
		add(retour, BorderLayout.SOUTH);
				
		setBackground(PandemiaColors.couleurFondPanneauMenu);
		
		setLocation(0, 0);
		setSize(1000,680);
		
	}
	
	public void actualisePanneauArbreTechnologique(){
		barbelesBar.setValue(Technologie.getTechnologieByNom("barbeles").getAvancementDeLaRecherche());
		anesthesiantBar.setValue(Technologie.getTechnologieByNom("anesthesiant").getAvancementDeLaRecherche());
	//	penicillineBar.setValue(Technologie.getTechnologieByNom("penicilline").getAvancementDeLaRecherche());
		planteBar.setValue(Technologie.getTechnologieByNom("planteMedicinale").getAvancementDeLaRecherche());
	//	stethoscopeBar.setValue(Technologie.getTechnologieByNom("stethoscope").getAvancementDeLaRecherche());
		savonBar.setValue(Technologie.getTechnologieByNom("savon").getAvancementDeLaRecherche());
		vaccins1Bar.setValue(Technologie.getTechnologieByNom("vaccins1").getAvancementDeLaRecherche());
		vaccins2Bar.setValue(Technologie.getTechnologieByNom("vaccins2").getAvancementDeLaRecherche());
		vaccins3Bar.setValue(Technologie.getTechnologieByNom("vaccins3").getAvancementDeLaRecherche());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == retour){
			dispose();
		}
		if(event.getSource() == planteBouton){
			Technologie.definirObjectifDeRechercheMedicale("planteMedicinale");
		}
		if(event.getSource() == savonBouton){
			Technologie.definirObjectifDeRechercheMedicale("savon");
		}
		if(event.getSource() == anesthesiantBouton){
			Technologie.definirObjectifDeRechercheMedicale("anesthesiant");
		}
		if(event.getSource() == penicillineBouton){
			Technologie.definirObjectifDeRechercheMedicale("penicilline");
		}
		if(event.getSource() == stethoscopeBouton){
			Technologie.definirObjectifDeRechercheMedicale("stethoscope");
		}
		if(event.getSource() == vaccins1Bouton){
			Technologie.definirObjectifDeRechercheMedicale("vaccins1");
		}
		if(event.getSource() == barbelesBouton){
			Technologie.definirObjectifDeRechercheMilitaire("barbeles");
		}
		if(event.getSource() == vaccins2Bouton){
			Technologie.definirObjectifDeRechercheMedicale("vaccins2");
		}
		if(event.getSource() == vaccins3Bouton){
			Technologie.definirObjectifDeRechercheMedicale("vaccins3");
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		requestFocus();
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
	
}

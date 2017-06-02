package ihm.panneau;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.Metropole;
import utils.DateUtils;
import utils.PandemiaColors;

public class BandeauSuperieur extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Metropole metropole;
	
	public static JLabel date;
	public JButton menu;
	public JButton plusInformations = new JButton("Rapport Epidemie");
	public JButton recherche = new JButton("Arbre Technologique");
	
	public JPanel populationTotalPanel = new JPanel();
	public PanneauImage populationTotalPanneauImage;
	public JLabel populationTotalLabel;
	
	public JPanel medecinsTotalPanel = new JPanel();
	public PanneauImage medecinsTotalPanneauImage;
	public JLabel medecinsTotalLabel;
	
	public JPanel militairesTotalPanel = new JPanel();
	public PanneauImage militairesTotalPanneauImage;
	public JLabel militairesTotalLabel;
	
	public JPanel vaccinsPanel = new JPanel();
	public PanneauImage vaccinsPanneauImage;
	public JLabel vaccinsLabel;

	public BandeauSuperieur(Metropole metropole) {
		
		this.metropole = metropole;
		
		setBackground(PandemiaColors.couleurFondBandeauSuperieur);
		
		add(recherche);
		add(plusInformations);
		
		date = new JLabel("00:00");
		date.setForeground(PandemiaColors.couleurTexteBandeauSuperieur);
		
		try{
			Dimension d = new Dimension(20,20);
			populationTotalPanneauImage = new PanneauImage(new File("images/seringue.gif"), d);
			medecinsTotalPanneauImage = new PanneauImage(new File("images/seringue.gif"), d);
			militairesTotalPanneauImage = new PanneauImage(new File("images/seringue.gif"), d);
			vaccinsPanneauImage = new PanneauImage(new File("images/seringue.gif"), d);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		populationTotalPanel.add(populationTotalPanneauImage);
		populationTotalLabel = new JLabel("Population: " + metropole.getPopulationActuelleTotal());
		populationTotalPanel.add(populationTotalLabel);		
		add(populationTotalPanel);
		
		medecinsTotalPanel.add(medecinsTotalPanneauImage);
		medecinsTotalLabel = new JLabel("Médecins: " + metropole.getMedecinsDisponibles() + " / " + metropole.getMedecinsTotal());
		medecinsTotalPanel.add(medecinsTotalLabel);		
		add(medecinsTotalPanel);
		
		militairesTotalPanel.add(militairesTotalPanneauImage);
		militairesTotalLabel = new JLabel("Militaires: " + metropole.getMilitairesDisponibles() + " / " + metropole.getMilitairesTotal());
		militairesTotalPanel.add(militairesTotalLabel);		
		add(militairesTotalPanel);
		
		vaccinsPanel.add(vaccinsPanneauImage);
		vaccinsLabel = new JLabel("Vaccins: " + metropole.getVaccins());
		vaccinsPanel.add(vaccinsLabel);		
		add(vaccinsPanel);
		
		add(date);
		
		menu = new JButton("Menu");
		add(menu);
	}

	
	public void actualiserBandeauSuperieur(){
		date.setText(DateUtils.getHHmm());
		populationTotalLabel.setText("Population: " + metropole.getPopulationActuelleTotal());
		medecinsTotalLabel.setText("Médecins: " + metropole.getMedecinsDisponibles() + " / " + metropole.getMedecinsTotal());
		militairesTotalLabel.setText("Militaires: " + metropole.getMilitairesDisponibles() + " / " + metropole.getMilitairesTotal());
		vaccinsLabel.setText("Vaccins: " + metropole.getVaccins());
	}

	
}

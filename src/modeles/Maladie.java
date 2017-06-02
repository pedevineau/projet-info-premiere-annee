package modeles;

import java.util.Map;

public class Maladie {
	
	private String nom;
	private int niveauDeSoinDeBase;
	private boolean monoSouche;
	double tauxLetalite;
	double parametreVirulenceBase;
	
	public Maladie(String nom, int niveauDeSoinDeBase, boolean monoSouche, double tauxLetalite, 
			double parametreVirulence){
		this.nom = nom;
		this.niveauDeSoinDeBase = niveauDeSoinDeBase;
		this.monoSouche = monoSouche;
		this.tauxLetalite = tauxLetalite;
		this.parametreVirulenceBase = parametreVirulence;
	}
	
	public Maladie(Map<String, Object> map){
		this((String) map.get("nom"), Integer.parseInt((String) map.get("niveauDeSoinDeBase")), Boolean.parseBoolean((String) map.get("monoSouche")), 
				Double.parseDouble((String) map.get("tauxLetalite")), Double.parseDouble((String) map.get("parametreVirulence")));
	}
	
	public double getParametreVirulenceBase() {
		return parametreVirulenceBase;
	}

	public void setParametreVirulenceBase(double parametreVirulenceBase) {
		this.parametreVirulenceBase = parametreVirulenceBase;
	}

	public boolean isMonoSouche() {
		return monoSouche;
	}
	
	public void setMonoSouche(boolean monoSouche) {
		this.monoSouche = monoSouche;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public double getTauxLetalite() {
		return tauxLetalite;
	}
	
	public void setTauxLetalite(double tauxLetalite) {
		this.tauxLetalite = tauxLetalite;
	}


	public int getNiveauDeSoinDeBase() {
		return niveauDeSoinDeBase;
	}

	public void setNiveauDeSoinDeBase(int niveauDeSoinDeBase) {
		this.niveauDeSoinDeBase = niveauDeSoinDeBase;
	}
	
}

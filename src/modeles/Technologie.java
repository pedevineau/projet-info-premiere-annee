package modeles;

import java.util.ArrayList;
import java.util.Map;

public class Technologie {
	
	public final static String URL_TECHNOLOGIES = "fichiersYAML/modeles/Technologies.yaml";
	
	public static Technologie rechercheMedicaleEnCours = new Technologie();
	public static Technologie rechercheMilitaireEnCours = new Technologie();
	public static ArrayList<Technologie> listeTechnologies = new ArrayList<Technologie>();
	
	String nom;
	//type de recherche : soit médicale soit militaire
	String typeDeRecherche;
	int avancementDeLaRecherche;
	int coutDeLaRecherche;
	double modificateurEfficaciteMedecins;
	double modificateurEfficaciteMilitaires;
	int modificateurVaccinsFabriqueParMedecin;
	double modificateurMilitairesPourQuarantaine;

	public Technologie(String nom, String typeDeRecherche, int avancementDeLaRecherche,
			int coutDeLaRecherche, double modificateurEfficaciteMedecins,
			double modificateurEfficaciteMilitaires, int modificateurVaccinsFabriqueParMedecins,
			double modificateurMilitairesPourQuarantaine){
		this.nom = nom;
		this.typeDeRecherche = typeDeRecherche;
		this.avancementDeLaRecherche = avancementDeLaRecherche;
		this.coutDeLaRecherche = coutDeLaRecherche;
		this.modificateurEfficaciteMedecins = modificateurEfficaciteMedecins;
		this.modificateurEfficaciteMilitaires = modificateurEfficaciteMilitaires;
		this.modificateurVaccinsFabriqueParMedecin = modificateurVaccinsFabriqueParMedecins;
		this.modificateurMilitairesPourQuarantaine = modificateurMilitairesPourQuarantaine;
	}
	
	public Technologie(){
		this("", "medicale",0,0,1.0,1.0,0,1.0);
	}
	
	public Technologie(Map<String, Object> map){
		this(
				(String) map.get("nom"),
				(String) map.get("typeDeRecherche"),
				Integer.parseInt((String) map.get("avancementDeLaRecherche")),
				Integer.parseInt((String) map.get("coutDeLaRecherche")),
				Double.parseDouble((String) map.get("modificateurEfficaciteMedecins")),
				Double.parseDouble((String) map.get("modificateurEfficaciteMilitaires")),
				Integer.parseInt((String) map.get("modificateurVaccinsFabriqueParMedecin")),
				Double.parseDouble((String) map.get("modificateurMilitairesPourQuarantaine"))
			);
	}
	
	public String getNom() {
		return nom;
	}

	public String getTypeDeRecherche() {
		return typeDeRecherche;
	}

	public int getAvancementDeLaRecherche() {
		return avancementDeLaRecherche;
	}

	public int getCoutDeLaRecherche() {
		return coutDeLaRecherche;
	}

	public double getModificateurEfficaciteMedecins() {
		return modificateurEfficaciteMedecins;
	}

	public double getModificateurEfficaciteMilitaires() {
		return modificateurEfficaciteMilitaires;
	}

	public int getModificateurVaccinsFabriqueParMedecins() {
		return modificateurVaccinsFabriqueParMedecin;
	}

	public double getModificateurMilitairesPourQuarantaine() {
		return modificateurMilitairesPourQuarantaine;
	}

	@SuppressWarnings("unchecked")
	public static void initialiseTechnologies(ArrayList<Object> liste){
		for(int i = 0; i < liste.size(); i++){
			listeTechnologies.add(new Technologie((Map<String, Object>) liste.get(i)));
		}
	}
	
	public static Technologie getTechnologieByNom(String nom){
		for (int i = 0; i < listeTechnologies.size(); i++){
			if(listeTechnologies.get(i).nom.equals(nom)){
				return listeTechnologies.get(i);
			}
		}
		return null;
	}
	
	public static void definirObjectifDeRechercheMedicale(String nom){
		rechercheMedicaleEnCours = getTechnologieByNom(nom);
	}
	public static void definirObjectifDeRechercheMilitaire(String nom){
		rechercheMilitaireEnCours = getTechnologieByNom(nom);
	}
	
	public static void actualiseRechercheTechnologique(int medecinsDisponibles, int militairesDisponibles){
		if(rechercheMedicaleEnCours.avancementDeLaRecherche + medecinsDisponibles < rechercheMedicaleEnCours.coutDeLaRecherche){
			rechercheMedicaleEnCours.avancementDeLaRecherche += medecinsDisponibles;
		} else {
			rechercheMedicaleEnCours.avancementDeLaRecherche = rechercheMedicaleEnCours.coutDeLaRecherche;
			//TODO notification
		}
		if(rechercheMilitaireEnCours.avancementDeLaRecherche + militairesDisponibles < rechercheMilitaireEnCours.coutDeLaRecherche){
			rechercheMilitaireEnCours.avancementDeLaRecherche += militairesDisponibles;
		} else {
			rechercheMilitaireEnCours.avancementDeLaRecherche = rechercheMilitaireEnCours.coutDeLaRecherche;
			//TODO notification
		}
	}
	public static double getModificateurGlobalEfficaciteMedecins(){
		double produit = 1.;
		for (int i = 0; i < listeTechnologies.size(); i++){
			if(listeTechnologies.get(i).avancementDeLaRecherche == listeTechnologies.get(i).coutDeLaRecherche){
				produit *= listeTechnologies.get(i).modificateurEfficaciteMedecins;
			}
		}
		return produit;
	}
	
	public static double getModificateurGlobalEfficaciteMilitaires(){
		double produit = 1.;
		for (int i = 0; i < listeTechnologies.size(); i++){
			if(listeTechnologies.get(i).avancementDeLaRecherche == listeTechnologies.get(i).coutDeLaRecherche){
				produit *= listeTechnologies.get(i).modificateurEfficaciteMilitaires;
			}
		}
		return produit;
	}
	
	public static double getModificateurGlobalMilitairesPourQuarantaine(){
		double produit = 1.;
		for (int i = 0; i < listeTechnologies.size(); i++){
			if(listeTechnologies.get(i).avancementDeLaRecherche == listeTechnologies.get(i).coutDeLaRecherche){
				produit *= listeTechnologies.get(i).modificateurMilitairesPourQuarantaine;
			}
		}
		return produit;
	}
		
	public static int getModificateurGlobalVaccinsFabriqueParMedecin(){
		int somme = 0;
		for (int i = 0; i < listeTechnologies.size(); i++){
			if(listeTechnologies.get(i).avancementDeLaRecherche == listeTechnologies.get(i).coutDeLaRecherche){
				somme += listeTechnologies.get(i).modificateurVaccinsFabriqueParMedecin;
			}
		}
		return somme;
	}
	
}

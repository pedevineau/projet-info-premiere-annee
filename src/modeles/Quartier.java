package modeles;

import java.util.Map;

public class Quartier {
	
	public static double nombreVaccinablesDeBaseParMedecin = 4;
	public static double nombreGuerisDeBaseParMedecin = 2;
	public static int nombreMilitairesDeBasePourQuarantaine = 100;
	public static int parametreExodeDeBaseParMilitaire = 12;


	// le nom sert aussi d'identifiant unique, il ne peut être modifié par le
	// joueur
	private String nom;
	private int populationInitiale;
	private int immunises;
	private int sains;
	private int malades;
	private int morts;
	private int medecins;
	private int militaires;
	private double tauxMobilite;
	private boolean quarantaineActivee;

	// Comment faire intervenir les militaires ?

	public Quartier(String nom, int populationInitiale, double tauxMobilite) {
		this.nom = nom;
		this.populationInitiale = populationInitiale;
		sains = populationInitiale;
		immunises = 0;
		malades = 0;
		morts = 0;
		medecins = populationInitiale / 1000;
		militaires = populationInitiale / 500;
		this.tauxMobilite = tauxMobilite;
		quarantaineActivee = false;
	}

	public Quartier(String nom, int populationInitiale, int sains, int immunises, int malades, int morts, int medecins,
			int militaires, double tauxMobilite, boolean quarantaineActivee) {
		this.nom = nom;
		this.populationInitiale = populationInitiale;
		this.sains = sains;
		this.immunises = immunises;
		this.malades = malades;
		this.morts = morts;
		this.medecins = medecins;
		this.militaires = militaires;
		this.tauxMobilite = tauxMobilite;
		this.quarantaineActivee = quarantaineActivee;
	}

	public Quartier(Map<String, Object> mapQuartier) {
		nom = (String) mapQuartier.get("nom");
		populationInitiale = Integer.parseInt((String)mapQuartier.get("populationInitiale"));
		immunises = Integer.parseInt((String) mapQuartier.get("immunises"));
		sains = Integer.parseInt((String)mapQuartier.get("sains"));
		malades = Integer.parseInt((String) mapQuartier.get("malades"));
		morts = Integer.parseInt((String) mapQuartier.get("morts"));
		medecins = Integer.parseInt((String) mapQuartier.get("medecins"));
		militaires = Integer.parseInt((String) mapQuartier.get("militaires"));
		tauxMobilite = Double.parseDouble((String) mapQuartier.get("tauxMobilite"));
		quarantaineActivee = Boolean.parseBoolean((String)mapQuartier.get("quarantaineActivee"));
	}

	// unicité du nom d'un quartier
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quartier other = (Quartier) obj;
		if (nom != other.nom)
			return false;
		return true;
	}

	public int getPopulationInitiale() {
		return populationInitiale;
	}

	public int getPopulationActuelle() {
		return sains + malades + immunises;
	}

	public int getSains() {
		return sains;
	}

	public void setSains(int sains) {
		this.sains = sains;
	}

	public int getMalades() {
		return malades;
	}

	public void setMalades(int malades) {
		this.malades = malades;
	}

	public int getMorts() {
		return morts;
	}

	public void setMorts(int morts) {
		this.morts = morts;
	}

	public int getMedecins() {
		return medecins;
	}

	public void setMedecins(int medecins) {
		this.medecins = medecins;
	}

	public int getMilitaires() {
		return militaires;
	}

	public void setMilitaires(int militaires) {
		this.militaires = militaires;
	}

	public double getTauxExode() {
		return tauxMobilite;
	}

	public void setTauxExode(double tauxMobilite) {
		this.tauxMobilite = tauxMobilite;
	}

	public boolean isQuarantaineActivee() {
		return quarantaineActivee;
	}

	public void setQuarantaineActivee(boolean quarantaineActivee) {
		this.quarantaineActivee = quarantaineActivee;
	}

	public String getNom() {
		return nom;
	}

	public int getImmunises() {
		return immunises;
	}

	public void setImmunises(int immunises) {
		this.immunises = immunises;
	}
	
	public void tomberMalades(int nouveauxMalades){
		if(nouveauxMalades < getSains()){
			setMalades(getMalades() + nouveauxMalades);
			setSains(getSains() - nouveauxMalades);			
		}
	}
	public void guerir(int gueris){
		if(gueris < getMalades()){
			setMalades(getMalades() - gueris);
			setSains(getSains() + gueris);			
		}
	}
	
	
	public void actualiserTauxExode(){
		if (isQuarantaineActivee()){
			setTauxExode(0);
		} else {
			//le taux d'exode est toujours compris entre 1 pour 1000 et 1 pour 100
			double tauxMalades = getMalades()/(double) getPopulationActuelle();
			double tauxMilitaires = getMilitaires()/(double)getPopulationActuelle(); 
			double taux = Math.max(0.001,Math.min(0.01, 0.05 * (tauxMalades - tauxMilitaires * 
					parametreExodeDeBaseParMilitaire * Technologie.getModificateurGlobalEfficaciteMilitaires())));
			setTauxExode(taux);
		}
	}
	
	
	public int vacciner(int vaccins) {
		/*
		 * - `vaccinables` est le nombre maximal de personnes que l'on peut
		 *    vacciner avec les médecins (du quartier) et le quotat de vaccins.
		 * - `vaccinsUtilises` est le nombre reel de vaccins consommés, qui peut
		 *   être moindre que `vaccinables`, s'il n'y a plus assez de monde à
		 *   vacciner
		 */
		int vaccinables = Math.min(vaccins, (int) (getMedecins() * nombreVaccinablesDeBaseParMedecin *
				Technologie.getModificateurGlobalVaccinsFabriqueParMedecin()));
		int vaccinsUtilises = Math.min(vaccinables, getSains());
		setSains(getSains() - vaccinsUtilises);
		setImmunises(getImmunises() + vaccinsUtilises);
		return vaccinsUtilises;
	}	
	
	
}

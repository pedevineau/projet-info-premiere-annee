package modeles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.YAML.LectureYAML;

public class Metropole {
	
	private static int vaccinsFabriqueParMedecins = 0;
	public static double pourcentageDefaite = 0.4;
	public static double tauxGuerisSpontanes = 0.05;
	
	private String nom;
	private String urlCarte;
	public Quartier[] ville;
	private int medecinsDisponibles;
	private int militairesDisponibles;
	private int populationActuelleTotal;
	private int medecinsTotal;
	private int militairesTotal;
	private int vaccins;

	//premier constructeur pour les tests, à enlever dans la version finale
	public Metropole(String nom, Quartier[] ville, int medecinsDisponibles, int militairesDiponibles) {
		this.nom = nom;
		this.ville = ville;
		this.medecinsDisponibles = medecinsDisponibles;
		this.militairesDisponibles = militairesDiponibles;
		setPopulationActuelleTotal(vaccins = 0);
		medecinsTotal = medecinsDisponibles;
		militairesTotal = militairesDisponibles;
		for (int i = 0; i < ville.length; i++) {
			setPopulationActuelleTotal(getPopulationActuelleTotal() + ville[i].getPopulationActuelle());
			medecinsTotal += ville[i].getMedecins();
			militairesTotal += ville[i].getMilitaires();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Metropole(Map<String, Object> configs){
		urlCarte = (String) configs.get("urlCarte");
		HashMap<String, Object> metropoleConfigs = (HashMap<String, Object>) configs.get("Metropole");
		nom = (String) metropoleConfigs.get("nom");
		medecinsDisponibles = Integer.parseInt((String) metropoleConfigs.get("medecinsDisponibles"));
		militairesDisponibles = Integer.parseInt((String) metropoleConfigs.get("militairesDisponibles"));
		vaccins = Integer.parseInt((String) metropoleConfigs.get("vaccins"));
		ArrayList<Object> villeConfigs = (ArrayList<Object>) metropoleConfigs.get("ville");
		Quartier[] ville = new Quartier[villeConfigs.size()];
		for(int i = 0; i < villeConfigs.size(); i++){
			ville[i] = new Quartier((Map<String, Object>) villeConfigs.get(i));
		}
		this.ville = ville;
		medecinsTotal = medecinsDisponibles;
		militairesTotal = militairesDisponibles;
		for (int i = 0; i < ville.length; i++) {
			setPopulationActuelleTotal(getPopulationActuelleTotal() + ville[i].getPopulationActuelle());
			medecinsTotal += ville[i].getMedecins();
			militairesTotal += ville[i].getMilitaires();
		}
	}

	@SuppressWarnings("unchecked")
	public Metropole(String urlYAML) {
		this((Map<String, Object>)LectureYAML.getConfigs(urlYAML));
	}
		
	public String getNom(){
		return nom;
	}

	public String getUrlCarte(){
		return urlCarte;
	}
	
	public int getMedecinsDisponibles() {
		return medecinsDisponibles;
	}

	public void setMedecinsDisponibles(int medecinsDisponibles) {
		this.medecinsDisponibles = medecinsDisponibles;
	}

	public int getMilitairesDisponibles() {
		return militairesDisponibles;
	}

	public void setMilitairesDisponibles(int militairesDisponibles) {
		this.militairesDisponibles = militairesDisponibles;
	}

	public int getPopulationActuelleTotal() {
		return populationActuelleTotal;
	}

	public void setPopulationActuelleTotal(int populationActuelleTotal) {
		this.populationActuelleTotal = populationActuelleTotal;
	}
	public int getPopulationInitialeTotal() {
		int popIni = 0;
		for(int i = 0; i < ville.length; i++){
			popIni += ville[i].getPopulationInitiale();
		}
		return popIni;
	}

	public int getMedecinsTotal() {
		return medecinsTotal;
	}

	public int getMilitairesTotal() {
		return militairesTotal;
	}

	public int getVaccins() {
		return vaccins;
	}

	public void setVaccins(int vaccins) {
		this.vaccins = vaccins;
	}

	public int getSainsTotal(){
		int sains = 0;
		for(int i = 0; i < ville.length; i++){
			sains += ville[i].getSains();
		}
		return sains;
	}

	public int getImmunisesTotal(){
		int immunises = 0;
		for(int i = 0; i < ville.length; i++){
			immunises += ville[i].getImmunises();
		}
		return immunises;
	}
	
	public int getMaladesTotal(){
		int malades = 0;
		for(int i = 0; i < ville.length; i++){
			malades += ville[i].getMalades();
		}
		return malades;
	}
	
	public int getMortsTotal(){
		int morts = 0;
		for(int i = 0; i < ville.length; i++){
			morts += ville[i].getMorts();
		}
		return morts;
	}

	// obtenir un quartier à partir de son nom
	public Quartier getQuartierByNom(String nom) {
		for (int i = 0; i < ville.length; i++) {
			if (ville[i].getNom() == nom)
				return ville[i];
		}
		return null;
	}
	
	public int getIndiceByQuartier(Quartier quartier){
		for(int indice = 0; indice < ville.length; indice++){
			if(ville[indice].equals(quartier)){
				return indice;
			}
		}
		System.err.println("Absent !");
		return -1;		
	}
	
	public boolean sontVoisins(Quartier q1, Quartier q2){
		int indice1 = getIndiceByQuartier(q1);
		int indice2 = getIndiceByQuartier(q2);
		return (( Math.abs(indice1-indice2) == 1) || (Math.abs(indice1-indice2) == 5));
	}

	public void depecherMedecins(Quartier quartier, int medecins) {
		setMedecinsDisponibles(getMedecinsDisponibles() - medecins);
		quartier.setMedecins(medecins + quartier.getMedecins());
	}

	public void depecherMilitaires(Quartier quartier, int militaires) {
		setMilitairesDisponibles(getMilitairesDisponibles() - militaires);
		quartier.setMilitaires(militaires + quartier.getMilitaires());
	}

	public void rappelerMedecins(Quartier quartier, int medecins) {
		setMedecinsDisponibles(getMedecinsDisponibles() + medecins);
		quartier.setMedecins(quartier.getMedecins() - medecins);
	}

	public void rappelerMilitaires(Quartier quartier, int militaires) {
		setMilitairesDisponibles(getMilitairesDisponibles() + militaires);
		quartier.setMilitaires(quartier.getMilitaires() - militaires);
	}
	
	public void exodeEntre2Quartiers(Quartier quartierOrigine, Quartier quartierDestination, int populationEnExode){
		double taux =  populationEnExode/(float) quartierOrigine.getPopulationActuelle();
		int sainsEnExode = (int) (quartierOrigine.getSains() * taux);
		int maladesEnExode = (int) (quartierOrigine.getMalades() * taux);
		int immunisesEnExode = (int) (quartierOrigine.getImmunises() * taux);
		quartierOrigine.setSains(quartierOrigine.getSains() - sainsEnExode);
		quartierOrigine.setMalades(quartierOrigine.getMalades() - maladesEnExode);
		quartierOrigine.setImmunises(quartierOrigine.getImmunises() - immunisesEnExode);
		quartierDestination.setSains(quartierDestination.getSains() + sainsEnExode);
		quartierDestination.setMalades(quartierDestination.getMalades() + maladesEnExode);
		quartierDestination.setImmunises(quartierDestination.getImmunises() + immunisesEnExode);		
	}

	
	public void exode(){
		// plus le taux de mobilité est important, plus il y a de mouvements entrant et sortant !
		for (int origine = 0; origine < ville.length; origine++){
			ville[origine].actualiserTauxExode();
			if(!ville[origine].isQuarantaineActivee()){
				int[] tableauExode = new int[ville.length];
				int populationFuyantMoyenne = (int) (ville[origine].getTauxExode() * ville[origine].getPopulationActuelle());
				int totalCoef = 0;
				for (int i = 0; i < ville.length; i++){
					if(sontVoisins(ville[i], ville[origine])){
						totalCoef += 12;
					} else {
						totalCoef += 1;
					}
				}
				for (int destination = 0; destination < ville.length; destination++){
					//destination peut être égal à origine, mais ce n'est pas un problème
					if(!ville[destination].isQuarantaineActivee()){
						tableauExode[destination] = (int) (2 * Math.random() * populationFuyantMoyenne / totalCoef);
						if(sontVoisins(ville[origine], ville[destination])){
							tableauExode[destination] *= 12;
						}
						exodeEntre2Quartiers(ville[origine], ville[destination], tableauExode[destination]);
					}
				}
			}
		}
	}

	public void initialiseEpidemie(Maladie maladie){
		int foyerPrincipal = (int)(Math.random() * ville.length);
		//entre 600 et 800 malades dans le foyer principal
		int maladesFoyerPrincipal = 600 + (int)(Math.random() * 800);
		// 2 foyers secondaires avec 200 malades maximum
		int foyerSecondaire1 = (int)(Math.random() * ville.length);
		int foyerSecondaire2 = (int)(Math.random() * ville.length);

		int maladesFoyerSecondaire1 = (int)(Math.random() * 200);
		int maladesFoyerSecondaire2 = (int)(Math.random() * 200);
		ville[foyerPrincipal].tomberMalades(maladesFoyerPrincipal);
		ville[foyerSecondaire1].tomberMalades(maladesFoyerSecondaire1);
		ville[foyerSecondaire2].tomberMalades(maladesFoyerSecondaire2);
	}
	
	
	public int effetMaladieSurUnQuartier(Quartier quartier, Maladie maladie, double parametreVirulence) {
		/*
		 * - la fonction actualiserEpidemie gère les soins de base, la vaccination (quand elle est possible)
		 *   et la propagation de l'épidémie
		 * - le taux de guérisons spontanées est défini ici; il est
		 *   inférieur ou égal à la moitié du taux de létalité `tauxLetalite`
		 */
		// il faudrait introduire davantage d'aléatoire !
		int decedes = (int) (quartier.getMalades() * maladie.getTauxLetalite());
		quartier.setMorts(quartier.getMorts() + decedes);
		quartier.setMalades(quartier.getMalades() - decedes);
		int guerisSpontanes = (int) (quartier.getMalades() * tauxGuerisSpontanes);
		if (maladie.isMonoSouche()) {
			quartier.setImmunises(quartier.getImmunises() + guerisSpontanes);
			quartier.setMalades(quartier.getMalades() - guerisSpontanes);
		} else {
			quartier.setSains(quartier.getSains() + guerisSpontanes);
			quartier.setMalades(quartier.getMalades() - guerisSpontanes);
		}
		int guerisParLesMedecins = Math.min(quartier.getMalades(),(int) (quartier.getMedecins() * Quartier.nombreGuerisDeBaseParMedecin * 
				maladie.getNiveauDeSoinDeBase() * Technologie.getModificateurGlobalEfficaciteMedecins()));
		quartier.setMalades(quartier.getMalades() - guerisParLesMedecins);
		quartier.setSains(quartier.getSains() + guerisParLesMedecins);
		// Rend de nouvelles personnes malades.
		//   parametreVirulence : caractérise la vitesse de propagation
		double tauxMalades = quartier.getMalades() / (double) quartier.getPopulationActuelle();
		int nouveauxInfectes = (int) ((quartier.getSains()) * (1 - Math.exp(- parametreVirulence * tauxMalades)) * (0.5 + Math.random()));
	
		quartier.setSains(quartier.getSains() - nouveauxInfectes);
		quartier.setMalades(quartier.getMalades() + nouveauxInfectes);
		
		return decedes;
	}
	
	 /* le systeme est fait pour qu'on puisse utiliser tous les vaccins produits
	 * chaque jour si on s'y prend bien
	 */
	public void actualiserEpidemie(Maladie maladie) {
		
		//ceci vaut 0 quand aucun vaccin n'a été découvert
		setVaccins((int) (getMedecinsDisponibles() * (vaccinsFabriqueParMedecins + Technologie.getModificateurGlobalVaccinsFabriqueParMedecin())+ getVaccins()));
		
		/*
		 * chaque quartier reçoit un nombre de vaccins proportionnels au nombre
		 * de médecins dépéchés. Si toute la population est immunisee, il rend
		 * les vaccins non utilises
		 */
		for (int i = 0; i < ville.length; i++) {
	
			int vaccinsDisponiblesPourCeQuartier;
			int medecinsVaccinateursTotal = getMedecinsTotal() - getMedecinsDisponibles();
		
			if (medecinsVaccinateursTotal == 0){
				vaccinsDisponiblesPourCeQuartier = 0;
			} else {
				vaccinsDisponiblesPourCeQuartier = getVaccins() * ville[i].getMedecins() / medecinsVaccinateursTotal;
			}
			int vaccinsUtilises = ville[i].vacciner(vaccinsDisponiblesPourCeQuartier);
			setVaccins(getVaccins() - vaccinsUtilises);
			
			//plus il y a de militaires, plus le quartier est confiné, donc plus la virulence est faible
			
			double parametreVirulence = maladie.getParametreVirulenceBase()
					* (1.1 - (float) ville[i].getMilitaires() / getMilitairesTotal());
			
			setPopulationActuelleTotal(getPopulationActuelleTotal() - effetMaladieSurUnQuartier(ville[i], maladie, parametreVirulence));
		}

	}

}

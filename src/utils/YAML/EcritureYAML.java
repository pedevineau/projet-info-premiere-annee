package utils.YAML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modeles.Maladie;
import modeles.Metropole;
import modeles.Quartier;
import modeles.Technologie;
import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

//ATTENTION : en cas de sauvegarde-chargement, le nb de médecins et de militaires est recalculé. DANGER !
//la seule solution consiste à ajouter tous les paramètres (médecins, militaires, morts, immunisés, etc. dans le fichier YAML)

public class EcritureYAML {
	public static void addData(String urlYAML, String cle, Object valeur){
		try {
			//pour copier le texte déjà présent dans le texte, puis le réécrire suivi de la nouvelle data
			YamlReader ymrd = new YamlReader(new FileReader(urlYAML));
			@SuppressWarnings({ "unchecked", "rawtypes" })
			HashMap<String, Object> data = (HashMap) ymrd.read();
			YamlWriter ymwt = new YamlWriter(new FileWriter(urlYAML));
			data.put(cle,valeur);
			ymwt.write(data);
			ymwt.close();
		} catch (IOException | YamlException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Object> makeSauvegarde(String pseudo, Metropole metropole, Maladie maladie){
		Map<String, Object> configs = new HashMap<String, Object>();
		Map<String, Object> metropoleConfigs = makeMetropole(metropole);
		Map<String, Object> maladieConfigs = makeMaladie(maladie);
		ArrayList<Object> arbreTechnologiqueConfigs = makeListeTechnologies(Technologie.listeTechnologies);
		configs.put("Pseudo", pseudo);
		configs.put("Metropole", metropoleConfigs);
		configs.put("Maladie", maladieConfigs);
		configs.put("ArbreTechnologique", arbreTechnologiqueConfigs);
		configs.put("urlCarte", metropole.getUrlCarte());
		return configs;
	}

	public static void makeConfigs(String urlYAML, Metropole metropole){
		Map<String, Object> configs = new HashMap<String, Object>();
		Map<String, Object> metropoleConfigs = makeMetropole(metropole);
		configs.put("Metropole", metropoleConfigs);
		configs.put("urlCarte", metropole.getUrlCarte());
		try {
			YamlWriter ymwt = new YamlWriter(new FileWriter(urlYAML));
			ymwt.write(configs);
			ymwt.close();
		} catch (IOException | YamlException e) {
			e.printStackTrace();
		}
	}
	private static Map<String, Object> makeMaladie(Maladie maladie) {
		Map<String, Object> maladieConfigs = new HashMap<String, Object>();
		maladieConfigs.put("nom", maladie.getNom());
		maladieConfigs.put("niveauDeSoinDeBase", maladie.getNiveauDeSoinDeBase());
		maladieConfigs.put("tauxLetalite", maladie.getTauxLetalite());
		maladieConfigs.put("parametreVirulence", maladie.getParametreVirulenceBase());
		return maladieConfigs;
	}
	private static ArrayList<Object> makeListeTechnologies(ArrayList<Technologie> listeTechnologies){
		ArrayList<Object> listeTechnologiesConfigs = new ArrayList<Object>();
		for (int i = 0; i < listeTechnologies.size(); i++){
			listeTechnologiesConfigs.add(makeTechnologie(listeTechnologies.get(i)));
		}
		return listeTechnologiesConfigs;
	}
	
	private static Map<String, Object> makeTechnologie(Technologie technologie) {
		
		Map<String, Object> technologiesConfigs = new HashMap<String, Object>();
		technologiesConfigs.put("typeDeRecherche", technologie.getTypeDeRecherche());		
		technologiesConfigs.put("nom", technologie.getNom());
		technologiesConfigs.put("avancementDeLaRecherche", technologie.getAvancementDeLaRecherche());
		technologiesConfigs.put("coutDeLaRecherche", technologie.getCoutDeLaRecherche());
		technologiesConfigs.put("modificateurEfficaciteMedecins", technologie.getModificateurEfficaciteMedecins());
		technologiesConfigs.put("modificateurEfficaciteMilitaires", technologie.getModificateurEfficaciteMilitaires());
		technologiesConfigs.put("modificateurMilitairesPourQuarantaine", technologie.getModificateurMilitairesPourQuarantaine());
		technologiesConfigs.put("modificateurVaccinsFabriqueParMedecin", technologie.getModificateurVaccinsFabriqueParMedecins());
		
		return technologiesConfigs;
	}

	public static Map<String, Object>[] makeVille(Metropole metropole){
		@SuppressWarnings("unchecked")
		Map<String, Object>[] villeConfigs = new Map[metropole.ville.length];
		for(int i=0; i < metropole.ville.length; i++){
			Map<String, Object> quartierConfigs = new HashMap<String, Object>();
			
			Quartier quartier = metropole.ville[i];
			quartierConfigs.put("nom", quartier.getNom());
			quartierConfigs.put("populationInitiale", quartier.getPopulationActuelle());
			quartierConfigs.put("sains", quartier.getSains());
			quartierConfigs.put("immunises", quartier.getImmunises());
			quartierConfigs.put("malades", quartier.getMalades());
			quartierConfigs.put("morts", quartier.getMorts());
			quartierConfigs.put("medecins", quartier.getMedecins());
			quartierConfigs.put("militaires", quartier.getMilitaires());
			quartierConfigs.put("tauxMobilite", quartier.getTauxExode());
			quartierConfigs.put("quarantaine", quartier.isQuarantaineActivee());
			
			villeConfigs[i] = quartierConfigs;
		}
		return villeConfigs;
	}
	public static Map<String, Object> makeMetropole(Metropole metropole){
		Map<String, Object> metropoleConfigs = new HashMap<String, Object>();
		Map<String, Object>[] villeConfigs = makeVille(metropole);
		metropoleConfigs.put("ville", villeConfigs);
		metropoleConfigs.put("medecinsDisponibles", metropole.getMedecinsDisponibles());
		metropoleConfigs.put("militairesDisponibles", metropole.getMilitairesDisponibles());
		metropoleConfigs.put("vaccins", metropole.getVaccins());		
		return metropoleConfigs;
	}
}

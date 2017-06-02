package utils.YAML;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;


public class LectureYAML {
	
	//fonction principale pour lire des fichiers YAML
	public static Object getConfigs(String urlYAML){
		try {
			YamlReader ymrd = new YamlReader(new FileReader(urlYAML));
			Object o = ymrd.read();
			ymrd.close();
			return o;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (YamlException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//pour les listes des sauvegardes et des nouvelles parties
	public static LinkedList<String> getListeNoms(Map<String, String> configs){
		LinkedList<String> listeNoms = new LinkedList<>();
		for (Map.Entry<String, String> entry : configs.entrySet()) {
			listeNoms.addLast(entry.getKey());
		}
		return listeNoms;
	}

	//Tout ce qui suit sert au chargement de nouvelles parties/de parties sauvegardees


	@SuppressWarnings("rawtypes")
	public static String geturlCarte(Map configs){
		return (String) configs.get("urlCarte");
	}
	@SuppressWarnings("rawtypes")
	public static String getNom(Map configs){
		return (String) ((Map) configs.get("Metropole")).get("nom");
	}
	/*
	@SuppressWarnings("rawtypes")
	public static int getMedecinsDisponibles(Map configs){
		return Integer.parseInt((String)((Map) configs.get("Metropole")).get("medecinsDisponibles"));
	}
	@SuppressWarnings("rawtypes")
	public static int getMilitairesDisponibles(Map configs){
		return Integer.parseInt((String)((Map) configs.get("Metropole")).get("militairesDisponibles"));
	}
	@SuppressWarnings("rawtypes")
	public static int getVaccins(Map configs){
		return Integer.parseInt((String)((Map) configs.get("Metropole")).get("vaccins"));
	}

	
	@SuppressWarnings("rawtypes")
	public static LinkedList<String> getListeNomQuartier(Map configs){
		LinkedList<String> listeNomQuartier = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			listeNomQuartier.addLast((String) ((Map) it.next()).get("nom"));
		}
		return listeNomQuartier;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListePopulationInitiale(Map configs){
		LinkedList<Integer> listePopulationInitiale = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer populationInitiale = Integer.parseInt((String)((Map) it.next()).get("populationInitiale"));
			listePopulationInitiale.addLast(populationInitiale);
		}
		return listePopulationInitiale;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListeSains(Map configs){
		LinkedList<Integer> listeSains = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer sains = Integer.parseInt((String)((Map) it.next()).get("sains"));
			listeSains.addLast(sains);
		}
		return listeSains;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListeMalades(Map configs){
		LinkedList<Integer> listeMalades = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer malades = Integer.parseInt((String)((Map) it.next()).get("malades"));
			listeMalades.addLast(malades);
		}
		return listeMalades;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListeMorts(Map configs){
		LinkedList<Integer> listeMorts = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer morts = Integer.parseInt((String)((Map) it.next()).get("morts"));
			listeMorts.addLast(morts);
		}
		return listeMorts;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListeImmunises(Map configs){
		LinkedList<Integer> listeImmunises = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer immunises = Integer.parseInt((String)((Map) it.next()).get("immunises"));
			listeImmunises.addLast(immunises);
		}
		return listeImmunises;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListeMedecins(Map configs){
		LinkedList<Integer> listeMedecins = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer medecins = Integer.parseInt((String)((Map) it.next()).get("medecins"));
			listeMedecins.addLast(medecins);
		}
		return listeMedecins;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Integer> getListeMilitaires(Map configs){
		LinkedList<Integer> listeMilitaires = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Integer militaires = Integer.parseInt((String)((Map) it.next()).get("militaires"));
			listeMilitaires.addLast(militaires);
		}
		return listeMilitaires;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Double> getListeTauxMobilite(Map configs){
		LinkedList<Double> listeTauxMobilite = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Double tauxMobilite = Double.parseDouble((String) ((Map) it.next()).get("tauxMobilite"));
			listeTauxMobilite.addLast(tauxMobilite);
		}
		return listeTauxMobilite;
	}
	@SuppressWarnings("rawtypes")
	public static LinkedList<Boolean> getListeQuarantaines(Map configs){
		LinkedList<Boolean> listeQuarantaines = new LinkedList<>();
		Map ville = (Map) configs.get("Metropole");
		ArrayList listeQuartiers = (ArrayList) ville.get("ville");
		ListIterator it = listeQuartiers.listIterator(0);
		while(it.hasNext()){
			Boolean quarantaine = Boolean.parseBoolean((String)((Map) it.next()).get("quarantaine"));
			listeQuarantaines.addLast(quarantaine);
		}
		return listeQuarantaines;
	}
	
	*/
	
	
/*	
	public static void main(String[] args){
		String url = "C:/Users/user/Desktop/testYaml.yaml";
		String urlBis = "C:/Users/user/Desktop/testYamlBis.yaml";
		String urlTer = "fichiersYAML/modeles/MaladiesDisponibles.yaml";
		String urlQua = "fichiersYAML/modeles/MessagesAleatoires.yaml";
		Map configs = (Map) LectureYAML.getConfigs(url);
		System.out.println(configs);
		Metropole metropole = new Metropole(url);
		Quartier quartier = metropole.ville[0];
		//EcritureYAML.makeConfigs(urlBis, metropole);
		//Map maladies = (Map) getConfigs(Pandemia.URL_MALADIES);
		//Maladie peste = new Maladie((Map) maladies.get("Peste"));
		Map ville = (Map) configs.get("Metropole");
		ArrayList liste = (ArrayList) ville.get("ville");
		ListIterator it = liste.listIterator(0);
		while(it.hasNext()){
			String nomQuartier = (String) ((Map) it.next()).get("nom");
			System.out.println(nomQuartier);
		};
		
		//System.out.println(maladies);
		//System.out.println(peste.isMonoSouche());
		//System.out.println(((Map) liste.get(0)).get("populationInitiale"));
	}
*/
}

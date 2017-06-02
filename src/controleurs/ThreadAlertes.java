package controleurs;

import java.util.ArrayList;

import ihm.panneau.BandeauAlertes;
import utils.YAML.LectureYAML;

public class ThreadAlertes extends Thread {

	final static long UNITE_DE_TEMPS_MESSAGES = 8000;
		
	Pandemia pandemia;
	String pseudo;
	BandeauAlertes bda;
	
	public ThreadAlertes(Pandemia pandemia) {
		this.pandemia = pandemia;
		pseudo = pandemia.pseudo;
		bda = pandemia.ihm.getPanneauDeJeu().bda;
	}
	
	@SuppressWarnings("unchecked")
	ArrayList<String> listeAlertes = (ArrayList<String>) LectureYAML.getConfigs(Pandemia.URL_MESSAGES);
	int longueurListe = listeAlertes.size();
	
	public void run(){
		while(!pandemia.partieTerminee){
			try{
				sleep(UNITE_DE_TEMPS_MESSAGES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int indice = (int) (Math.random() * longueurListe);
			String alerte = listeAlertes.get(indice).replace("<nom>", pseudo);
			bda.addAlerte(alerte);
		}
	}
}

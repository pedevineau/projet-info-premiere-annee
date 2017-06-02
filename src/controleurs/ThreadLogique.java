package controleurs;

import modeles.Technologie;

public class ThreadLogique extends Thread {

	final static long UNITE_DE_TEMPS_TOUR = 1000;

	Pandemia pandemia;

	public ThreadLogique(Pandemia pandemia) {
		this.pandemia = pandemia;
	}

	public void run() {
		int compteur = 0;
		while (!pandemia.partieTerminee) {
			pandemia.metropole.actualiserEpidemie(pandemia.maladie);
			pandemia.metropole.exode();
			Technologie.actualiseRechercheTechnologique(pandemia.metropole.getMedecinsDisponibles(),
					pandemia.metropole.getMilitairesDisponibles());
			pandemia.ihm.getFenetreArbreTechnologie().actualisePanneauArbreTechnologique();
			pandemia.actualiseAlertes();
			pandemia.ihm.getPanneauDeJeu().actualisePanneauDeJeu();
			pandemia.actualisePartieTerminee();

			System.out.println("compteur tour " + compteur++);
			try {
				sleep(UNITE_DE_TEMPS_TOUR);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

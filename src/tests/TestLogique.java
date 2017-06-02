package tests;
import java.util.ArrayList;

import modeles.Quartier;
import utils.DateUtils;

public class TestLogique {
	
	static ArrayList<String> alertes = new ArrayList<String>();
	
	public static boolean isInAlertes(String alerte){
		for(String x:alertes){
			//les 8 premiers caractères d'une alerte correspondent à l'heure (cf ci-dessus)
			System.out.println(x.substring(8)+"/" + alerte + "/ " + (x.substring(8).equals(alerte)));
			if(x.substring(8) == alerte){
				return true;
			}
		}
		return false;
	}	
	
	public static void addAlerte(String alerte) {
		// La string est ajoutée au début de l' ArrayList ( à l'index 0)
		// On ajoute l'heure actuelle au début de la string
		alertes.add(0, "[" + DateUtils.getHHmm() + "] " + alerte);
	}
	
	public static void main(String[] args) {

		//Maladie peste = new Maladie("peste", 0,true, true, 0.2, 1.2,0,1000);
		Quartier newAtlas = new Quartier("New Atlas", 100000, 0);
		//Quartier platonTown = new Quartier("Platon Town", 10000, 0);
		//Quartier[] ville = { newAtlas, platonTown };
		//Metropole atlantide = new Metropole("Atlantide", ville, 10, 100);
		
		addAlerte("toto1");
		addAlerte("toto2");
		addAlerte("toto3");
		
		System.out.println("2"+ isInAlertes("toto2"));
		System.out.println("4" +isInAlertes("toto4"));
		
		// System.out.println("medecinstotal " + atlantide.getMedecinsTotal()
		// +"\n medecins atlas " + newAtlas.getMedecins() +"\n med platon " +
		// platonTown.getMedecins());

		newAtlas.setSains(newAtlas.getSains() - 100);
		newAtlas.setMalades(100);
		
		// peste.setVaccinTrouve(true);
		/*
		 * for (int j=0; j<10; j++){
		 * atlantide.actualiserEpidemie(peste);
		 * System.out.println("malades " + newAtlas.getMalades() + "\n morts " +
		 * newAtlas.getMorts() + "\n immunises " + newAtlas.getImmunises()); }
		 * atlantide.depecherMedecins(newAtlas,
		 * atlantide.getMedecinsDisponibles());
		 * atlantide.depecherMilitaires(newAtlas,
		 * atlantide.getMilitairesDisponibles());
		 * atlantide.rappelerMedecins(platonTown, platonTown.getMedecins());
		 * System.out.println("medecins en force.\n dispo " +
		 * atlantide.getMedecinsDisponibles() +"\n newAtlas" +
		 * newAtlas.getMedecins()); System.out.println("militaires total "
		 * + atlantide.getMilitairesDisponibles()); System.out.println(
		 * "militaires atlas " +newAtlas.getMilitaires());
		 */
	/*	for (int j = 0; j < 1000; j++) {
			atlantide.actualiserEpidemie(peste);
			System.out.println("i=" + j + ": "
					+ "sains = " + newAtlas.getSains() + " / "
					+ "malades = " + newAtlas.getMalades() + " / "
					+ "morts = " + newAtlas.getMorts() + " / "
					+ "immunises = " + newAtlas.getImmunises());
		}
*/
	}

}

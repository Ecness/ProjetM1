package model.carte.stellaire;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.batiment.EnumBatiment;
import model.parametre.EnumAbondanceRessource;

public class Planete {
	private EnumTypePlanete typePlanete;
	private Map<EnumRessource, Integer> TRessource;
	private EnumBatiment[] TBatiment;
	private Ville ville;
	
	public Planete(EnumTypePlanete typePlanete, EnumAbondanceRessource ressource) {
		this.typePlanete = typePlanete;
		this.TRessource = new HashMap<EnumRessource, Integer>();
		generationPlanete(ressource);
		TBatiment = new EnumBatiment[2];
		this.ville = null;
	}
	
	/*
	 * Génére les ressorce de base de la planete.
	 * 5 ressource max, moin il y as de ressource plus les bonus sont grand.
	 * 
	 */
	private void generationPlanete(EnumAbondanceRessource ressource) {
		
		
		//donne le nombre de ressource sur la planete
		int nbRessource = (int) (5*Math.random());
		EnumRessource choix;
		
		switch (nbRessource) {
		case 4:
			//pour chaque ressource
			for(int i=0;i<4;i++) {
				
				choix = EnumRessource.renvoit();
				//l'ajoute au tableau
				if(this.TRessource.get(choix)!=null) {
					this.TRessource.put(choix,  Math.abs((int) (2*Math.random()+1+this.TRessource.get(choix)+ressource.getmodificateur())));
				}
				else {
					this.TRessource.put(choix,  Math.abs((int) (2*Math.random()+1+ressource.getmodificateur())));
				}
			}
			break;
		case 3:
			//pour chaque ressource
			for(int i=0;i<3;i++) {
				
				choix = EnumRessource.renvoit();
				//l'ajoute au tableau
				if(this.TRessource.get(choix)!=null) {
					this.TRessource.put(choix,  Math.abs((int) (3*Math.random()+1+this.TRessource.get(choix)+ressource.getmodificateur())));
				}
				else {
					this.TRessource.put(choix,  Math.abs((int) (3*Math.random()+1+ressource.getmodificateur())));
				}
			}
			break;
		case 2:
			//pour chaque ressource
			for(int i=0;i<2;i++) {
				
				choix = EnumRessource.renvoit();
				//l'ajoute au tableau
				if(this.TRessource.get(choix)!=null) {
					this.TRessource.put(choix,  Math.abs((int) (5*Math.random()+3+this.TRessource.get(choix)+ressource.getmodificateur())));
				}
				else {
					this.TRessource.put(choix,  Math.abs((int) (5*Math.random()+3+ressource.getmodificateur())));
				}
			}
			break;
		case 1:
			
			//l'ajoute au tableau
			this.TRessource.put(EnumRessource.renvoit(),  Math.abs((int) (8*Math.random()+5+ressource.getmodificateur())));
			break;
		
		default:
			break;
		}	
	}
	
	
	
	
	
	public EnumTypePlanete getTypePlanete() {
		return typePlanete;
	}
	public void setTypePlanete(EnumTypePlanete typePlanete) {
		this.typePlanete = typePlanete;
	}
	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}
	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}
	public EnumBatiment[] getTBatiment() {
		return TBatiment;
	}
	public void setTBatiment(EnumBatiment[] tBatiment) {
		TBatiment = tBatiment;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
}

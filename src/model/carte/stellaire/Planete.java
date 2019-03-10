package model.carte.stellaire;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.entity.player.Joueur;
import model.parametre.EnumAbondanceRessource;

public class Planete {
	private int id;
	private EnumTypePlanete typePlanete;
	private Map<EnumRessource, Integer> TRessource;
	private BatimentPlanete[] TBatiment;
	private Ville ville;
	private Joueur joueur;
	private boolean reDraw;
	
	public Planete(EnumTypePlanete typePlanete, EnumAbondanceRessource ressource, GenerationRessourceEtAnomalie ressourcePlanete, int id) {
		this.typePlanete = typePlanete;
		this.TRessource = new HashMap<EnumRessource, Integer>();
		for (EnumRessource t : EnumRessource.values()) {
			TRessource.put(t, 0);
		}
		generationPlanete(ressource,ressourcePlanete);
		TBatiment = new BatimentPlanete[2];
		this.ville = null;
		this.joueur = null;
	}
	
	/**
	 * Vérifie si un emplacement de bâtiment est vide ou non
	 * 
	 * @param emplacement	Emplacement du bâtiment
	 * @return	Vrai si un bâtiment est construit, Faux sinon
	 */
	public boolean presenceBatiment(int emplacement) {
		return TBatiment[emplacement] != null;
	}
	
	/**
	 * Vérifie si un bâtiment peut être construit
	 * 
	 * @param batiment		Bâtiment à construire
	 * @param emplacement	Emplacement du bâtiment
	 * @return
	 */
	public boolean verifConstructionBatiment(BatimentPlanete batiment, int emplacement) {
		if (presenceBatiment(emplacement)) {
			return false;
		}
		
		//Vérification des ressources disponibles
		for (EnumRessource e : EnumRessource.values()) {
			if(joueur.getTRessource().get(e) < batiment.getCout().get(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Détruit le bâtiment indiqué
	 * 
	 * @param emplacement	Emplacement du bâtiment
	 * @return	Vrai si le bâtiment a été détruit, Faux si il n'y a aucun bâtiment
	 */
	public boolean deconstructionBatiment(int emplacement) {
		//Si bâtiment présent à l'emplacement indiqué
		if (presenceBatiment(emplacement)) {
			//Récupération de la moitié des ressources et retrait des bonus du bâtiment
			for (EnumRessource e : EnumRessource.values()) {
				if (e == EnumRessource.SCIENCE || e == EnumRessource.PRODUCTION) {
					this.joueur.getTRessource().put(e, (this.joueur.getTRessource().get(e) - (int) (TBatiment[emplacement].getBonus().get(e))));
				} else {
					this.joueur.getTRessource().put(e, (this.joueur.getTRessource().get(e) + (int) (TBatiment[emplacement].getCout().get(e) / 2)));
				}
				TRessource.put(e, TRessource.get(e) - TBatiment[emplacement].getBonus().get(e));
			}
			
			TBatiment[emplacement] = null;
			
			return true;
		}			
		
		return false;
	}
	
	public boolean constructionBatiment(BatimentPlanete batiment, int emplacement) {
		//Si pas de bâtiment ni de ville et suffisamment de ressources
		if (verifConstructionBatiment(batiment, emplacement) && ville == null) {

			TBatiment[emplacement] = batiment;
			//Retrait des ressources et ajout des bonus du bâtiment
			for (EnumRessource e : EnumRessource.values()) {
				joueur.getTRessource().put(e, joueur.getTRessource().get(e) - batiment.getCout().get(e));
				joueur.getTRessource().put(e, joueur.getTRessource().get(e) + batiment.getBonus().get(e));
				TRessource.put(e, TRessource.get(e) + TBatiment[emplacement].getBonus().get(e));
			}

			return true;
		}

		return false;
	}
	
	/*
	 * Génére les ressorce de base de la planete.
	 * 5 ressource max, moin il y as de ressource plus les bonus sont grand.
	 * 
	 */
	private void generationPlanete(EnumAbondanceRessource ressource,GenerationRessourceEtAnomalie ressourceEtAnomalie) {
		
		//donne le nombre de ressource sur la planete
		int nbRessource = (int) (4*Math.random()+1);
		EnumRessource choix;
		
		switch (nbRessource) {
		case 4:
			//pour chaque ressource
			for(int i=0;i<4;i++) {
				
				choix = ressourceEtAnomalie.generationRessourcePlanete(typePlanete);
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
				
				choix = ressourceEtAnomalie.generationRessourcePlanete(typePlanete);
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
				
				choix = ressourceEtAnomalie.generationRessourcePlanete(typePlanete);
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
			this.TRessource.put( ressourceEtAnomalie.generationRessourcePlanete(typePlanete),  Math.abs((int) (8*Math.random()+5+ressource.getmodificateur())));
			break;
		
		default:
			break;
		}	
	}
	
	public int getId() {
		return id;
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
	public BatimentPlanete[] getTBatiment() {
		return TBatiment;
	}
	public BatimentPlanete getTBatimentNum(int num) {
		return TBatiment[num];
	}
	public void setTBatiment(BatimentPlanete[] tBatiment) {
		TBatiment = tBatiment;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public boolean isReDraw() {
		return reDraw;
	}

	public void setReDraw(boolean reDraw) {
		this.reDraw = reDraw;
	}
	
}

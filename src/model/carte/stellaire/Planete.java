package model.carte.stellaire;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.ListBatiment;
import model.entity.player.Joueur;
import model.parametre.EnumAbondanceRessource;
import model.util.MapRessource;

public class Planete {
	private int id;
	private EnumTypePlanete typePlanete;
	private MapRessource TRessource;
	private BatimentPlanete[] TBatiment;
	private Ville ville;
	private Joueur joueur;
	private Systeme systeme;
	private ListBatiment buildings;
	private boolean reDraw, reDrawBuild1, reDrawBuild2;
	
	public Planete(Systeme systeme, EnumTypePlanete typePlanete, EnumAbondanceRessource ressource, GenerationRessourceEtAnomalie ressourcePlanete, int id) {
		this.id = id;
		this.systeme = systeme;
		this.typePlanete = typePlanete;
		this.TRessource = new MapRessource();
		for (EnumRessource t : EnumRessource.values()) {
			TRessource.put(t, 0);
		}
		generationPlanete(ressource,ressourcePlanete);
		TBatiment = new BatimentPlanete[2];
		this.ville = null;
		this.joueur = null;
	}
	
	
	public Planete(Systeme systeme, EnumTypePlanete typePlanete, EnumAbondanceRessource ressource, int id, Joueur joueur) {
		this.id = id;
		this.systeme = systeme;
		this.typePlanete = typePlanete;
		this.TRessource = new MapRessource();
		for (EnumRessource t : EnumRessource.values()) {
			if (!t.equals(EnumRessource.PUISSANCE)) {
				TRessource.put(t, 2+ressource.getmodificateur());				
			} else {
				TRessource.put(t, 0);
			}
		}
		TBatiment = new BatimentPlanete[2];
		this.ville = null;
		this.joueur = joueur;
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
			
			TBatiment[emplacement].setConstruit(false);
			TBatiment[emplacement] = null;
			limitRessources();
			
			return true;
		}			
		
		return false;
	}
	
	public boolean constructionBatiment(BatimentPlanete batiment, int emplacement) {
		//Si pas de bâtiment ni de ville et suffisamment de ressources
		if (verifConstructionBatiment(batiment, emplacement) && ville == null) {
			batiment.setConstruit(true);
			TBatiment[emplacement] = batiment;
			//Retrait des ressources et ajout des bonus du bâtiment
			for (EnumRessource e : EnumRessource.values()) {
				joueur.getTRessource().put(e, joueur.getTRessource().get(e) - batiment.getCout().get(e));
				if (e == EnumRessource.SCIENCE || e == EnumRessource.PRODUCTION) {
					joueur.getTRessource().put(e, joueur.getTRessource().get(e) + batiment.getBonus().get(e));
				}
				TRessource.put(e, TRessource.get(e) + TBatiment[emplacement].getBonus().get(e));
			}

			limitRessources();
			return true;
		}

		return false;
	}
	
	public void limitRessources() {
		joueur.limitRessources();
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
	
	/**
	 *  Verifie si le joueur a la tech nécessaire
	 * @param batiment	batiment à tester
	 * @return
	 * 					: true si le joueur a la tech necessaire, false sinon
	 */
	public boolean isBuildingUnlocked(BatimentPlanete batiment) {
		return joueur.getTechnology().getScienceBatiment().get(batiment.getTechNecessaire()).isRechercher();
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
	public MapRessource getTRessource() {
		return TRessource;
	}
	public void setTRessource(MapRessource tRessource) {
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

	public Systeme getSysteme() {
		return systeme;
	}

	public ListBatiment getBuildings() {
		return buildings;
	}

	public void setBuildings(ListBatiment buildings) {
		this.buildings = buildings;
	}

	public boolean isReDraw() {
		return reDraw;
	}

	public void setReDraw(boolean reDraw) {
		this.reDraw = reDraw;
	}


	public boolean isReDrawBuild1() {
		return reDrawBuild1;
	}


	public void setReDrawBuild1(boolean reDrawBuild1) {
		this.reDrawBuild1 = reDrawBuild1;
	}


	public boolean isReDrawBuild2() {
		return reDrawBuild2;
	}


	public void setReDrawBuild2(boolean reDrawBuild2) {
		this.reDrawBuild2 = reDrawBuild2;
	}
	
}

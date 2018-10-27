package model.carte.stellaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.entity.player.Joueur;
import model.parametre.EnumAbondanceRessource;

public class Planete {
	private EnumTypePlanete typePlanete;
	private Map<EnumRessource, Integer> TRessource;
	private BatimentPlanete[] TBatiment;
	private Ville ville;
	private Joueur joueur;
	
	public Planete(EnumTypePlanete typePlanete, EnumAbondanceRessource ressource) {
		this.typePlanete = typePlanete;
		this.TRessource = new HashMap<EnumRessource, Integer>();
		generationPlanete(ressource);
		TBatiment = new BatimentPlanete[2];
		this.ville = null;
		this.joueur = null;
	}
	
	public boolean deconstructionBatiment(BatimentPlanete batiment) {
		if(TBatiment[0]==null && TBatiment[1]==null) {
			return false;
		}
		
		for(BatimentPlanete b : TBatiment) {
			if(b != null) {
				if(b.getNom()==batiment.getNom()) {
					for (EnumRessource e : EnumRessource.values()) {
						this.joueur.getTRessource().put(e, (this.joueur.getTRessource().get(e)+(int)(b.getCout().get(e)/2)));
					}
					return true;
				}				
			}
		}
		return false;
	}
	
	public boolean constructionBatiment(BatimentPlanete batiment) {
		
		if(TBatiment[0]!=null && TBatiment[1]!=null) {
			return false;
		}
		
		boolean constructible = true;
		if(joueur.getTechnology().getScience().get(batiment.getTechNecessaire()).isRechercher()==true) {
			
			for (EnumRessource e : EnumRessource.values()) {
				if(joueur.getTRessource().get(e) < batiment.getCout().get(e)) {
					constructible = false;
				}
			}
			
			if(constructible && (TBatiment[0]==null || TBatiment[1]==null)) {
				for(BatimentPlanete b : TBatiment) {
					if(b == null) {
						b = new BatimentPlanete(batiment.getNom(), batiment.getDescription(), batiment.getTechNecessaire(), batiment.getBonus(), batiment.getBonus());
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private EnumRessource choixRessorce() {
	
		List<EnumRessource> choix =new ArrayList<EnumRessource>();
		
		for(EnumRessource e : EnumRessource.values()) {
			if(typePlanete==EnumTypePlanete.GAZEUSE) {
				if(e!=EnumRessource.ACIER && e!=EnumRessource.CRISTAL) {
					choix.add(e);
				}
			}
			else {
				choix.add(e);
			}
		}
		int type = (int) ((choix.size())*Math.random()+1);
		
		for(EnumRessource e : choix) {
			if(type==e.getNumero()) {
				return e;
			}
		}
		return null;
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
				
				choix = choixRessorce();
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
				
				choix =  choixRessorce();
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
				
				choix =  choixRessorce();
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
			this.TRessource.put( choixRessorce(),  Math.abs((int) (8*Math.random()+5+ressource.getmodificateur())));
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
	
}

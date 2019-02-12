<<<<<<< HEAD:src/model/carte/stellaire/Ville.java
package model.carte.stellaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.EnumRessource;
import model.batiment.BatimentVille;
import model.entity.player.Joueur;
import model.entity.vaisseau.Vaisseau;
import model.module.EnumModule;

public class Ville {
	
	private int id;
	private int puissance;
	private int puissanceTotal;
	private Joueur joueur;
	private Map<EnumRessource, Integer> TRessource;
	private List<BatimentVille> TBatimentVille;
	private EnumModule[] module;
	private List<BatimentVille> filleDeConstructionBattiment;
	private List<Vaisseau> filleDeConstructionUniter;
	
	public Ville(Joueur joueur, Planete planete) {
		
		this.puissance = 0;
		this.puissanceTotal = 0;
		this.joueur = joueur;
		this.id=joueur.getTVille().size();
		this.TRessource = new HashMap<EnumRessource, Integer>();
		for (Entry<EnumRessource, Integer> t : planete.getTRessource().entrySet()) {
			TRessource.put(t.getKey(), t.getValue());
		}
		this.TBatimentVille = new ArrayList<BatimentVille>();
		this.module = new EnumModule[5];
		this.filleDeConstructionBattiment = new ArrayList<BatimentVille>();
		this.filleDeConstructionUniter = new ArrayList<Vaisseau>();
	}

	public void regenerationPuissance() {
		if(puissance<puissanceTotal) {
			puissance+=(int)(puissanceTotal/10);
		}
		if(puissance>puissanceTotal) {
			puissance=puissanceTotal;
		}
	}
	
	public boolean constructionBatiment(BatimentVille batiment) {
		
		if(joueur.getTechnology().getScience().get(batiment.getTechNecessaire()).isRechercher()==true) {
			for (BatimentVille b : TBatimentVille) {
				if(b.getNom()==batiment.getNom()) {
					return false;
				}
			}
			filleDeConstructionBattiment.add(new BatimentVille(batiment.getNom(), batiment.getDescription(), 
					batiment.getTechNecessaire(), batiment.getBonus(), batiment.getCout()));
			return true;
		}
		return false;
	}
	
	public boolean testFinConstruction() {
		if(TRessource.get(EnumRessource.PRODUCTION)>0 && !filleDeConstructionBattiment.isEmpty()) {		
			filleDeConstructionBattiment.get(0).setCout(filleDeConstructionBattiment.get(0).getCout()-TRessource.get(EnumRessource.PRODUCTION));
			if(filleDeConstructionBattiment.get(0).getCout()<=0) {
				for(EnumRessource e : EnumRessource.values()) {
					TRessource.put(e, TRessource.get(e)+filleDeConstructionBattiment.get(0).getBonus().get(e));
				}
				TBatimentVille.add(filleDeConstructionBattiment.get(0));
				filleDeConstructionBattiment.remove(0);
				return true;
			}
		}
		return false;
	}

	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}


	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}


	public List<BatimentVille> getTBatimentVille() {
		return TBatimentVille;
	}


	public void setTBatimentVille(List<BatimentVille> tBatimentVille) {
		TBatimentVille = tBatimentVille;
	}


	public int getPuissance() {
		return puissance;
	}


	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}


	public EnumModule[] getModule() {
		return module;
	}
	public EnumModule getModuleNum(int num) {
		return module[num];
	}


	public void setModule(EnumModule[] module) {
		this.module = module;
	}


	public List<BatimentVille> getFilleDeConstructionBattiment() {
		return filleDeConstructionBattiment;
	}


	public void setFilleDeConstructionBattiment(List<BatimentVille> filleDeConstructionBattiment) {
		this.filleDeConstructionBattiment = filleDeConstructionBattiment;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public List<Vaisseau> getFilleDeConstructionUniter() {
		return filleDeConstructionUniter;
	}


	public void setFilleDeConstructionUniter(List<Vaisseau> filleDeConstructionUniter) {
		this.filleDeConstructionUniter = filleDeConstructionUniter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuissanceTotal() {
		return puissanceTotal;
	}

	public void setPuissanceTotal(int puissanceTotal) {
		this.puissanceTotal = puissanceTotal;
	}
	
}
=======
package model.carte.stellaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.EnumRessource;
import model.batiment.BatimentVille;
import model.entity.player.Joueur;
import model.entity.vaisseau.Vaisseau;
import model.module.EnumModule;

public class Ville {
	
	private int id;
	private int puissance;
	private int puissanceTotal;
	private Joueur joueur;
	private Map<EnumRessource, Integer> TRessource;
	private List<BatimentVille> TBatimentVille;
	private EnumModule[] module;
	private List<BatimentVille> filleDeConstructionBattiment;
	private List<Vaisseau> filleDeConstructionUniter;
	
	public Ville(Joueur joueur, Planete planete) {
		
		this.puissance = 0;
		this.puissanceTotal = 0;
		this.joueur = joueur;
		this.id=joueur.getTVille().size();
		this.TRessource = new HashMap<EnumRessource, Integer>();
		for (Entry<EnumRessource, Integer> t : planete.getTRessource().entrySet()) {
			TRessource.put(t.getKey(), t.getValue());
		}
		this.TBatimentVille = new ArrayList<BatimentVille>();
		this.module = new EnumModule[5];
		this.filleDeConstructionBattiment = new ArrayList<BatimentVille>();
		this.filleDeConstructionUniter = new ArrayList<Vaisseau>();
	}

	public void regenerationPuissance() {
		if(puissance<puissanceTotal) {
			puissance+=(int)(puissanceTotal/10);
		}
		if(puissance>puissanceTotal) {
			puissance=puissanceTotal;
		}
	}
	
	public boolean constructionBatiment(BatimentVille batiment) {
		
		if(joueur.getTechnology().getScience().getScience().get(batiment.getTechNecessaire()).isRechercher()==true) {
			for (BatimentVille b : TBatimentVille) {
				if(b.getNom()==batiment.getNom()) {
					return false;
				}
			}
			filleDeConstructionBattiment.add(new BatimentVille(batiment.getNom(), batiment.getDescription(), 
					batiment.getTechNecessaire(), batiment.getBonus(), batiment.getCout()));
			return true;
		}
		return false;
	}
	
	public boolean testFinConstruction() {
		if(TRessource.get(EnumRessource.PRODUCTION)>0 && !filleDeConstructionBattiment.isEmpty()) {		
			filleDeConstructionBattiment.get(0).setCout(filleDeConstructionBattiment.get(0).getCout()-TRessource.get(EnumRessource.PRODUCTION));
			if(filleDeConstructionBattiment.get(0).getCout()<=0) {
				for(EnumRessource e : EnumRessource.values()) {
					TRessource.put(e, TRessource.get(e)+filleDeConstructionBattiment.get(0).getBonus().get(e));
				}
				TBatimentVille.add(filleDeConstructionBattiment.get(0));
				filleDeConstructionBattiment.remove(0);
				return true;
			}
		}
		return false;
	}

	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}


	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}


	public List<BatimentVille> getTBatimentVille() {
		return TBatimentVille;
	}


	public void setTBatimentVille(List<BatimentVille> tBatimentVille) {
		TBatimentVille = tBatimentVille;
	}


	public int getPuissance() {
		return puissance;
	}


	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}


	public EnumModule[] getModule() {
		return module;
	}
	public EnumModule getModuleNum(int num) {
		return module[num];
	}


	public void setModule(EnumModule[] module) {
		this.module = module;
	}


	public List<BatimentVille> getFilleDeConstructionBattiment() {
		return filleDeConstructionBattiment;
	}


	public void setFilleDeConstructionBattiment(List<BatimentVille> filleDeConstructionBattiment) {
		this.filleDeConstructionBattiment = filleDeConstructionBattiment;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public List<Vaisseau> getFilleDeConstructionUniter() {
		return filleDeConstructionUniter;
	}


	public void setFilleDeConstructionUniter(List<Vaisseau> filleDeConstructionUniter) {
		this.filleDeConstructionUniter = filleDeConstructionUniter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuissanceTotal() {
		return puissanceTotal;
	}

	public void setPuissanceTotal(int puissanceTotal) {
		this.puissanceTotal = puissanceTotal;
	}
	
}
>>>>>>> devops:Projet/src/model/carte/stellaire/Ville.java

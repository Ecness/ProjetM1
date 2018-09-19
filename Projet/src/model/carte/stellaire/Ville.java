package model.carte.stellaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.EnumRessource;
import model.batiment.EnumBatimenVille;
import model.entity.player.Joueur;
import model.entity.vaisseau.Vaisseau;
import model.module.EnumModule;

public class Ville {

	
	private Map<EnumRessource, Integer> TRessource;
	private EnumBatimenVille[] TBatimentVille;
	private int puissance;
	private List<EnumModule> module;
	private List<EnumBatimenVille> filleDeConstructionBattiment;
	private Joueur joueur;
	private List<Vaisseau> filleDeConstructionUniter;
	
	
	public Ville(Map<EnumRessource, Integer> tRessource, EnumBatimenVille[] tBatimentVille, int puissance,
			List<EnumModule> module, Joueur joueur) {
		
		// TODO
		
		TRessource = tRessource;
		TBatimentVille = tBatimentVille;
		this.puissance = puissance;
		this.module = module;
		this.filleDeConstructionBattiment = new ArrayList<EnumBatimenVille>();
		this.joueur = joueur;
		this.filleDeConstructionUniter = new ArrayList<Vaisseau>();
		
	}

	
	
	

	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}


	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}


	public EnumBatimenVille[] getTBatimentVille() {
		return TBatimentVille;
	}


	public void setTBatimentVille(EnumBatimenVille[] tBatimentVille) {
		TBatimentVille = tBatimentVille;
	}


	public int getPuissance() {
		return puissance;
	}


	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}


	public List<EnumModule> getModule() {
		return module;
	}


	public void setModule(List<EnumModule> module) {
		this.module = module;
	}


	public List<EnumBatimenVille> getFilleDeConstructionBattiment() {
		return filleDeConstructionBattiment;
	}


	public void setFilleDeConstructionBattiment(List<EnumBatimenVille> filleDeConstructionBattiment) {
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
	
	
	
	
}

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

public class Ville {
	
	private int id;
	private int puissance;
	private int puissanceTotal;
	private Joueur joueur;
	private Map<EnumRessource, Integer> TRessource;
	private List<BatimentVille> TBatimentVille;
	private List<BatimentVille> fileDeConstructionBatiment;
	private List<Vaisseau> fileDeConstructionUnite;
	private boolean constructionTerminee;
	private boolean reDrawBatiments, reDrawFiles;
	
	public Ville(Joueur joueur, Planete planete) {
		
		this.puissance = 0;
		this.puissanceTotal = 0;
		this.joueur = joueur;
		System.out.println(joueur.getTVille());
		if(joueur.getTVille()==null) {
			this.id=0;
		}else {			
			this.id=joueur.getTVille().size();
		}
		this.TRessource = new HashMap<EnumRessource, Integer>();
		for (Entry<EnumRessource, Integer> t : planete.getTRessource().entrySet()) {
			TRessource.put(t.getKey(), t.getValue());
		}
		this.TBatimentVille = new ArrayList<BatimentVille>();
		this.fileDeConstructionBatiment = new ArrayList<BatimentVille>();
		this.fileDeConstructionUnite = new ArrayList<Vaisseau>();
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
		
		if(joueur.getTechnology().getScienceBatiment().get(batiment.getTechNecessaire()).isRechercher()==true) {
			for (BatimentVille b : TBatimentVille) {
				if(b.getNom()==batiment.getNom()) {
					return false;
				}
			}
			fileDeConstructionBatiment.add(new BatimentVille(batiment.getNom(), batiment.getDescription(), 
					batiment.getTechNecessaire(), batiment.getBonus(), batiment.getCout()));
			reDrawFiles = true;
			return true;
		}
		return false;
	}
	
	public boolean testFinConstruction() {
		reDrawFiles = true;
		if(TRessource.get(EnumRessource.PRODUCTION)>0 && !fileDeConstructionBatiment.isEmpty()) {		
			fileDeConstructionBatiment.get(0).setCout(fileDeConstructionBatiment.get(0).getCout()-TRessource.get(EnumRessource.PRODUCTION));
			if(fileDeConstructionBatiment.get(0).getCout()<=0) {
				for(EnumRessource e : EnumRessource.values()) {
					TRessource.put(e, TRessource.get(e)+fileDeConstructionBatiment.get(0).getBonus().get(e));
				}
				TBatimentVille.add(fileDeConstructionBatiment.get(0));
				reDrawBatiments = true;
				fileDeConstructionBatiment.remove(0);
				constructionTerminee = true;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean destructionBatiment(BatimentVille batiment) {
		//Si la ville possède le bâtiment concerné
		if (TBatimentVille.contains(batiment)) {
			TBatimentVille.remove(batiment);
			reDrawBatiments = true;
			return true;
		}
		
		return false;
	}
	
	public boolean annulationBatiment(BatimentVille batiment) {
		if (fileDeConstructionBatiment.contains(batiment)) {
			fileDeConstructionBatiment.remove(batiment);
			reDrawBatiments = true;
			return true;
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

	public List<BatimentVille> getFileDeConstructionBatiment() {
		return fileDeConstructionBatiment;
	}


	public void setFileDeConstructionBatiment(List<BatimentVille> filleDeConstructionBattiment) {
		this.fileDeConstructionBatiment = filleDeConstructionBattiment;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public List<Vaisseau> getFileDeConstructionUnite() {
		return fileDeConstructionUnite;
	}


	public void setFileDeConstructionUnite(List<Vaisseau> filleDeConstructionUniter) {
		this.fileDeConstructionUnite = filleDeConstructionUniter;
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

	public boolean isConstructionTerminee() {
		return constructionTerminee;
	}

	public void setConstructionTerminee(boolean constructionTerminee) {
		this.constructionTerminee = constructionTerminee;
	}

	public boolean isReDrawBatiments() {
		return reDrawBatiments;
	}

	public void setReDrawBatiments(boolean reDrawBatiments) {
		this.reDrawBatiments = reDrawBatiments;
	}

	public boolean isReDrawFiles() {
		return reDrawFiles;
	}

	public void setReDrawFiles(boolean reDrawFiles) {
		this.reDrawFiles = reDrawFiles;
	}
}

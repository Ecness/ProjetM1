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
	private boolean constructionTerminee, constructionAnnulee;
	private boolean reDrawBatiments, reDrawFiles;
	
	public Ville(Joueur joueur, Planete planete) {

		this.puissance = 0;
		this.puissanceTotal = 0;
		this.joueur = joueur;
		if(joueur.getTVille()==null) {
			this.id=0;
		}else {			
			this.id=joueur.getTVille().size();
		}
		this.TRessource = new HashMap<EnumRessource, Integer>();
		for (Entry<EnumRessource, Integer> t : planete.getTRessource().entrySet()) {
			TRessource.put(t.getKey(), t.getValue());
		}
		if (TRessource.get(EnumRessource.PRODUCTION) <= 0) {
			TRessource.put(EnumRessource.PRODUCTION, 1);
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

	//-----------------------------------------------------------------------------------------------------------------------------

	/**
	 *  Verifie si le joueur a la tech nécessaire
	 * @param batiment 
	 * 					: batiment à tester
	 * @return
	 * 					: true si le joueur a la tech necessaire, false sinon
	 */
	public boolean isBatimentDebloquer(BatimentVille batiment) {
		return joueur.getTechnology().getScienceBatiment().get(batiment.getTechNecessaire()).isRechercher();
	}

	/**
	 *  Verifie si le joueur a la tech nécessaire
	 * @param vaisseau 
	 * 					: vaisseau à tester
	 * @return
	 * 					: true si le joueur a la tech necessaire, false sinon
	 */
	public boolean isVaisseauDebloquer(Vaisseau vaisseau) {
		return joueur.getTechnology().getScienceMillitaire().get(vaisseau.getTechNecessaire()).isRechercher();
	}
	
	/**
	 * Verifie si le batiment est déjà construit
	 * @param batiment
	 * 					: Batiment à tester
	 * @return
	 * 					: true si déjà construit, false sinon
	 */
	public boolean presenceBatiment(BatimentVille batiment) {
		for (BatimentVille b : TBatimentVille) {
			if(b.getNom().equals(batiment.getNom())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ajoute un batiment a la file de construction
	 * @param batiment
	 * 					: Batiment à ajouter
	 * @return	
	 * 					: true si ajouter a la file, false sinon;
	 */
	public boolean constructionBatiment(BatimentVille batiment) {

		if(isBatimentDebloquer(batiment) && !presenceBatiment(batiment)) {
			fileDeConstructionBatiment.add(new BatimentVille(batiment.getNom(), batiment.getDescription(), 
					batiment.getTechNecessaire(), batiment.getBonus(), batiment.getCout()));
			reDrawFiles = true;
			return true;
		}
		return false;
	}

	/**
	 * vérifie si le joueur a les ressources pour construire le vaisseau
	 * @param vaisseau
	 * 					: vaisseau à tester
	 * @return true si le joueur a les ressources, false sinon
	 */
	public boolean ressourceDisponible(Vaisseau vaisseau) {
		for (EnumRessource e : EnumRessource.values()) {
			if(e != EnumRessource.PRODUCTION && joueur.getTRessource().get(e) < vaisseau.getCout().get(e)) {
				return false;
			}
		}
		return true; 
	}
	
	/**
	 * Teste la possibiliter de construire un vaisseau
	 * @param vaisseau
	 * 					: vaisseau à construire
	 * @return	true si ajouter a la file, false sinon
	 * @exception clone exception
	 */
	public boolean constructionVaisseau(Vaisseau vaisseau) {

		if(isVaisseauDebloquer(vaisseau) && ressourceDisponible(vaisseau)) {
			try {
				this.fileDeConstructionUnite.add(vaisseau.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * teste si la production de la ville est supérieur a 0
	 * @return
	 * 			: true si supérieur à 0, false sinon
	 */
	public boolean canProduct() {
		return (TRessource.get(EnumRessource.PRODUCTION)>0 ? true : false);
	}

	/**
	 * Ajoute la production de la ville au premier batiment de la file de construction
	 */
	public void reEvalueCoutBatiment() {
		fileDeConstructionBatiment.get(0).setCout(fileDeConstructionBatiment.get(0).getCout()-TRessource.get(EnumRessource.PRODUCTION));
		if(fileDeConstructionBatiment.get(0).getCout()<0) {
			fileDeConstructionBatiment.get(0).setCout(0);
		}
	}

	public void addBonusBatiment(BatimentVille batimentVille) {
		for(EnumRessource e : EnumRessource.values()) {
			TRessource.put(e, TRessource.get(e)+batimentVille.getBonus().get(e));
		}
	}

	/**
	 * teste la fin de la construction d'un batiment
	 * @return
	 * 			: true si un batiment a fini de se construire, false sinon
	 */
	public boolean testFinConstruction() {
		reDrawFiles = true;
		if(TRessource.get(EnumRessource.PRODUCTION)>0 && !fileDeConstructionBatiment.isEmpty()) {		
			fileDeConstructionBatiment.get(0).setCout(fileDeConstructionBatiment.get(0).getCout()-TRessource.get(EnumRessource.PRODUCTION));
			if(fileDeConstructionBatiment.get(0).getCout()<=0) {
				for(EnumRessource e : EnumRessource.values()) {
					TRessource.put(e, TRessource.get(e)+fileDeConstructionBatiment.get(0).getBonus().get(e));
				}
				if (TRessource.get(EnumRessource.PRODUCTION) <= 0) {
					TRessource.put(EnumRessource.PRODUCTION, 1);
				}
				TBatimentVille.add(fileDeConstructionBatiment.get(0));
				fileDeConstructionBatiment.remove(0);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean destructionBatiment(BatimentVille batiment) {
		//Si la ville possède le bâtiment concerné
		if (TBatimentVille.contains(batiment)) {
			TBatimentVille.remove(batiment);
			
			for (EnumRessource ressource : EnumRessource.values()) {
				TRessource.put(ressource, TRessource.get(ressource) - batiment.getBonus().get(ressource));
			}
			
			reDrawBatiments = true;
			return true;
		}
		
		return false;
	}
	
	public boolean annulationBatiment(BatimentVille batiment) {
		if (fileDeConstructionBatiment.contains(batiment)) {
			fileDeConstructionBatiment.remove(batiment);
			reDrawFiles = true;
			return true;
		}
		
		return false;
	}

	

	//-----------------------------------------------------------------------------------------------------------------------------

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

	public boolean isConstructionAnnulee() {
		return constructionAnnulee;
	}

	public void setConstructionAnnulee(boolean constructionAnnulee) {
		this.constructionAnnulee = constructionAnnulee;
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

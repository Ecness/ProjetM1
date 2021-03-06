package model.carte.stellaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import model.EnumRessource;
import model.batiment.BatimentVille;
import model.entity.player.Joueur;
import model.entity.vaisseau.Vaisseau;
import model.util.MapRessource;

public class Ville {

	private int id;
	private int puissance;
	private int puissanceTotal;
	private Joueur joueur;
	private Planete planete;
	private MapRessource TRessource;
	private List<BatimentVille> TBatimentVille;
	private List<BatimentVille> fileDeConstructionBatiment;
	private List<Vaisseau> fileDeConstructionUnite;
	private boolean batimentTermine, batimentAnnule, batimentCommence;
	private boolean vaisseauTermine, vaisseauAnnule, vaisseauCommence;
	private boolean reDraw, reDrawBatiments, reDrawFilesBatiments, reDrawFilesVaisseaux;

	public Ville(Joueur joueur, Planete planete) {

		this.puissance = 50;
		this.puissanceTotal = 50;
		this.joueur = joueur;
		this.planete = planete;
		if(joueur.getTVille()==null) {
			this.id=0;
		}else {			
			this.id=joueur.getTVille().size();
		}
		this.TRessource = new MapRessource();
		for (Entry<EnumRessource, Integer> t : planete.getTRessource().entrySet()) {
			TRessource.put(t.getKey(), t.getValue());
			if(t.getKey().equals(EnumRessource.PUISSANCE)) {
				puissanceTotal+=t.getValue();
				puissance+=t.getValue();
			}
		}
		limitRessources();
		this.TBatimentVille = new ArrayList<BatimentVille>();
		this.fileDeConstructionBatiment = new ArrayList<BatimentVille>();
		this.fileDeConstructionUnite = new ArrayList<Vaisseau>();
	}

	//-----------------------------------------------------------------------------------------------------------------------------

	public void regenerationPuissance() {
		if(puissance<puissanceTotal) {
			puissance+=(int)(puissanceTotal*0.1);
		}
		if(puissance>puissanceTotal) {
			puissance=puissanceTotal;
		}
	}

	/**
	 *  Verifie si le joueur a la tech nécessaire
	 * @param batiment	batiment à tester
	 * @return
	 * 					: true si le joueur a la tech necessaire, false sinon
	 */
	public boolean isBuildingUnlocked(BatimentVille batiment) {
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
	 * Verifie si le batiment est déjà construit ou en construction
	 * @param batiment
	 * 					: Batiment à tester
	 * @return
	 * 					: true si déjà construit ou en construction, false sinon
	 */
	public boolean presenceBatiment(BatimentVille batiment) {
		return batiment.isConstruit() || batiment.isEnConstruction();
	}

	/**
	 * Ajoute un batiment a la file de construction
	 * @param batiment
	 * 					: Batiment à ajouter
	 * @return	
	 * 					: true si ajouter a la file, false sinon;
	 */
	public boolean constructionBatiment(BatimentVille batiment) {

		if(isBuildingUnlocked(batiment) && !presenceBatiment(batiment)) {
			batimentCommence = true;
			batiment.setEnConstruction(true);
			fileDeConstructionBatiment.add(batiment);
			reDrawFilesBatiments = true;
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
				vaisseauCommence = true;
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

	public void testFinConstruction() {
		if (!fileDeConstructionBatiment.isEmpty()) {
			reDrawFilesBatiments = true;
			reDraw = true;
			fileDeConstructionBatiment.get(0).setCout(fileDeConstructionBatiment.get(0).getCout()-TRessource.get(EnumRessource.PRODUCTION));
			if(fileDeConstructionBatiment.get(0).getCout()<=0) {
				batimentTermine = true;
				fileDeConstructionBatiment.get(0).setConstruit(true);
				fileDeConstructionBatiment.get(0).setEnConstruction(false);
				ajoutBonusBatiment(fileDeConstructionBatiment.get(0));
				TBatimentVille.add(fileDeConstructionBatiment.get(0));
				fileDeConstructionBatiment.remove(0);
				reDrawBatiments = true;
			}
		}
		
		if (!fileDeConstructionUnite.isEmpty()) {
			reDrawFilesVaisseaux = true;
			reDraw = true;
			fileDeConstructionUnite.get(0).getCout().put(EnumRessource.PRODUCTION, fileDeConstructionUnite.get(0).getCout().get(EnumRessource.PRODUCTION)-TRessource.get(EnumRessource.PRODUCTION));
			if(fileDeConstructionUnite.get(0).getCout().get(EnumRessource.PRODUCTION) <= 0) {
				fileDeConstructionUnite.get(0).setJoueur(joueur);
				joueur.creationNewFlotte(planete.getSysteme(), fileDeConstructionUnite.get(0));
				fileDeConstructionUnite.remove(0);
				vaisseauTermine = true;
			}
		}

	}

	/**
	 * Ajoute les bonnus d'un batiment à la ville
	 * 
	 * @param batimentVille
	 * 						: Batiment dont les bonus doivent étre ajouter
	 */
	public void ajoutBonusBatiment(BatimentVille batimentVille) {
		for(EnumRessource e : EnumRessource.values()) {
			if (e.equals(EnumRessource.PUISSANCE)) {
				puissanceTotal+=batimentVille.getBonus().get(e);
				puissance+=batimentVille.getBonus().get(e);
			} else {
				TRessource.put(e, TRessource.get(e)+batimentVille.getBonus().get(e));
			}
		}
		limitRessources();
	}

	public boolean destructionBatiment(BatimentVille batimentVille) {
		//Si la ville possède le bâtiment concerné
		if (TBatimentVille.contains(batimentVille)) {
			batimentVille.setConstruit(false);
			batimentVille.setCout(batimentVille.getBaseProdCost());
			TBatimentVille.remove(batimentVille);

			for (EnumRessource ressource : EnumRessource.values()) {
				if (ressource.equals(EnumRessource.PUISSANCE)) {
					puissanceTotal-=batimentVille.getBonus().get(ressource);
					puissance-=batimentVille.getBonus().get(ressource);
				} else {
					TRessource.put(ressource, TRessource.get(ressource)-batimentVille.getBonus().get(ressource));
				}
			}

			limitRessources();

			reDrawBatiments = true;
			return true;
		}

		return false;
	}

	public boolean annulationBatiment(BatimentVille batiment) {
		if (fileDeConstructionBatiment.contains(batiment)) {
			batiment.setEnConstruction(false);
			batiment.setCout(batiment.getBaseProdCost());
			fileDeConstructionBatiment.remove(batiment);
			reDrawFilesBatiments = true;
			return true;
		}

		return false;
	}
	
	public boolean annulationVaisseau(Vaisseau vaisseau) {
		if (fileDeConstructionUnite.contains(vaisseau)) {
			fileDeConstructionUnite.remove(vaisseau);
			reDrawFilesVaisseaux = true;
		}
		
		return false;
	}

	/**
	 * Limitation des ressources
	 */
	public void limitRessources() {
		joueur.limitRessources();

		//Minimum de Production d'une ville à 1
		if (TRessource.get(EnumRessource.PRODUCTION) <= 0) {
			TRessource.put(EnumRessource.PRODUCTION, 1);
		} 
	}

	//-----------------------------------------------------------------------------------------------------------------------------

	public MapRessource getTRessource() {
		return TRessource;
	}


	public void setTRessource(MapRessource tRessource) {
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

	public Planete getPlanete() {
		return planete;
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

	public boolean isBatimentTermine() {
		return batimentTermine;
	}

	public void setBatimentTermine(boolean batimentTermine) {
		this.batimentTermine = batimentTermine;
	}

	public boolean isBatimentAnnule() {
		return batimentAnnule;
	}

	public void setBatimentAnnule(boolean batimentAnnule) {
		this.batimentAnnule = batimentAnnule;
	}

	public boolean isBatimentCommence() {
		return batimentCommence;
	}

	public void setBatimentCommence(boolean batimentCommence) {
		this.batimentCommence = batimentCommence;
	}

	public boolean isVaisseauTermine() {
		return vaisseauTermine;
	}

	public void setVaisseauTermine(boolean vaisseauTermine) {
		this.vaisseauTermine = vaisseauTermine;
	}

	public boolean isVaisseauAnnule() {
		return vaisseauAnnule;
	}

	public void setVaisseauAnnule(boolean vaisseauAnnule) {
		this.vaisseauAnnule = vaisseauAnnule;
	}

	public boolean isVaisseauCommence() {
		return vaisseauCommence;
	}

	public void setVaisseauCommence(boolean vaisseauCommence) {
		this.vaisseauCommence = vaisseauCommence;
	}

	public boolean isReDrawBatiments() {
		return reDrawBatiments;
	}

	public boolean isReDraw() {
		return reDraw;
	}

	public void setReDraw(boolean reDraw) {
		this.reDraw = reDraw;
	}

	public void setReDrawBatiments(boolean reDrawBatiments) {
		this.reDrawBatiments = reDrawBatiments;
	}

	public boolean isReDrawFilesBatiments() {
		return reDrawFilesBatiments;
	}

	public void setReDrawFilesBatiments(boolean reDrawFilesBatiments) {
		this.reDrawFilesBatiments = reDrawFilesBatiments;
	}

	public boolean isReDrawFilesVaisseaux() {
		return reDrawFilesVaisseaux;
	}

	public void setReDrawFilesVaisseaux(boolean reDrawFilesVaisseaux) {
		this.reDrawFilesVaisseaux = reDrawFilesVaisseaux;
	}
}

package model.carte.stellaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.BatimentVille;
import model.entity.player.Joueur;
import model.entity.vaisseau.Flotte;
import model.parametre.EnumAbondanceRessource;

public class Systeme {
	private int idSysteme;
	private List<Planete> TPlanete;
	private Joueur joueur;
	/**Nombre de liaison vers d'autres systèmes*/
	private int nbLiens;
	/**Nombre de liens maximum vers d'autres systèmes*/
	private int nbLiensMax;
	/**Liens du système vers d'autres systèmes*/
	private TreeMap<Systeme, Integer> liens;
	/**Position du système*/
	private Position position;
	private List<Anomalie> TAnomalie;
	private List<Flotte> flottes;
	private GenerationRessourceEtAnomalie ressourceEtAnomalie;
	private EnumTypeSysteme typeSysteme;
	
	/**Position du système selon un "pseudo-tableau"[couche][rang] utilisé pour la génération de la carte*/
	class Position {
		/**Utilisé pour la création du graphe des systèmes*/
		private int couche;
		/**Utilisé pour la création du graphe des systèmes*/
		private int rang;
		
		public Position(int couche, int rang) {
			this.couche = couche;
			this.rang = rang;
		}

		public int getCouche() {
			return couche;
		}

		public int getRang() {
			return rang;
		}
		
		public int coucheSuivante() {
			return couche+1;
		}
		
		public int rangSuivant() {
			return rang+1;
		}
		
		public int rangPrecedent() {
			return rang-1;
		}
	}

	public Systeme(EnumAbondanceRessource nbRessource, int maxPlanete, int maxAnomalie, int couche, int rang, int idSysteme) {
		this.idSysteme=idSysteme;
		this.ressourceEtAnomalie=new GenerationRessourceEtAnomalie();
		this.typeSysteme=EnumTypeSysteme.type();
		TPlanete = new ArrayList<Planete>();
		this.joueur = null;
		TAnomalie = new ArrayList<Anomalie>();
		this.flottes = new ArrayList<Flotte>();
		if(typeSysteme!=EnumTypeSysteme.NEBULEUSE && typeSysteme!=EnumTypeSysteme.TROU_NOIR) {
			generationSystem(nbRessource, maxPlanete);
		}
		generationAnomalie(maxAnomalie);
		liens = new TreeMap<Systeme, Integer>(new Comparator<Systeme>() {
			@Override
			public int compare(Systeme o1, Systeme o2) {
				return o1.getCouche() < o2.getCouche() ? -1 :
					(o1.getCouche() > o2.getCouche() ? 1 : 
						(o1.getRang() < o2.getRang() ? -1 : 
							(o1.getRang() > o2.getRang() ? 1 : 0)));
			}
		});
		nbLiensMax = generationNbLiens();
		position = new Position(couche, rang);
		//Force le nombre minimum de liens pour le premier système de chaque couche
		//(empêche la génération des systèmes de s'arrêter avant que le nombre de systèmes requis soit atteint)
		if (rang == 0 && nbLiensMax < 2) {
			nbLiensMax = 2;
		}
	}
	
	public boolean presenceVille() {
		for (Planete planete : TPlanete) {
			if(planete.getVille() != null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presencePlaneteHabitable() {
		for (Planete planete : TPlanete) {
			if(planete.getTypePlanete() != EnumTypePlanete.GAZEUSE) {
				return true;
			}
		}
		return false;
	}
	
	public boolean rechercheAnomalie(int numero, Joueur joueur) {
		
		if(!TAnomalie.get(numero).isDecouverte()) {
			TAnomalie.get(numero).giveBonus(joueur);
			if(TAnomalie.get(numero).getAnomalie()==EnumAnomalie.EPAVE) {				
				TAnomalie.remove(numero);
			}
		}
		return false;
	}
	
	//--------------------------------------
	
	public boolean constructionBatimentPlanete(Joueur joueur, BatimentPlanete batiment, Planete planete) {
		
		if(joueur.getName()==this.joueur.getName()) {
			if(planete.constructionBatiment(batiment)) {				
				planete.setJoueur(joueur);
			}
			return true;
		}
		return false;
	}
	
	public boolean deconstructionBatimentPlanete(Joueur joueur, BatimentPlanete batiment, Planete planete) {
		
		if(joueur.getName()==this.joueur.getName()) {
			if(planete.deconstructionBatiment(batiment)) {				
				if(planete.getTBatimentNum(1)==null && planete.getTBatimentNum(0)==null) {
					planete.setJoueur(null);					
				}
			}
			return true;
		}
		return false;
	}
	
	//--------------------------------------
	
	public boolean constructionBatimentVille(Joueur joueur, BatimentVille batiment, Ville ville) {
		
		if(joueur.getName()==this.joueur.getName() && ville.getJoueur().getName()==this.joueur.getName()) {
			return ville.constructionBatiment(batiment);
		}
		return false;
	}
	
	public boolean testFinConstructionVille(Joueur joueur, Ville ville) {
		
		if(joueur.getName()==this.joueur.getName()&& ville.getJoueur().getName()==this.joueur.getName()) {
			return ville.testFinConstruction();
		}
		return false;
	}
	
	//--------------------------------------
	
	public boolean testPriseDeVille(Joueur joueur, Ville ville, Flotte flotte) {
		
		int i=0;

		if(this.joueur.getName()==joueur.getName()) {
			return false;
		}
		if(ville.getPuissance()>0) {
			attaqueVille(ville, flotte);
		}
		if(ville.getPuissance()==0) {
			while(i<this.joueur.getSysteme().size() && this.joueur.getSysteme().get(i).getIdSysteme()!=this.idSysteme) {
				i++;
			}
			if(i<this.joueur.getSysteme().size()) {
				return false;
			}
			this.joueur.getSysteme().remove(i);
			this.joueur.getTVille().remove(ville.getId());
			joueur.getSysteme().add(this);
			this.setJoueur(joueur);
			ville.setJoueur(joueur);
			ville.setId(joueur.getTVille().size());
			joueur.ajoutVille(ville);
			for(Planete planete : this.TPlanete) {
				if(planete.getJoueur()!=null) {
					planete.setJoueur(joueur);
				}
			}
			ville.setPuissance((int)(ville.getPuissanceTotal()/2));
			ville.setFilleDeConstructionBattiment(null);
			ville.setFilleDeConstructionUniter(null);
			return true;
		} 
		return false;
	}
	
	private void attaqueVille(Ville ville, Flotte flotte) {

		if(flotte.getPuissance()>10*ville.getPuissance()) {
			ville.setPuissance(0);
		}else {
			ville.setPuissance(ville.getPuissance()-(int)(flotte.getPuissance()/10));
		}
		if(ville.getPuissance()<0) {
			ville.setPuissance(0);
		}
	}
	
	public boolean pillage(Planete planete, Joueur joueur) {
		
		if(planete.getJoueur()==null) {
			return false;
		}
		if(planete.getJoueur().getName()==joueur.getName()) {
			return false;
		}
		
		for (BatimentPlanete batiment : planete.getTBatiment()) {
			if(batiment!=null) {
				batiment=null;
				joueur.getTRessource().put(EnumRessource.CREDIT, joueur.getTRessource().get(EnumRessource.CREDIT)+150);
			}
		}
		planete.setJoueur(null);
		return true;
	}
	
	//--------------------------------------
	
	public boolean ajouterVille(Planete planete,Joueur joueur) {
		
		if(this.getJoueur() == null) {
			if(!presenceVille() && presencePlaneteHabitable()) {
				this.joueur=joueur;
				planete.setVille(new Ville(this.joueur,planete));
				planete.setJoueur(this.joueur);
				this.joueur.ajoutVille(planete.getVille());
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public void ajoutPlanete(Planete p) {
		this.TPlanete.add(p);
	}

	private void generationSystem(EnumAbondanceRessource nbRessource,int maxPlanete) {
		double x=0,y=0;
		
		do {
			y = Math.random();
			x = (int)((maxPlanete)*Math.random()+1);			
		}while(y > (1-Math.pow((((2*x)/maxPlanete )-1),2)));
		
		for(int i=0; i<(int)x;i++) {				
			TPlanete.add(new Planete(EnumTypePlanete.type(),nbRessource,this.ressourceEtAnomalie));
		}		
	}
	
	/**
	 * Génération du nombre maximum de systèmes liés
	 * 
	 * @return	Nombre de liens maximum du système vers d'autres systèmes
	 */
	private int generationNbLiens() {
		double x = Math.random();

		return x < 0.15 ? 1 : 
			x < 0.45 ? 2 :
				x < 0.75 ? 3 : 
					x < 0.9 ? 4 : 5;
	}
	
	private void generationAnomalie(int maxAnomalie) {
		
		int nbAnomalie = (int) (maxAnomalie*Math.random());
		
		if(typeSysteme==EnumTypeSysteme.TROU_NOIR) {
			nbAnomalie=1;
		}
		
		for( int i=0; i<nbAnomalie; i++) {
			TAnomalie.add(new Anomalie(ressourceEtAnomalie.generationAnomalieSysteme(typeSysteme)));
		}	
	}
	
	/**Fait le lien avec un autre système (distance aléatoire)*/
	/*public void ajouterLien(Systeme systeme) {
		this.liens.put(systeme, (int) Math.random()*12+1);
	}*/
	
	/**Fait le lien avec un autre système avec une distance prédéfinie (sens unique)*/
	public void ajouterLien(Systeme systeme, int distance) {
		liens.put(systeme, distance);
		nbLiens++;
	}
	
	/**Fait le lien avec un autre système avec une distance prédéfinie (dans les deux sens)*/
	public void faireLien(Systeme systeme, int distance) {
		this.ajouterLien(systeme, distance);
		systeme.ajouterLien(this, distance);
	}
	
	public List<Planete> getTPlanete() {
		return TPlanete;
	}

	public void setTPlanete(List<Planete> tPlanete) {
		TPlanete = tPlanete;
	}

	public int getNbLiensMax() {
		return nbLiensMax;
	}

	public int getNbLiens() {
		return nbLiens;
	}

	public int getCouche() {
		return position.couche;
	}

	public int getRang() {
		return position.rang;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Position getPositionSuivante() {
		return new Position(position.getCouche(), position.getRang()+1);
	}
	
	public Position getPositionPrecedente() {
		return new Position(position.getCouche(), position.getRang()-1);
	}
	
	public Position getDernierLien() {
		return liens.lastKey().position;
	}
	
	public Position getPremierLien() {
		return liens.size() > 1 ? liens.higherKey(liens.firstKey()).position : liens.firstKey().position;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
		for (Planete planete : TPlanete) {
			planete.setJoueur(joueur);
		}
	}

	public List<Anomalie> getTAnomalie() {
		return TAnomalie;
	}

	public void setTAnomalie(List<Anomalie> tAnomalie) {
		TAnomalie = tAnomalie;
	}

	public List<Flotte> getFlottes() {
		return flottes;
	}

	public void setFlottes(List<Flotte> flottes) {
		this.flottes = flottes;
	}
	
	public TreeMap<Systeme, Integer> getLiens() {
		return liens;
	}

	public GenerationRessourceEtAnomalie getRessourceEtAnomalie() {
		return ressourceEtAnomalie;
	}

	public void setRessourceEtAnomalie(GenerationRessourceEtAnomalie ressourceEtAnomalie) {
		this.ressourceEtAnomalie = ressourceEtAnomalie;
	}

	public EnumTypeSysteme getTypeSysteme() {
		return typeSysteme;
	}

	public int getIdSysteme() {
		return idSysteme;
	}

	public void setIdSysteme(int idSysteme) {
		this.idSysteme = idSysteme;
	}

	public void setTypeSysteme(EnumTypeSysteme typeSysteme) {
		this.typeSysteme = typeSysteme;
	}
	
}

package model.carte.stellaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class Systeme {
	private List<Planete> TPlanete;
	private int numJoueur;
	private List<EnumAnomalie> TAnomalie;
	/**Nombre de liaison vers d'autres systèmes*/
	private int nbLiens;
	/**Nombre de liens maximum vers d'autres systèmes*/
	private int nbLiensMax;
	/**Liens du système vers d'autres systèmes*/
	private TreeMap<Systeme, Integer> liens;
	/**Position du système*/
	private Position position;
	
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

	public Systeme(int couche, int rang) {
		TPlanete = new ArrayList<Planete>();
		this.numJoueur = -1;
		TAnomalie = new ArrayList<EnumAnomalie>();
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

	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

	public List<EnumAnomalie> getTAnomalie() {
		return TAnomalie;
	}

	public void setTAnomalie(List<EnumAnomalie> tAnomalie) {
		TAnomalie = tAnomalie;
	}

	public TreeMap<Systeme, Integer> getLiens() {
		return liens;
	}
}

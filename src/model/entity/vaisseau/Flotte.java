package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import model.carte.stellaire.Systeme;
import model.entity.general.General;
import model.entity.player.Joueur;

public class Flotte {

	private String nom;
	private int puissance;
	private List<Vaisseau> TVaisseau;
	private General general;
	private Joueur joueur;
	private int vitesse;

	private Systeme systemeActuel;
	private List<Systeme> trajet;
	private int distance;
	private Vector2 coordonnees;

	public Flotte(Joueur joueur, Systeme systeme) {
		this.nom = "Flotte";
		this.puissance = 0;
		TVaisseau = new ArrayList<Vaisseau>();
		this.general = null;
		this.joueur = joueur;
		this.systemeActuel = systeme;
		this.coordonnees = new Vector2(systeme.getX(), systeme.getY());
		this.trajet = new ArrayList<Systeme>();
	}
	public Flotte(Flotte flotte) {
		this.nom = new String(flotte.getNom());
		this.puissance = flotte.getPuissance();
		TVaisseau = new ArrayList<Vaisseau>();
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			TVaisseau.add(vaisseau);
		}
		this.general = flotte.general;
		this.joueur = flotte.getJoueur();
		this.systemeActuel = flotte.systemeActuel;
		this.coordonnees = flotte.getCoordonnees();
		this.trajet = new ArrayList<Systeme>();
	}

	//------------------------------------------------------------------------------------------------------

	public void addVaisseau(Vaisseau vaisseau) {

		TVaisseau.add(vaisseau);
		puissance += vaisseau.getPuissance();
		if (vitesse == 0 || vitesse > vaisseau.getVitesse()) {
			vitesse = vaisseau.getVitesse();
		}
	}

	public void removeVaisseau(Vaisseau vaisseau) {
		TVaisseau.remove(vaisseau);
		puissance -= vaisseau.getPuissance();
		vitesse = Integer.MAX_VALUE;
		for (Vaisseau composant : TVaisseau) {
			if (vitesse == 0 || composant.getVitesse() < vitesse) {
				vitesse = composant.getVitesse();
			}
		}
	}

	/**
	 * Recherche du plus court chemin entre deux systèmes
	 * 
	 * @param graphe	Liste des systèmes
	 * @param begin		Système de départ
	 * @param end		Système d'arrivée
	 * 
	 * @return	Plus court chemin de begin à end
	 */
	private List<Systeme> pathFinding(List<Systeme> graphe, Vector2 begin, Systeme end) {
		List<Systeme> path = new ArrayList<Systeme>();

		//Recherche des plus courts chemins par l'algorithme de Dijkstra (PCC2)
		double[] distance = new double[graphe.size()];
		Systeme[] predecesseur = new Systeme[graphe.size()];
		for (Systeme sys : graphe) {
			distance[sys.getIdSysteme()] = Double.MAX_VALUE;
			predecesseur[sys.getIdSysteme()] = null;
		}
		Systeme plusProcheSysteme = plusProche(graphe, begin);
		distance[plusProcheSysteme.getIdSysteme()] = 0;

		List<Systeme> marque = new ArrayList<Systeme>();
		List<Systeme> nonMarque = new ArrayList<Systeme>();
		nonMarque.addAll(graphe);

		while (!nonMarque.isEmpty()) {
			Systeme x = plusProche(nonMarque, distance);
			nonMarque.remove(x);
			marque.add(x);
			for (Entry<Systeme, Vector2> sys : x.getLiens().entrySet()) {
				if (distance[sys.getKey().getIdSysteme()] > distance[x.getIdSysteme()] + Vector2.dst(x.getX(), x.getY(), sys.getKey().getX(), sys.getKey().getY())) {
					distance[sys.getKey().getIdSysteme()] = distance[x.getIdSysteme()] + Vector2.dst(x.getX(), x.getY(), sys.getKey().getX(), sys.getKey().getY());
					predecesseur[sys.getKey().getIdSysteme()] = x;
				}
			}
		}

		//Recherche du plus court chemin de end à begin dans le graphe des plus courts chemins (PCC1)
		path.add(end);
		Systeme courant = end;
		do {
			path.add(predecesseur[courant.getIdSysteme()]);
			courant = predecesseur[courant.getIdSysteme()];
		} while (!path.contains(plusProcheSysteme));

		return path;
	}
	//TODO Voir pourquoi la flotte termine son trajet dans le cas d'un changement de destination

	/**
	 * Set le plus court chemin entre deux systèmes
	 * 
	 * @param graphe	Liste des systèmes
	 * @param begin		Système de départ
	 * @param end		Système d'arrivée
	 */
	public void setPath(List<Systeme> graphe, Vector2 begin, Systeme end) {
		trajet.clear();
		//Récupération des systèmes à parcourir dans le bon ordre (système actuel vers système cible)
		List<Systeme> path = pathFinding(graphe, begin, end);
		for (int i = path.size() - 1; i >= 0; i--) {
			trajet.add(path.get(i));
		}
		
		//Retrait de la première étape si la flotte y est déjà
		if (!trajet.isEmpty() && Vector2.dst(trajet.get(0).getX(), trajet.get(0).getY(), coordonnees.x, coordonnees.y) == 0) {
			trajet.remove(0);
		}
	}

	/**
	 * Renvoie le système ayant la distance associée la plus faible parmi une liste de systèmes
	 * 
	 * @param nonMarque	Liste des systèmes
	 * @param distance	Distances associées
	 * @return	Le système ayant la distance associée la plus faible
	 */
	private Systeme plusProche(List<Systeme> nonMarque, double[] distance) {
		double min = Double.MAX_VALUE;
		Systeme plusProche = null;
		for (Systeme sys : nonMarque) {
			if (distance[sys.getIdSysteme()] < min) {
				min = distance[sys.getIdSysteme()];
				plusProche = sys;
			}
		}

		return plusProche;
	}

	/**
	 * Renvoie le système le plus proche d'un point donné
	 * 
	 * @param graphe	Liste des systèmes
	 * @param point		Point donné
	 * @return	Le système le plus proche du point donné
	 */
	private Systeme plusProche(List<Systeme> graphe, Vector2 point) {
		double min = Double.MAX_VALUE;
		Systeme plusProche = null;

		//Parcours de tous les systèmes pour trouver le plus proche
		for (Systeme systeme : graphe) {
			//Si la distance entre le système est le point vaut 0, le système est renvoyé
			if (Vector2.dst(point.x, point.y, systeme.getX(), systeme.getY()) == 0) {
				return systeme;
			} else if (Vector2.dst(point.x, point.y, systeme.getX(), systeme.getY()) < min) {
				//Sinon, on retourne le système le plus proche ayant le point dans ses liens
				for (Systeme lien : systeme.getLiens().keySet()) {
					if (Intersector.distanceSegmentPoint(systeme.getCoordonnees(), lien.getCoordonnees(), point) <= 0.01) {
						plusProche = systeme;
					}
				}
			}
		}

		return plusProche;
	}

	/**
	 * Déplacement de la flotte
	 */
	public void mouvement() {
		if (!trajet.isEmpty()) {
			//Retrait de la flotte du système actuel si elle est dans un système
			if (systemeActuel != null) {
				systemeActuel.getFlottes().remove(this);
			}
			//Si étape atteinte, on indique que la flotte est dans le système
			//Si l'étape est dépassée, on remet la flotte dans le système correspondant
			if (coordonnees.equals(trajet.get(0).getCoordonnees()) || depasse()) {
				systemeActuel = trajet.get(0);
				//Ajout de la flotte dans le nouveau système
				systemeActuel.getFlottes().add(this);
				coordonnees = new Vector2(systemeActuel.getCoordonnees());
			} else {
				//Sinon, on avance vers la prochaine étape
				systemeActuel = null;
				Vector2 vecteur = new Vector2(trajet.get(0).getCoordonnees());
				vecteur.sub(coordonnees);
				vecteur.nor();
				vecteur.scl(vitesse);
				coordonnees.add(vecteur);
			}

			//Mise à jour de la distance restante pour atteindre la prochaine étape
			distance = (int) Vector2.dst(coordonnees.x, coordonnees.y, trajet.get(0).getX(), trajet.get(0).getY());
			if (distance == 0) {
				//Si étape atteinte, on la retire du trajet
				trajet.remove(0);
			}
		}
	}

	/**
	 * Vérifie si un mouvement ne dépasse pas l'étape du trajet
	 * 
	 * @return	True si le mouvement dépasse l'étape, False sinon
	 */
	private boolean depasse() {
		Vector2 base = new Vector2(coordonnees);
		Vector2 vecteur = new Vector2(trajet.get(0).getCoordonnees());
		vecteur.sub(coordonnees);
		vecteur.nor();
		vecteur.scl(vitesse);
		Vector2 resultat = base.add(vecteur);

		return ((coordonnees.x < trajet.get(0).getX() || coordonnees.y < trajet.get(0).getY())
				&& (resultat.x > trajet.get(0).getX() || resultat.y > trajet.get(0).getY()))
				|| ((coordonnees.x > trajet.get(0).getX() || coordonnees.y > trajet.get(0).getY())
						&& (resultat.x < trajet.get(0).getX() || resultat.y < trajet.get(0).getY()));
	}

	@Override
	public String toString() {
		String string="Flotte de puissance :" + puissance + "\n";
		for (Vaisseau vaisseau : TVaisseau) {
			string += vaisseau.toString() + "\n\n";	
		}
		return string;
	}

	//------------------------------------------------------------------------------------------------------

	public int getPuissance() {
		return puissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public List<Vaisseau> getTVaisseau() {
		return TVaisseau;
	}

	public void setTVaisseau(List<Vaisseau> tVaisseau) {
		TVaisseau = tVaisseau;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}

	public List<Systeme> getTrajet() {
		return trajet;
	}
	public void setTrajet(List<Systeme> trajet) {
		this.trajet = trajet;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public Systeme getSystemeActuel() {
		return systemeActuel;
	}
	public void setSystemeActuel(Systeme systemeActuel) {
		this.systemeActuel = systemeActuel;
	}
	public Vector2 getCoordonnees() {
		return coordonnees;
	}
	public void setCoordonnees(Vector2 coordonnees) {
		this.coordonnees = coordonnees;
	}
	public int getVitesse() {
		return vitesse;
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
}

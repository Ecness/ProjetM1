package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.math.Vector2;

import model.carte.stellaire.Systeme;
import model.entity.general.General;

public class Flotte {

	private String nom;
	private int puissance;
	private List<Vaisseau> TVaisseau;
	private General general;
	private List<Systeme> path;
	
	public Flotte() {
		this.nom = "Flotte";
		this.puissance = 0;
		TVaisseau = new ArrayList<Vaisseau>();
		this.general = null;
	}
	public Flotte(Flotte flotte) {
		this.nom = new String(flotte.getNom());
		this.puissance = flotte.getPuissance();
		TVaisseau = new ArrayList<Vaisseau>();
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			//TODO Vérifier si risque de modification par référence avec la fusion des flottes
			TVaisseau.add(vaisseau);
		}
		this.general = flotte.general;
	}
	
	//------------------------------------------------------------------------------------------------------
	
	public void addVaisseau(Vaisseau vaisseau) {
		
		TVaisseau.add(vaisseau);
		puissance += vaisseau.getPuissance();
	}
	
	public void removeVaisseau(Vaisseau vaisseau) {
		TVaisseau.remove(vaisseau);
		puissance -= vaisseau.getPuissance();
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
	public List<Systeme> pathFinding(List<Systeme> graphe, Systeme begin, Systeme end) {
		List<Systeme> path = new ArrayList<Systeme>();
		
		//Recherche des plus courts chemins par l'algorithme de Dijkstra (PCC2)
		double[] distance = new double[graphe.size()];
		Systeme[] predecesseur = new Systeme[graphe.size()];
		for (Systeme sys : graphe) {
			distance[sys.getIdSysteme()] = Double.MAX_VALUE;
			predecesseur[sys.getIdSysteme()] = null;
		}
		distance[begin.getIdSysteme()] = 0;
		
		List<Systeme> marque = new ArrayList<Systeme>();
		List<Systeme> nonMarque = new ArrayList<Systeme>();
		nonMarque.addAll(graphe);
		
		while (!nonMarque.isEmpty()) {
			Systeme x = plusProche(nonMarque, distance);
			nonMarque.remove(x);
			marque.add(x);
			for (Entry<Systeme, Vector2> sys : x.getLiens().entrySet()) {
				if (distance[sys.getKey().getIdSysteme()] > distance[x.getIdSysteme()] + sys.getValue().len()) {
					distance[sys.getKey().getIdSysteme()] = distance[x.getIdSysteme()] + sys.getValue().len();
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
		} while (!path.contains(begin));
		
		return path;
	}
	
	/**
	 * Renvoie le systeme ayant la distance associée la plus faible
	 * 
	 * @param nonMarque	Liste des systèmes
	 * @param distance	Distances associées
	 * @return	Le système ayant la distance associée la plus faible
	 */
	public Systeme plusProche(List<Systeme> nonMarque, double[] distance) {
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
	
	public List<Systeme> getPath() {
		return path;
	}
	public void setPath(List<Systeme> path) {
		this.path = path;
	}
}

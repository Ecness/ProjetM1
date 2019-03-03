package model.carte.stellaire;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import model.parametre.Parametre;

public class Carte2 {
	/**Liste des systèmes*/
	private List<Systeme> listeSysteme;
	/**Système le plus éloigné du premier système par distance euclidienne (Plus Lointain Système)*/
	private Systeme pls;
	
	public Carte2(Parametre parametres) {
		listeSysteme = new ArrayList<Systeme>();

		generationSystemes(parametres);
		liaisonSystemes();
	}

	/**
	 * Génération des systèmes
	 * 
	 * @param tailleSysteme		Nombre de systèmes de la partie
	 */
	private void generationSystemes(Parametre parametre) {
		
		int index = 0, id=0;
		boolean valide = true, exterieur = false;

		//Génération du premier système
		Systeme premierSysteme = new Systeme(parametre.getAbondanceRessource(), parametre.getNbMaxPlanete(), parametre.getNbMaxAnomalie(), id);
		listeSysteme.add(premierSysteme);
		int systemeAGenerer = premierSysteme.getNbLiensMax();
		pls = premierSysteme;

		//Génération des autres systèmes de la carte
		while (listeSysteme.size() < parametre.getTailleCarte().getQuantite()) {
			while (systemeAGenerer > 0) {
				valide = true;
				if (listeSysteme.size() < parametre.getTailleCarte().getQuantite() && listeSysteme.get(index).getNbLiens() < listeSysteme.get(index).getNbLiensMax()) {
					Systeme systeme = new Systeme(parametre.getAbondanceRessource(), parametre.getNbMaxPlanete(), parametre.getNbMaxAnomalie(), ++id);
					listeSysteme.add(systeme);
					if (listeSysteme.get(index).ajouterLienNouveauSysteme(systeme, exterieur) && verifCoordSysteme(systeme) && verifDistanceSysteme(systeme)) {
						if (exterieur) {
							exterieur = false;
						}
						Vector2 baseVector = new Vector2(listeSysteme.get(index).getLiens().get(systeme));
						
						int rotation = 0, multiplicateur = 1;
						valide = verifLiaisonSysteme(listeSysteme.get(index), systeme);
						Vector2 nouveauVecteur = new Vector2(baseVector);
						while (!valide && multiplicateur > 0) {
							//Rotation du vecteur de 10 degrés
							nouveauVecteur.rotate(45);
							//Mise à jour des vecteurs
							listeSysteme.get(index).getLiens().put(systeme, nouveauVecteur);
							Vector2 oppose = new Vector2(nouveauVecteur);
							oppose.rotate(180);
							listeSysteme.get(listeSysteme.size()-1).getLiens().put(listeSysteme.get(index), oppose);
							//Mise à jour des nouvelles coordonnées du système
							systeme.getCoordonnees().set(listeSysteme.get(index).getCoordonnees().getX() + (int) listeSysteme.get(index).getLiens().get(systeme).x, 
									listeSysteme.get(index).getCoordonnees().getY() + (int) listeSysteme.get(index).getLiens().get(systeme).y);
							rotation += 45;
							//Si un tour complet a été fait, la distance est diminuée
							if (rotation == 360) {
								rotation = 0;
								multiplicateur -= 0.1;
								nouveauVecteur.scl(multiplicateur);
								listeSysteme.get(index).getLiens().put(systeme, nouveauVecteur);
								oppose = new Vector2(nouveauVecteur);
								oppose.rotate(180);
								listeSysteme.get(listeSysteme.size()-1).getLiens().put(listeSysteme.get(index), oppose);
								systeme.getCoordonnees().set(listeSysteme.get(index).getCoordonnees().getX() + (int) listeSysteme.get(index).getLiens().get(systeme).x, 
										listeSysteme.get(index).getCoordonnees().getY() + (int) listeSysteme.get(index).getLiens().get(systeme).y);
							}

							valide = verifLiaisonSysteme(listeSysteme.get(index), systeme);
						}

						if (!valide || multiplicateur <= 0) {
							listeSysteme.get(index).getLiens().remove(systeme);
							listeSysteme.get(index).setNbLiens(listeSysteme.get(index).getNbLiens() - 1);
							listeSysteme.remove(systeme);
							id--;
						}
					} else {
						if (listeSysteme.get(index).getLiens().remove(systeme) != null) {
							listeSysteme.get(index).setNbLiens(listeSysteme.get(index).getNbLiens() - 1);
						}
						listeSysteme.remove(systeme);
						id--;
					}
				}
				
				systemeAGenerer--;

				//Calcul du système le plus éloigné du premier système
				if (!listeSysteme.get(0).equals(listeSysteme.get(listeSysteme.size()-1)) && 
						(Vector2.dst2(0, 0, pls.getCoordonnees().getX(), pls.getCoordonnees().getY()) < Vector2.dst2(0, 0, listeSysteme.get(listeSysteme.size()-1).getCoordonnees().getX(), listeSysteme.get(listeSysteme.size()-1).getCoordonnees().getY()))) {
					pls = listeSysteme.get(listeSysteme.size()-1);
				}
			}
			if (systemeAGenerer == 0) {
				//Si on peut continuer à créer des systèmes sans revenir en arrière on le fait, sinon on en crée depuis le pls
				if (index+1 < listeSysteme.size()) {
					index++;
					systemeAGenerer = (int)(Math.random()*listeSysteme.get(index).getNbLiensMax())+1;
				} else {
					index = listeSysteme.indexOf(pls);
					if (pls.getNbLiens() == pls.getNbLiensMax()) {
						pls.setNbLiensMax(pls.getNbLiensMax() + 1);
					}
					systemeAGenerer = pls.getNbLiensMax() - pls.getNbLiens();
					exterieur = true;
				}
			}
		}
	}

	/**Liaison des systèmes pouvant être liés*/
	private void liaisonSystemes() {
		for (Systeme origine : listeSysteme) {
			for (Systeme destination : listeSysteme) {
				if (!origine.equals(destination) && origine.getNbLiens() < origine.getNbLiensMax() && destination.getNbLiens() < destination.getNbLiensMax() 
						&& !origine.getLiens().containsKey(destination) && verifLiaisonSysteme(origine, destination)) {
					origine.getLiens().put(destination, new Vector2(destination.getX() - origine.getX(), destination.getY() - origine.getY()));
					origine.setNbLiens(origine.getNbLiens() + 1);
					destination.getLiens().put(origine, new Vector2(origine.getX() - destination.getX(), origine.getY() - destination.getY()));
					destination.setNbLiens(destination.getNbLiens() + 1);
				}
			}
		}
	}
	
	/**
	 * Vérifie que la liaison entre deux systèmes ne croise aucune autre liaison
	 * 
	 * @param origine		Système à lier
	 * @param destination	Système à lier
	 * @return				Vrai si les systèmes peuvent être liés, Faux sinon
	 */
	private boolean verifLiaisonSysteme(Systeme origine, Systeme destination) {
		Vector2 intersection = new Vector2();
		Vector2 point1 = new Vector2(origine.getCoordonnees().getX(), origine.getCoordonnees().getY());
		Vector2 point2 = new Vector2(destination.getCoordonnees().getX(), destination.getCoordonnees().getY());

		for (int i = 0; i < listeSysteme.size()-1; i++) {
			for (int j = 1; j < listeSysteme.size(); j++) {
				if (listeSysteme.get(i).getLiens().containsKey(listeSysteme.get(j))) {
					Vector2 point3 = new Vector2(listeSysteme.get(i).getCoordonnees().getX(), listeSysteme.get(i).getCoordonnees().getY());
					Vector2 point4 = new Vector2(listeSysteme.get(j).getCoordonnees().getX(), listeSysteme.get(j).getCoordonnees().getY());
					if (Intersector.intersectSegments(point1, point2, point3, point4, intersection) && 
							!intersection.equals(point1) && !intersection.equals(point2) && !intersection.equals(point3) && !intersection.equals(point4)) {
						return false;
					}
				}
			}
		}

		return true;
	}
	
	/**
	 * Vérifie qu'un système n'est pas trop proche d'un autre
	 * 
	 * @param systeme	Le système à vérifier
	 * @return	Vrai si le système est suffisamment distant des autres, False sinon
	 */
	private boolean verifDistanceSysteme(Systeme systeme) {
		for (Systeme sys : listeSysteme) {
			if (!sys.equals(systeme)) {
				Circle circle = new Circle(new Vector2(systeme.getCoordonnees().getX(), systeme.getCoordonnees().getY()), 100);
				if (circle.contains(sys.getCoordonnees().getX(), sys.getCoordonnees().getY())) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Vérifie qu'un système n'a pas les mêmes coordonnées qu'un autre système
	 * 
	 * @param systeme	Le système dont il faut vérifier les coordonnées
	 * @return	Vrai si le système n'est pas confondu avec un autre système, Faux sinon
	 */
	private boolean verifCoordSysteme(Systeme systeme) {
		for (Systeme sys : listeSysteme) {
			if (!sys.equals(systeme) && sys.getCoordonnees().equals(systeme.getCoordonnees()))
				return false;
		}

		return true;
	}

	/**
	 * Affichage console des différents systèmes et de leurs liens
	 */
	public void affichage() {
		for (Systeme sys : listeSysteme) {
			System.out.print(sys.getIdSysteme() + "("  + sys.getCoordonnees() + ")->");
			for (Systeme lien : sys.getLiens().keySet()) {
				System.out.print(lien.getIdSysteme() + "(" + lien.getCoordonnees() +")->");
			}
			System.out.println("NULL");
		}
	}

	/**
	 * @return	Liste des systèmes
	 */
	public List<Systeme> getListeSysteme() {
		return listeSysteme;
	}
}

package model.carte.combat.obstacle;

import java.util.ArrayList;
import java.util.List;

import model.util.Coordonnees;

public class Obstacle {

	private List<Coordonnees> coordonee;
	
	public Obstacle() {
		super();
		coordonee = new ArrayList<Coordonnees>();
	}
	
	public boolean estCompris(int x, int y) {
		
		if((coordonee.get(0).getX()>x && coordonee.get(3).getX()<x) && (coordonee.get(0).getY()>y && coordonee.get(3).getY()<y)) {
			return true;
		}
		return false;
	}
	
	public void addCoordonee(int x, int y) {
		Coordonnees c = new Coordonnees(x, y);
		coordonee.add(c);
	}

	public List<Coordonnees> getCoordonee() {
		return coordonee;
	}

	public void setCoordonee(List<Coordonnees> coordonee) {
		this.coordonee = coordonee;
	}
}

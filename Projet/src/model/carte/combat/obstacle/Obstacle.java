package model.carte.combat.obstacle;

import java.util.ArrayList;
import java.util.List;

import model.carte.combat.Coordonee;

public class Obstacle {

	private List<Coordonee> coordonee;
	
	public Obstacle() {
		super();
		coordonee = new ArrayList<Coordonee>();
	}
	
	public boolean estCompris(int x, int y) {
		
		if((coordonee.get(0).getX()>x && coordonee.get(3).getX()<x) && (coordonee.get(0).getY()>y && coordonee.get(3).getY()<y)) {
			return true;
		}
		return false;
	}
	
	public void addCoordonee(int x, int y) {
		Coordonee c = new Coordonee(x, y);
		coordonee.add(c);
	}

	public List<Coordonee> getCoordonee() {
		return coordonee;
	}

	public void setCoordonee(List<Coordonee> coordonee) {
		this.coordonee = coordonee;
	}
}

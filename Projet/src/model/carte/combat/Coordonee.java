package model.carte.combat;

public class Coordonee {
	
	private int x,y;

	
	public Coordonee() {
		
	}
	public Coordonee(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	

	public String toString() {
		return x + " " + y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

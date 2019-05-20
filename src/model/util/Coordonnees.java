package model.util;

public class Coordonnees {
	
	private int x, y;

	
	public Coordonnees() {
		
	}
	public Coordonnees(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public String toString() {
		return x + ";" + y;
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
	
	public boolean isZero() {
		return x == 0 && y == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return x == ((Coordonnees) obj).getX() && y == ((Coordonnees) obj).getY();
	}
}

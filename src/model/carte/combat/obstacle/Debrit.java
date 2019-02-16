package model.carte.combat.obstacle;

public class Debrit extends Obstacle{
	
	/**
	 * création d'une zone carée de debrit de 200/200
	 * @param coordonee du milieu
	 */
	public Debrit(int x, int y) {
		super();
		this.addCoordonee(x+100, y+100);
		this.addCoordonee(x+100, y-100);
		this.addCoordonee(x-100, y+100);
		this.addCoordonee(x-100, y-100);
	}
	
	
}

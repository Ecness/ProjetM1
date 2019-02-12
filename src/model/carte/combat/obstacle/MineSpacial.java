package model.carte.combat.obstacle;

public class MineSpacial extends Obstacle {

	public MineSpacial(int x, int y) {
		super();
		this.addCoordonee(x+25, y+25);
		this.addCoordonee(x+25, y-25);
		this.addCoordonee(x-25, y+25);
		this.addCoordonee(x-25, y-25);
		
	}
}

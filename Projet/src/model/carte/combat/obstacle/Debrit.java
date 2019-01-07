package model.carte.combat.obstacle;

public class Debrit extends Obstacle{
	
	public Debrit(int x, int y) {
		super();
		double type = Math.random();
		int X,Y;
		
		if(type<0.1) {
			X=200;
			Y=50;
		}else if(type<0.4) {
			X=100;
			Y=37;
		}else {
			X=37;
			Y=37;
		}
		
		if(Math.random()<0.5){
			this.addCoordonee(x+X, y+Y);
			this.addCoordonee(x+X, y-Y);
			this.addCoordonee(x-X, y+Y);
			this.addCoordonee(x-X, y-Y);
		}else {
			this.addCoordonee(x+Y, y+X);
			this.addCoordonee(x+Y, y-X);
			this.addCoordonee(x-Y, y+X);
			this.addCoordonee(x-Y, y-X);
		}
	}
}

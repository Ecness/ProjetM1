package model.carte.combat.obstacle;

public class NuageDeGaz extends Obstacle {

	public NuageDeGaz(int x, int y) {
		super();

		int X = (int)(6*Math.random()+1);
		int Y=(int)(3*Math.random()+1);
		
		if(Math.random()<0.5){
			this.addCoordonee(x+(X*50), y+(Y*50));
			this.addCoordonee(x+(X*50), y-(Y*50));
			this.addCoordonee(x-(X*50), y+(Y*50));
			this.addCoordonee(x-(X*50), y-(Y*50));
		}else {
			this.addCoordonee(x+(Y*50), y+(X*50));
			this.addCoordonee(x+(Y*50), y-(X*50));
			this.addCoordonee(x-(Y*50), y+(X*50));
			this.addCoordonee(x-(Y*50), y-(X*50));
		}
	}
}

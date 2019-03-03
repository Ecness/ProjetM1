package model.entity.vaisseau;

import com.badlogic.gdx.utils.IntMap;

public class ListVaisseaux {

	private IntMap<Corvette> CorvettePirate;
	
	public ListVaisseaux() {
		CorvettePirate = new IntMap<Corvette>();
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------
		public void addCorvettePirate(int id, Corvette vaisseau) {
			CorvettePirate.put(id, vaisseau);
		}
			
	//--------------------------------------------------------------------------------------------------------------------------------------------

		public IntMap<Corvette> getCorvettePirate() {
			return CorvettePirate;
		}
		public void setCorvettePirate(IntMap<Corvette> vaisseauxPirate) {
			this.CorvettePirate = vaisseauxPirate;
		}
}

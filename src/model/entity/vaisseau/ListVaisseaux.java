package model.entity.vaisseau;

import com.badlogic.gdx.utils.IntMap;

public class ListVaisseaux {

	private IntMap<Vaisseau> vaisseaux;

	public ListVaisseaux() {
		vaisseaux = new IntMap<Vaisseau>();
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------
	public void addVaisseau(int id, Vaisseau vaisseau) {
		vaisseaux.put(id, vaisseau);
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------

	public IntMap<Vaisseau> getVaisseaux() {
		return vaisseaux;
	}
	public void setVaisseaux(IntMap<Vaisseau> vaisseaux) {
		this.vaisseaux = vaisseaux;
	}
}

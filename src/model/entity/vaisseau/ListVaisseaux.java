package model.entity.vaisseau;

import com.badlogic.gdx.utils.IntMap;

public class ListVaisseaux {

	private IntMap<Corvette> corvettePirate;
	private IntMap<VaisseauCivil> vaisseauCivil;
	private IntMap<Croiseur> croiseurPirate;

	public ListVaisseaux() {
		corvettePirate = new IntMap<Corvette>();
		vaisseauCivil = new IntMap<VaisseauCivil>();
		croiseurPirate = new IntMap<Croiseur>();
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------
	public void addCorvettePirate(int id, Corvette vaisseau) {
		corvettePirate.put(id, vaisseau);
	}
	public void addVaisseauCivil(int id, VaisseauCivil vaisseau) {
		vaisseauCivil.put(id, vaisseau);
	}
	public void addCroiseurPirate(int id, Croiseur vaisseau) {
		croiseurPirate.put(id, vaisseau);
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------

	public IntMap<Corvette> getCorvettePirate() {
		return corvettePirate;
	}
	public void setCorvettePirate(IntMap<Corvette> vaisseauxPirate) {
		this.corvettePirate = vaisseauxPirate;
	}
	public IntMap<VaisseauCivil> getVaisseauCivil() {
		return vaisseauCivil;
	}
	public void setVaisseauCivil(IntMap<VaisseauCivil> vaisseauCivil) {
		this.vaisseauCivil = vaisseauCivil;
	}
	public IntMap<Croiseur> getCroiseurPirate() {
		return croiseurPirate;
	}
	public void setCroiseurPirate(IntMap<Croiseur> croiseurPirate) {
		this.croiseurPirate = croiseurPirate;
	}
}

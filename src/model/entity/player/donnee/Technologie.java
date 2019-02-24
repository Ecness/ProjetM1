package model.entity.player.donnee;

import com.badlogic.gdx.utils.IntMap;

import model.entity.player.Science;

public class Technologie {
	
	private IntMap<Science> scienceMillitaire;
	private IntMap<Science> scienceBatiment;
	private IntMap<Science> scienceBonus;
	
	public Technologie() {
		this.scienceBatiment = new IntMap<Science>();
		this.scienceBonus = new IntMap<Science>();
		this.scienceMillitaire = new IntMap<Science>();
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
		public void addScienceMillitaire(int id, Science science) {
			scienceMillitaire.put(id, science);
		}
		public void addScienceBonus(int id, Science science) {
			scienceBonus.put(id, science);
		}
		public void addScienceBatiment(int id, Science science) {
			scienceBatiment.put(id, science);
		}
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public IntMap<Science> getScienceMillitaire() {
		return scienceMillitaire;
	}
	public void setScienceMillitaire(IntMap<Science> scienceMillitaire) {
		this.scienceMillitaire = scienceMillitaire;
	}
	public IntMap<Science> getScienceBatiment() {
		return scienceBatiment;
	}
	public void setScienceBatiment(IntMap<Science> scienceBatiment) {
		this.scienceBatiment = scienceBatiment;
	}
	public IntMap<Science> getScienceBonus() {
		return scienceBonus;
	}
	public void setScienceBonus(IntMap<Science> scienceBonus) {
		this.scienceBonus = scienceBonus;
	}
}

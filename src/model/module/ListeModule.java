package model.module;

import com.badlogic.gdx.utils.IntMap;

public class ListeModule {

	private IntMap<Arme> armeLaser;
	private IntMap<Arme> armePlasma;
	private IntMap<Arme> armeCinetique;
	private IntMap<Blindage> blindage;
	private IntMap<Chassie> chassie;
	
	public ListeModule() {
		armeLaser = new IntMap<Arme>();
		armePlasma = new IntMap<Arme>();
		armeCinetique = new IntMap<Arme>();
		blindage = new IntMap<Blindage>();
		chassie = new IntMap<Chassie>();
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public void addArmeLaser(int id, Arme arme) {
		armeLaser.put(id, arme);
	}
	public void addArmePlasma(int id, Arme arme) {
		armePlasma.put(id, arme);
	}
	public void addArmeCinetique(int id, Arme arme) {
		armeCinetique.put(id, arme);
	}
	public void addBlindage(int id, Blindage blindage) {
		this.blindage.put(id, blindage);
	}
	public void addChassie(int id, Chassie chassie) {
		this.chassie.put(id, chassie);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public IntMap<Arme> getArmeLaser() {
		return armeLaser;
	}

	public void setArmeLaser(IntMap<Arme> armeLaser) {
		this.armeLaser = armeLaser;
	}

	public IntMap<Arme> getArmePlasma() {
		return armePlasma;
	}

	public void setArmePlasma(IntMap<Arme> armePlasma) {
		this.armePlasma = armePlasma;
	}

	public IntMap<Arme> getArmeCinetique() {
		return armeCinetique;
	}

	public void setArmeCinetique(IntMap<Arme> armeCinetique) {
		this.armeCinetique = armeCinetique;
	}
}

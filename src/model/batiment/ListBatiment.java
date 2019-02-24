package model.batiment;

import com.badlogic.gdx.utils.IntMap;


public class ListBatiment {

	private IntMap<BatimentPlanete> batimentsPlanete;
	private IntMap<BatimentVille> batimentsVille;
	
	public ListBatiment() {
		this.batimentsPlanete = new IntMap<BatimentPlanete>();
		this.batimentsVille = new IntMap<BatimentVille>();
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public void addBatimentsPlanete(int id, BatimentPlanete batiment) {
		batimentsPlanete.put(id, batiment);
	}
	public void addBatimentsVille(int id, BatimentVille batiment) {
		batimentsVille.put(id, batiment);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public IntMap<BatimentPlanete> getBatimentsPlanete() {
		return batimentsPlanete;
	}
	public void setBatimentsPlanete(IntMap<BatimentPlanete> batimentsPlanete) {
		this.batimentsPlanete = batimentsPlanete;
	}
	public IntMap<BatimentVille> getBatimentsVille() {
		return batimentsVille;
	}
	public void setBatimentsVille(IntMap<BatimentVille> batimentsVille) {
		this.batimentsVille = batimentsVille;
	}
}

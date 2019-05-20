package model.carte.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.carte.combat.obstacle.EnumListObstacle;
import model.carte.stellaire.EnumTypeSysteme;

public class GenerationObstacleMapCombat {

	private Map<EnumTypeSysteme, List<EnumListObstacle>> obstaclePossible;
	
	
	public GenerationObstacleMapCombat() {
		
		obstaclePossible = new HashMap<EnumTypeSysteme, List<EnumListObstacle>>();
		generationObstaclePossible();
	}
	
	private void generationObstaclePossible() {
		
		for (EnumTypeSysteme t : EnumTypeSysteme.values()) {
			
			switch (t) {
			case NEBULEUSE:
				obstaclePossible.put(t, new ArrayList<EnumListObstacle>());
				
				obstaclePossible.get(t).add(EnumListObstacle.ASTEROIDE);
				obstaclePossible.get(t).add(EnumListObstacle.DEBRIT);
				obstaclePossible.get(t).add(EnumListObstacle.DEBRIT);
				obstaclePossible.get(t).add(EnumListObstacle.MINE_SPACIAL);
				
				break;
			
			case TROU_NOIR:
				obstaclePossible.put(t, new ArrayList<EnumListObstacle>());
				
				break;

			default:
				obstaclePossible.put(t, new ArrayList<EnumListObstacle>());
				
				obstaclePossible.get(t).add(EnumListObstacle.ASTEROIDE);
				obstaclePossible.get(t).add(EnumListObstacle.DEBRIT);
				obstaclePossible.get(t).add(EnumListObstacle.DEBRIT);
				obstaclePossible.get(t).add(EnumListObstacle.NUAGE_DE_GAZ);
//				obstaclePossible.get(t).add(EnumListObstacle.CEINTURE_ASTEROIDE);
				obstaclePossible.get(t).add(EnumListObstacle.MINE_SPACIAL);
				obstaclePossible.get(t).add(EnumListObstacle.MINE_SPACIAL);
				
				break;
			}
		}
	}
	
	private EnumListObstacle generationObstaclePossible(EnumTypeSysteme systeme) {
		int obstacle = (int) ((obstaclePossible.get(systeme).size())*Math.random());	
		
		return obstaclePossible.get(systeme).get(obstacle);
	}
	
	public List<EnumListObstacle> generationListObstaclePossible(int nombreObstacle, EnumTypeSysteme systeme){
		
		List<EnumListObstacle> list = new ArrayList<EnumListObstacle>();
		
		if(systeme == EnumTypeSysteme.TROU_NOIR) {
			list.add(EnumListObstacle.FORTE_GRAVITER);
		}else {
			for(int i = 0 ; i < nombreObstacle ; i++) {
				list.add(generationObstaclePossible(systeme));
			}
			if(systeme == EnumTypeSysteme.NEBULEUSE) {
				list.add(EnumListObstacle.NEBULEUSE);
			}
		}
		return list;
	}
}
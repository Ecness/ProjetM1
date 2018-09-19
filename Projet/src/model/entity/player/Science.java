package model.entity.player;

import java.util.HashMap;
import java.util.Map;

public class Science {
	private Map<EnumTech, Boolean> tech;

	public Science() {
		this.tech = new HashMap<EnumTech, Boolean>();
	}

	public Map<EnumTech, Boolean> getTech() {
		return tech;
	}

	public void setTech(Map<EnumTech, Boolean> tech) {
		this.tech = tech;
	}
	
	
}

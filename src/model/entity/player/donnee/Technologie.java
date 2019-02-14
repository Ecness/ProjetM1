package model.entity.player.donnee;

import java.util.HashMap;
import java.util.Map;

import model.entity.player.Science;

public class Technologie {

	private Map<Integer,Science> science;

	public Technologie() {
		this(new HashMap<Integer,Science>());
	}
	public Technologie(Map<Integer, Science> science) {
		super();
		this.science = science;
	}

	public Map<Integer, Science> getScience() {
		return science;
	}

	public void setScience(Map<Integer, Science> science) {
		this.science = science;
	}
	
}

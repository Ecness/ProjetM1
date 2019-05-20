package view.menus;

import com.badlogic.gdx.utils.Array;

public enum EnumCouleurs {

	BLUE,
	CYAN,
	GREEN,
	YELLOW,
	ORANGE,
	BROWN,
	PINK,
	MAGENTA;
	
	
	@Override
	public String toString() {
		return name();
	}
	
	public static Array<String> getCouleur() {
		
		Array<String> string = new Array<String>();
		for (EnumCouleurs e : EnumCouleurs.values()) {
			string.add(e.name());
		}
		return string;
	}
}

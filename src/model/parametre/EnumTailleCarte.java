package model.parametre;

public enum EnumTailleCarte {
	TEST(10),
	TEST2(25),
	MINUSCULE(50),
	PETITE (75),
	MOYENNE (100),
	GRANDE (150),
	IMMENSE (200);
	
	/**Nombre de système présents dans la carte*/
	private int quantite;
	
	EnumTailleCarte(int quantite) {
		this.quantite = quantite;
	}

	public int getQuantite() {
		return quantite;
	}
}

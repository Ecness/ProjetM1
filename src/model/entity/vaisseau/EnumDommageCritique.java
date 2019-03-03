package model.entity.vaisseau;

public enum EnumDommageCritique {

	ARME_ENDOMMAGER(0),
	ARME_DETRUITE(1),
	MOTEUR_ENDOMMAGER(2),
	MOTEUR_DETRUIT(3),
	GENERATEUR_DE_BOUCLIER_DETRUIT(4),
	BREACH_DANS_LA_COQUE(5),
	STRUCTURE_INTERNE_TOUCHER(6),
	MAGASIN_A_MUNITION(7);
	
	private int id;
	private static int nbDommage = 7;
	
	private EnumDommageCritique(int id) {
		this.id = id;
	}
	
	public static EnumDommageCritique getDammage() {
		int type = (int) (nbDommage*Math.random()+1);
		for(EnumDommageCritique e : values()) {
			if(type==e.getId()) {
				return e;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
}

package model.entity.general;

public enum EnumPassif {
	
	REPARATION_PLUS_EFFICACE(EnumGrade.NOVIS),
	MORALE_AMELIORE(EnumGrade.NOVIS),
	CHANCE_DE_TOUCHER_AMELIOREE(EnumGrade.NOVIS),
	EQUIPE_D_ABORDAGE_SUPPLEMENTAIRE(EnumGrade.NOVIS),
	AUGMENTATION_DE_LA_CAPACITE_DE_FLOTTE(EnumGrade.NOVIS),
	VITESSE_DE_LA_FLOTTE(EnumGrade.NOVIS),
	ATTAQUE_DE_LA_FLOTTE(EnumGrade.NOVIS),
	DEFENSE_DE_LA_FLOTTE(EnumGrade.NOVIS),
	SANTE_DE_LA_FLOTTE(EnumGrade.NOVIS),
	BOUCLIER_DE_LA_FLOTTE(EnumGrade.NOVIS),
	ESQUIVE_DE_LA_FLOTTE(EnumGrade.NOVIS),
	DEFENSE_DE_PROXIMITE_AMELIOREE(EnumGrade.NOVIS);
	
	private EnumGrade gradeNecessaire;

	private EnumPassif(EnumGrade gradeNecessaire) {
		this.gradeNecessaire = gradeNecessaire;
	}
	
	public EnumGrade getGradeNecessaire() {
		return gradeNecessaire;
	}
	
}
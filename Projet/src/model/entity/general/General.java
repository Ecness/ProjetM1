package model.entity.general;

public class General {
	private String nom;
	private int experience;
	private EnumGrade grade;
	private EnumPassif[] Passif;
	
	public General(String nom) {
		this.nom = nom;
		this.experience = 0;
		this.grade = EnumGrade.NOVIS;
		Passif = new EnumPassif[5];// max a deffinir 
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public EnumGrade getGrade() {
		return grade;
	}

	public void setGrade(EnumGrade grade) {
		this.grade = grade;
	}

	public EnumPassif[] getPassif() {
		return Passif;
	}

	public void setPassif(EnumPassif[] passif) {
		Passif = passif;
	}
	
}

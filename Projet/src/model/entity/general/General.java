package model.entity.general;

import java.util.ArrayList;
import java.util.List;

public class General {
	private String nom;
	private int experience;
	private EnumGrade grade;
	private List<EnumPassif> Passif;
	
	public General(String nom) {
		this.nom = nom;
		this.experience = 0;
		this.grade = EnumGrade.NOVIS;
		Passif = new ArrayList<EnumPassif>();// max a deffinir 
	}

	public boolean choixPassif(EnumPassif passif) {
		
		if(grade.getNumero()<passif.getGradeNecessaire().getNumero()) {
			return false;
		}else {
			this.Passif.add(passif);
			return true;
		}
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

	public List<EnumPassif> getPassif() {
		return Passif;
	}

	public void setPassif(List<EnumPassif> passif) {
		Passif = passif;
	}
	
}

package model.entity.player;

public class Science {

	private String nom;
	private String description;
	private boolean rechercher;
	private int cout;
	private int dependanceUn;
	private int dependanceDeux;
	
	public Science() {
		this("Default", "", false, -1, -1, -1);
	}
	
	public Science(String nom, String description, boolean rechercher, int cout, int dependanceUn, int dependanceDeux) {
		this.nom = nom;
		this.description = description;
		this.rechercher = rechercher;
		this.cout = cout;
		this.dependanceUn = dependanceUn;
		this.dependanceDeux = dependanceDeux;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isRechercher() {
		return rechercher;
	}
	public void setRechercher(boolean rechercher) {
		this.rechercher = rechercher;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public int getDependanceUn() {
		return dependanceUn;
	}
	public void setDependanceUn(int dependanceUn) {
		this.dependanceUn = dependanceUn;
	}
	public int getDependanceDeux() {
		return dependanceDeux;
	}
	public void setDependanceDeux(int dependanceDeux) {
		this.dependanceDeux = dependanceDeux;
	}
	
	public int getMaxDependance() {
		return dependanceUn < dependanceDeux ? dependanceDeux : dependanceUn;
	}

//	@Override
//	public boolean equals(Object obj) {
//		return nom.equals(((Science) obj).getNom());
//	}
}

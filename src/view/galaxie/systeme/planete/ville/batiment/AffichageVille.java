package view.galaxie.systeme.planete.ville.batiment;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import model.carte.stellaire.Ville;
import view.galaxie.systeme.planete.ville.file.FileDeConstruction;

public class AffichageVille extends VerticalGroup {

	public AffichageVille(Ville ville, Skin skin) {
		super();
		setName("afficheur_ville");
		
		AffichageBatimentsVille batiments = new AffichageBatimentsVille(ville, skin);
		batiments.setName("batiments_ville");
		
		FileDeConstruction listeAttente = new FileDeConstruction(ville, skin);
		listeAttente.setName("liste_attente");
		
		addActor(batiments);
		addActor(listeAttente);
	}
	
	public void updateBatiments(Ville ville, Skin skin) {
		((AffichageBatimentsVille) findActor("batiments_ville")).update(ville, skin);
	}
	
	public void updateFile(Ville ville, Skin skin) {
		((FileDeConstruction) findActor("liste_attente")).update(ville, skin);
	}
}

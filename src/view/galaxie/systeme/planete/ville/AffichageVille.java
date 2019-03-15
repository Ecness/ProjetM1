package view.galaxie.systeme.planete.ville;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import model.carte.stellaire.Ville;

public class AffichageVille extends SplitPane {

	public AffichageVille(Ville ville, Skin skin) {
		super(null, null, true, skin);
		setName("afficheur_ville");
		
		AffichageBatimentsVille afficheurBatiments = new AffichageBatimentsVille(ville, skin);
		afficheurBatiments.setName("afficheur_batimentsVille");
		
		setFirstWidget(afficheurBatiments);
		
		
		FileDeConstruction afficheurListeAttente = new FileDeConstruction(this, ville, skin);
		afficheurListeAttente.setName("liste_attente");
		
		setSecondWidget(afficheurListeAttente);
	}
	
	public void updateBatiments(Ville ville, Skin skin) {
		((AffichageBatimentsVille) findActor("afficheur_batimentsVille")).update(ville, skin);
	}
	
	public void updateFile(Ville ville, Skin skin) {
		if (findActor("liste_attente") != null) {
			((FileDeConstruction) findActor("liste_attente")).update(this, ville, skin);
		} else {
			FileDeConstruction afficheurListeAttente = new FileDeConstruction(this, ville, skin);
			afficheurListeAttente.setName("liste_attente");
			
			setSecondWidget(afficheurListeAttente);
		}
	}
}

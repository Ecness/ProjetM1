package view.galaxie.planete.ville;

import java.io.ObjectInputStream.GetField;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import model.carte.stellaire.Ville;

//TODO SEPARER LES VUES
public class AffichageVille extends SplitPane {

	public AffichageVille(Ville ville, Skin skin) {
		super(null, null, true, skin);
		setName("afficheur_ville");
		
		AffichageBatimentsVille afficheurBatiments = new AffichageBatimentsVille(ville, skin);
		afficheurBatiments.setName("afficheur_batimentsVille");
		
		setFirstWidget(afficheurBatiments);
		
		
		FileDeConstruction afficheurListeAttente = new FileDeConstruction(ville, skin);
		afficheurListeAttente.setName("liste_attente");
		
		setSecondWidget(afficheurListeAttente);
	}
	
	public void updateBatiments(Ville ville, Skin skin) {
		((AffichageBatimentsVille) findActor("afficheur_batimentsVille")).update(ville, skin);
	}
	
	public void updateFile(Ville ville, Skin skin) {
		((FileDeConstruction) findActor("liste_attente")).update(ville, skin);
	}
}

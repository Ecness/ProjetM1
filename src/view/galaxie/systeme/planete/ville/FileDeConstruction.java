package view.galaxie.systeme.planete.ville;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import model.carte.stellaire.Ville;

public class FileDeConstruction extends HorizontalGroup {

	public FileDeConstruction(SplitPane container, Ville ville, Skin skin) {
		super();
		
		FileDeConstructionBatiment batiments = new FileDeConstructionBatiment(container, ville, skin);
		batiments.setName("file_batiments");
		
		FileDeConstructionVaisseau vaisseaux = new FileDeConstructionVaisseau(ville, skin);
		vaisseaux.setName("file_vaisseaux");
		
		addActor(batiments);
		addActor(vaisseaux);
	}
	
	public void update(SplitPane container, Ville ville, Skin skin) {
		((FileDeConstructionBatiment) findActor("file_batiments")).update(container, ville, skin);
		((FileDeConstructionVaisseau) findActor("file_vaisseaux")).update(ville);
	}
}

package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;

import model.carte.stellaire.Ville;

public class FileDeConstruction extends SplitPane {

	public FileDeConstruction(Ville ville, Skin skin) {
		super(null, null, false, skin);
		setName("file_construction");
		
		FileDeConstructionBatiment batiments = new FileDeConstructionBatiment(ville, skin);
		batiments.setName("file_batiments");
		
		FileDeConstructionVaisseau vaisseaux = new FileDeConstructionVaisseau(ville, skin);
		vaisseaux.setName("file_vaisseaux");
		
		setFirstWidget(batiments);
		setSecondWidget(vaisseaux);
	}
	
	public void update(Ville ville, Skin skin) {
		((FileDeConstructionBatiment) findActor("file_batiments")).update(ville, skin);
		((FileDeConstructionVaisseau) findActor("file_vaisseaux")).update(ville, skin);
	}
}

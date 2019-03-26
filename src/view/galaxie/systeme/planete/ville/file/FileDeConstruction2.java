package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;

import model.carte.stellaire.Ville;

public class FileDeConstruction2 extends SplitPane {

	public FileDeConstruction2(Ville ville, Skin skin) {
		super(null, null, false, skin);
		setName("file_construction");
		
		FileDeConstructionBatiment2 batiments = new FileDeConstructionBatiment2(ville, skin);
		batiments.setName("file_batiments");
		
		FileDeConstructionVaisseau2 vaisseaux = new FileDeConstructionVaisseau2(ville, skin);
		vaisseaux.setName("file_vaisseaux");
		
		setFirstWidget(batiments);
		setSecondWidget(vaisseaux);
	}
	
	public void update(Ville ville, Skin skin) {
		((FileDeConstructionBatiment2) findActor("file_batiments")).update(ville, skin);
		((FileDeConstructionVaisseau2) findActor("file_vaisseaux")).update(ville, skin);
	}
}

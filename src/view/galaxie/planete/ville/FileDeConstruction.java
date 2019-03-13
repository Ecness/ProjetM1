package view.galaxie.planete.ville;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.carte.stellaire.Ville;

public class FileDeConstruction extends HorizontalGroup {

	public FileDeConstruction(Ville ville, Skin skin) {
		super();
		
		FileDeConstructionBatiment batiments = new FileDeConstructionBatiment(ville, skin);
		batiments.setName("file_batiments");
		
		FileDeConstructionVaisseau vaisseaux = new FileDeConstructionVaisseau(ville, skin);
		vaisseaux.setName("file_vaisseaux");
		
		addActor(batiments);
		addActor(vaisseaux);
	}
	
	public void update(Ville ville, Skin skin) {
		((FileDeConstructionBatiment) findActor("file_batiments")).update(ville, skin);
		((FileDeConstructionVaisseau) findActor("file_vaisseaux")).update(ville);
//		clear();
//		FileDeConstructionBatiment batiments = new FileDeConstructionBatiment(ville, skin);
//		batiments.setName("file_batiments");
//		
//		FileDeConstructionVaisseau vaisseaux = new FileDeConstructionVaisseau(ville, skin);
//		vaisseaux.setName("file_vaisseaux");
//		
//		addActor(batiments);
//		addActor(vaisseaux);
	}
}

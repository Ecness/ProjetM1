package view.galaxie.planete.ville;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class FileDeConstructionBatiment extends VerticalGroup {

	public FileDeConstructionBatiment(Ville ville, Skin skin) {
		super();
		setName("liste_attente_batiment");

		int i = 0;
		for (BatimentVille batiment : ville.getFileDeConstructionBatiment()) {
			TextButton bat = new TextButton(batiment.getNom() + "\n" + batiment.getCout() / ville.getTRessource().get(EnumRessource.PRODUCTION), skin);
			bat.setName("batiment_file_" + i);
			i++;
			//TODO Ajouter mécanique d'annulation
			addActor(bat);
		}
	}

	public void update(Ville ville, Skin skin) {
//		List<Actor> newList = new ArrayList<Actor>();

		int ind = 0;
		for (Actor button : getChildren()) {
			
			button.setName("batiment_file_" + ind);
			ind++;
		}

		for (int i = 0; i < ville.getFileDeConstructionBatiment().size(); i++) {
			BatimentVille batiment = ville.getFileDeConstructionBatiment().get(i);
			String text = batiment.getNom() + "\n" + batiment.getCout() / ville.getTRessource().get(EnumRessource.PRODUCTION);
			if (findActor("batiment_file_" + i) != null) {
				((TextButton) findActor("batiment_file_" + i)).setText(text);
			} else {
				TextButton button = new TextButton(text, skin);
				button.setName("batiment_file_" + i);
				addActor(button);
			}

		}
		if (ville.isConstructionTerminee()) {
			//				ville.getFileDeConstructionBatiment().remove(batiment);
			removeActor(findActor("batiment_file_0"));
			ville.setConstructionTerminee(false);
		}

//		for (Actor button : newList) {
//			removeActor(button);
//		}

	}
}

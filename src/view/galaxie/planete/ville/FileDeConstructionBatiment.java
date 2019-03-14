package view.galaxie.planete.ville;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.boutons.ville.ConfirmationAnnulationBatimentVille;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class FileDeConstructionBatiment extends VerticalGroup {

	public FileDeConstructionBatiment(SplitPane container, Ville ville, Skin skin) {
		super();
		setName("liste_attente_batiment");

		int i = 0;
		for (BatimentVille batiment : ville.getFileDeConstructionBatiment()) {
			TextButton bat = new TextButton(batiment.getNom() + "\nTour(s)" + (int) Math.ceil(batiment.getCout() / ville.getTRessource().get(EnumRessource.PRODUCTION)), skin);
			bat.setName("batiment_file_" + i);
			i++;
			//TODO Ajouter mécanique d'annulation
			bat.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					container.setSecondWidget(new ConfirmationAnnulationBatimentVille(ville, batiment, skin));
				}
				
			});
			
			addActor(bat);
		}
	}

	public void update(SplitPane container, Ville ville, Skin skin) {
//		List<Actor> newList = new ArrayList<Actor>();

		int ind = 0;
		for (Actor button : getChildren()) {
			
			button.setName("batiment_file_" + ind);
			ind++;
		}

		for (int i = 0; i < ville.getFileDeConstructionBatiment().size(); i++) {
			BatimentVille batiment = ville.getFileDeConstructionBatiment().get(i);
			String text = batiment.getNom() + "\nTour(s)" + (int) Math.ceil(batiment.getCout() / ville.getTRessource().get(EnumRessource.PRODUCTION));
			if (findActor("batiment_file_" + i) != null) {
				((TextButton) findActor("batiment_file_" + i)).setText(text);
			} else {
				TextButton button = new TextButton(text, skin);
				button.setName("batiment_file_" + i);
				
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						container.setSecondWidget(new ConfirmationAnnulationBatimentVille(ville, batiment, skin));
					}
					
				});
				
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

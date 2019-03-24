package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.ConfirmationAnnulationBatimentVille;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class FileDeConstructionBatiment extends VerticalGroup {

	public FileDeConstructionBatiment(SplitPane container, Ville ville, Skin skin) {
		super();
		setName("liste_attente_batiment");

		int i = 0;
		for (BatimentVille batiment : ville.getFileDeConstructionBatiment()) {
			TextButton bat = new TextButton(batiment.getNom() + "\nTour(s) : " + (int) Math.ceil((double) batiment.getCout() / (double) ville.getTRessource().get(EnumRessource.PRODUCTION)), skin);
			bat.setName("batiment_file_" + i);
			i++;
			bat.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					new ConfirmationAnnulationBatimentVille(ville, batiment, skin);
				}
				
			});
			
			addActor(bat);
		}
	}

	public void update(SplitPane container, Ville ville, Skin skin) {

		int ind = 0;
		
		if (ville.isConstructionAnnulee()) {
			clear();
			ville.setConstructionAnnulee(false);
		}
		
		for (Actor button : getChildren()) {
			
			button.setName("batiment_file_" + ind);
			ind++;
		}

		for (int i = 0; i < ville.getFileDeConstructionBatiment().size(); i++) {
			BatimentVille batiment = ville.getFileDeConstructionBatiment().get(i);
			String text = batiment.getNom() + "\nTour(s) : " + (int) Math.ceil((double) batiment.getCout() / (double) ville.getTRessource().get(EnumRessource.PRODUCTION));
			if (findActor("batiment_file_" + i) != null) {
				((TextButton) findActor("batiment_file_" + i)).setText(text);
			} else {
				TextButton button = new TextButton(text, skin);
				button.setName("batiment_file_" + i);
				
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						new ConfirmationAnnulationBatimentVille(ville, batiment, skin);
					}
					
				});
				
				addActor(button);
			}

		}
		if (ville.isConstructionTerminee()) {
			removeActor(findActor("batiment_file_0"));
			ville.setConstructionTerminee(false);
		}

	}
}

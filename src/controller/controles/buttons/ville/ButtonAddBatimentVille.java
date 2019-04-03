package controller.controles.buttons.ville;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.error.Error;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;
import view.launcher.Project;

public class ButtonAddBatimentVille extends TextButton {

	public ButtonAddBatimentVille(Actor container, Ville ville, BatimentVille batiment, Skin skin) {
		super(batiment.getNom() + "\nTour(s) : " + (int) Math.ceil((double) batiment.getCout() / (double) ville.getTRessource().get(EnumRessource.PRODUCTION)), skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				//Test si le joueur a débloqué la technologie nécessaire
				if (ville.isBuildingUnlocked(batiment)) {
					//Récupération du bâtiment selon son nom (clé de la map)
					ville.constructionBatiment(batiment);
					ville.setReDrawFilesBatiments(true);
					ville.setReDraw(true);
					Project.displayHasChanged = true;

					//Remove the container from screen
					container.remove();
				} else {
					//Affichage d'erreur si technologie non débloquée, ne devrait pas apparaître
					new Error("Technologie manquante",
							"Technologie manquante : " + 
									ville.getJoueur().getTechnology().getScienceBatiment().get(batiment.getTechNecessaire()).getNom(),
									skin);
				}
			}
		});
	}
}

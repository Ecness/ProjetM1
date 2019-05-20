package controller.controles.buttons.vaisseau;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.error.Error;
import model.EnumRessource;
import model.carte.stellaire.Ville;
import model.entity.vaisseau.Vaisseau;
import view.launcher.Project;

public class ButtonAddVaisseau extends TextButton {

	public ButtonAddVaisseau(Actor container, Ville ville, Vaisseau vaisseau, Skin skin) {
		super(vaisseau.getNom() + "\nTour(s) : " + (int) Math.ceil((double) vaisseau.getCout().get(EnumRessource.PRODUCTION) / (double) ville.getTRessource().get(EnumRessource.PRODUCTION)), skin);

		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (ville.ressourceDisponible(vaisseau)) {
					ville.constructionVaisseau(vaisseau);
					ville.setReDrawFilesVaisseaux(true);
					ville.setReDraw(true);
					Project.displayHasChanged = true;

					//Remove the container from screen
					container.remove();
				} else {
					new Error("Ressources manquantes", 
							"Pas assez de ressources", 
							skin);
				}
			}
		});
	}
}

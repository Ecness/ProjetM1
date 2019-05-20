package controller.controles.buttons.planete;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.error.Error;
import model.batiment.BatimentPlanete;
import model.carte.stellaire.Planete;
import view.launcher.Project;

public class ButtonAddBatimentPlanete extends TextButton {

	public ButtonAddBatimentPlanete(Actor container, Planete planete, int emplacement, BatimentPlanete batiment, Skin skin) {
		super(batiment.getNom(), skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				if (planete.verifConstructionBatiment(batiment, emplacement)) {
					planete.constructionBatiment(batiment, emplacement);
					
					if (emplacement == 0) {
						planete.setReDrawBuild1(true); 
					} else {
						planete.setReDrawBuild2(true);
					}
					planete.setReDraw(true);
					Project.displayHasChanged = true;
					
					//Remove the container from screen
					container.remove();
				} else {
					//Gestion du bouton d'erreur en cas de ressources insuffisantes
					new Error("Ressources manquantes", 
								"Pas assez de ressources", 
								skin);
				}
			}
		});
	}
}

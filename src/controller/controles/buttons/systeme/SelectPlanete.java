package controller.controles.buttons.systeme;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class SelectPlanete extends TextButton {
	
	public SelectPlanete(Systeme systeme, Planete planete, Skin skin) {
		super("", skin);
		
		String text = "Type : " + planete.getTypePlanete() + "\n";
		
		for (EnumRessource ressource : EnumRessource.values()) {
			int value = planete.getVille() != null ? planete.getVille().getTRessource().get(ressource)
												: planete.getTRessource().get(ressource);
			text += " " + ressource.toString().substring(0, 1) + value;
		}
		
		setText(text);
		
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.planeteSelectionne = planete;
				Project.displayHasChanged = true;
			}
		});
	}
}

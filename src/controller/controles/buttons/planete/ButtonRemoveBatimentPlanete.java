package controller.controles.buttons.planete;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.planete.ConfirmationDestructionBatimentPlanete;
import model.carte.stellaire.Planete;

public class ButtonRemoveBatimentPlanete extends TextButton {

	public ButtonRemoveBatimentPlanete(Planete planete, int emplacement, Skin skin) {
		super("X", skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				new ConfirmationDestructionBatimentPlanete(planete, emplacement, skin);
			}
			
		});
	}
}

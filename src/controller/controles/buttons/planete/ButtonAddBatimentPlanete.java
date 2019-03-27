package controller.controles.buttons.planete;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.controles.select.SelectBatimentPlanete;
import model.carte.stellaire.Planete;

public class ButtonAddBatimentPlanete extends TextButton {

	public ButtonAddBatimentPlanete(Planete planete, int emplacement, Skin skin) {
		super("+", skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				new SelectBatimentPlanete(planete, emplacement, skin);
			}
			
		});
	}
	
}

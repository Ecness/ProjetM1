package controller.boutons.planete;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Planete;

public class ConfirmationDestructionBatimentPlanete extends Dialog {

	public ConfirmationDestructionBatimentPlanete(Planete planete, int emplacement,  Skin skin) {
		super("Confirmation destruction", skin);
		
		TextButton cancel = new TextButton("Annuler", skin);
		cancel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				clear();
				planete.setReDraw(true);
			}
			
		});
		
		TextButton validate = new TextButton("Confirmer", skin);
		validate.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				planete.deconstructionBatiment(emplacement);
				clear();
				planete.setReDraw(true);
			}
			
		});
		
		button(cancel);
		button(validate);
	}
}

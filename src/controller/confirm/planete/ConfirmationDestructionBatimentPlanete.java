package controller.confirm.planete;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.carte.stellaire.Planete;

public class ConfirmationDestructionBatimentPlanete extends Confirm {

	public ConfirmationDestructionBatimentPlanete(Planete planete, int emplacement,  Skin skin) {
		super("Confirmation destruction", 
				"Confirmer la destruction de " + planete.getTBatiment()[emplacement].getNom() + " ?", 
				skin);
		
		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				planete.setReDraw(true);
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				planete.deconstructionBatiment(emplacement);
				planete.setReDraw(true);
				getParent().remove();
			}
			
		});
	}
}

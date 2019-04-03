package controller.confirm.planete;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.carte.stellaire.Planete;
import view.launcher.Project;

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
				if (emplacement == 0) {
					planete.setReDrawBuild1(true); 
				} else {
					planete.setReDrawBuild2(true);
				}
				planete.setReDraw(true);
				Project.displayHasChanged = true;
				getParent().remove();
			}
			
		});
	}
}

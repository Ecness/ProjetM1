package controller.confirm.ville.batiment;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class ConfirmationDestructionBatimentVille extends Confirm {

	public ConfirmationDestructionBatimentVille(Ville ville, BatimentVille batiment,  Skin skin) {
		super("Confirmation destruction",
				"Confirmer la destruction de " + batiment.getNom() + " ?",
				skin);
		
		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.setReDrawBatiments(true);
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.destructionBatiment(batiment);
				ville.setReDrawBatiments(true);
				getParent().remove();
			}
			
		});
	}
}

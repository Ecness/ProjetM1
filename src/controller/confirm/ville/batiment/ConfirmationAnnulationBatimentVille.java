package controller.confirm.ville.batiment;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;
import view.launcher.Project;

public class ConfirmationAnnulationBatimentVille extends Confirm {

	public ConfirmationAnnulationBatimentVille(Ville ville, BatimentVille batiment, Skin skin) {
		super("Confirmation annulation",
				"Confirmer l'annulation de " + batiment.getNom() + " ?",
				skin);
		
		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.setReDrawFilesBatiments(true);
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.annulationBatiment(batiment);
				ville.setBatimentAnnule(true);
				ville.setReDrawFilesBatiments(true);
				Project.displayHasChanged = true;
				getParent().remove();
			}
			
		});
	}
}

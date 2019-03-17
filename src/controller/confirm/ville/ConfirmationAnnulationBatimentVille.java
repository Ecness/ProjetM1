package controller.confirm.ville;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class ConfirmationAnnulationBatimentVille extends Confirm {

	public ConfirmationAnnulationBatimentVille(Ville ville, BatimentVille batiment, Skin skin) {
		super("Confirmation annulation",
				"Confirmer l'annulation de " + batiment.getNom() + " ?",
				skin);
		
		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.setReDrawFiles(true);
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.annulationBatiment(batiment);
				ville.setReDrawFiles(true);
				ville.setConstructionAnnulee(true);
				getParent().remove();
			}
			
		});
	}
}

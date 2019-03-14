package controller.boutons.ville;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class ConfirmationAnnulationBatimentVille extends Dialog {

	public ConfirmationAnnulationBatimentVille(Ville ville, BatimentVille batiment, Skin skin) {
		super("Confirmation annulation", skin);
		
		TextButton cancel = new TextButton("Annuler", skin);
		cancel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				clear();
				ville.setReDrawFiles(true);
			}
			
		});
		
		TextButton validate = new TextButton("Confirmer", skin);
		validate.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.annulationBatiment(batiment);
				clear();
				ville.setReDrawFiles(true);
			}
			
		});
		
		button(cancel);
		button(validate);
	}
}

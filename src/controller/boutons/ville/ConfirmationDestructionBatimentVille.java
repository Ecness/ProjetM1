package controller.boutons.ville;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class ConfirmationDestructionBatimentVille extends Dialog {

	public ConfirmationDestructionBatimentVille(Ville ville, BatimentVille batiment,  Skin skin) {
		super("Confirmation destruction", skin);
		
		TextButton cancel = new TextButton("Annuler", skin);
		cancel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				clear();
				ville.setReDrawBatiments(true);
			}
			
		});
		
		TextButton validate = new TextButton("Confirmer", skin);
		validate.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.destructionBatiment(batiment);
				clear();
				ville.setReDrawBatiments(true);
			}
			
		});
		
		button(cancel);
		button(validate);
	}
}

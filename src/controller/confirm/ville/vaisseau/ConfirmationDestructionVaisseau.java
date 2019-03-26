package controller.confirm.ville.vaisseau;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.entity.vaisseau.Vaisseau;

//TODO Implémenter le comportement
public class ConfirmationDestructionVaisseau extends Confirm {

	public ConfirmationDestructionVaisseau(Vaisseau vaisseau, Skin skin) {
		super("Confirmation destruction",
				"Confirmer la destruction de " + vaisseau.getNom() + " ?",
				skin);
		
		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
//				ville.setReDrawBatiments(true);
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
//				ville.destructionBatiment(batiment);
//				ville.setReDrawBatiments(true);
				getParent().remove();
			}
			
		});
	}
}

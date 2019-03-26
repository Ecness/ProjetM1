package controller.confirm.ville.vaisseau;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.Confirm;
import model.carte.stellaire.Ville;
import model.entity.vaisseau.Vaisseau;

public class ConfirmationAnnulationVaisseau extends Confirm {
	
	public ConfirmationAnnulationVaisseau(Ville ville, Vaisseau vaisseau, Skin skin) {
		super("Confirmation annulation",
				"Confirmer l'abbulation de " + vaisseau.getNom() + " ?",
				skin);
		
		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ville.setReDrawFilesVaisseaux(true);
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO Implémenter les méthodes vaisseau
//				ville.annulationBatiment(batiment);
				ville.setReDrawFilesVaisseaux(true);
//				ville.setConstructionAnnulee(true);
				getParent().remove();
			}
			
		});
	}
}

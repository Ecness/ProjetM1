package view.galaxie.systeme.planete;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import model.carte.stellaire.Planete;
import view.galaxie.systeme.planete.ville.AffichageVille;

public class AffichagePlanete extends Container<Actor> {

	public AffichagePlanete(Planete planete, Skin skin) {
		super(null);
		setName("afficheur_planete");

		if (planete.getVille() != null) {
			setActor(new AffichageVille(planete.getVille(), skin));
		} else {
			setActor(new AffichageBatimentsPlanete(planete, skin));
		}
	}

	public void update(Planete planete, Skin skin) {
		if (planete.getVille() != null) {
			if (planete.getVille().isReDrawBatiments()) {
				((AffichageVille) getActor()).updateBatiments(planete.getVille(), skin);
				planete.getVille().setReDrawBatiments(false);
			}
			if (planete.getVille().isReDrawFiles()) {
				((AffichageVille) getActor()).updateFile(planete.getVille(), skin);
				planete.getVille().setReDrawFiles(false);
			}
		} else {
			((AffichageBatimentsPlanete) getActor()).update(planete);
		}
		planete.setReDraw(true);
	}
}

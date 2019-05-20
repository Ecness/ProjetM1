package view.galaxie.systeme;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import controller.controles.buttons.systeme.SelectPlanete;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;

public class AffichageListePlanetes extends VerticalGroup {
	
	public AffichageListePlanetes(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_liste_planetes");
		
		for (Planete planete : systeme.getTPlanete()) {
			TextButton bouton = new SelectPlanete(systeme, planete, skin);
			bouton.setName("planete_" + planete.getId());
			bouton.center();
			bouton.getLabel().setAlignment(Align.left);
			
			addActor(bouton);
		}
		
		align(Align.center);
		grow();
	}
}

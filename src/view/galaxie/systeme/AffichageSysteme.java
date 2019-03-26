package view.galaxie.systeme;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import model.carte.stellaire.Systeme;

public class AffichageSysteme extends VerticalGroup {
	
	public AffichageSysteme(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_systeme");
		
		addActor(new AffichageInformationsSysteme(systeme, skin));
		addActor(new AffichageListePlanetes2(systeme, skin));
		
		align(Align.center);
		grow();
	}

	public void update(Systeme systeme) {
		((AffichageListePlanetes2) findActor("afficheur_liste_planetes")).update(systeme);
	}
}

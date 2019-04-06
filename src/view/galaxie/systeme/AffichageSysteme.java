package view.galaxie.systeme;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import model.carte.stellaire.Systeme;
import view.galaxie.systeme.flotte.AffichageFlottes;
import view.galaxie.systeme.planete.AffichagePlanete;
import view.launcher.Project;

public class AffichageSysteme extends VerticalGroup {
	
	public AffichageSysteme(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_systeme");
		
		addActor(new AffichageInformationsSysteme(systeme, skin));
		addActor(new AffichageListePlanetes(systeme, skin));
		addActor(new AffichageFlottes(systeme, skin));
		
		align(Align.center);
		grow();
	}

	public void update(Systeme systeme, Skin skin) {
		if (Project.planeteSelectionne == null || Project.changeSysteme) {
			Project.changeSysteme = false;
			if (findActor("afficheur_informations_systeme") != null && findActor("afficheur_liste_planetes") != null) {
				((AffichageInformationsSysteme) findActor("afficheur_informations_systeme")).update(systeme, skin);
				findActor("afficheur_liste_planetes").remove();
				addActorAfter(findActor("afficheur_informations_systeme"), new AffichageListePlanetes(systeme, skin));
			} else {
				clear();
				addActor(new AffichageInformationsSysteme(systeme, skin));
				addActor(new AffichageListePlanetes(systeme, skin));
				addActor(new AffichageFlottes(systeme, skin));
			}
		} else {
			if (findActor("afficheur_planete") != null) {
				((AffichagePlanete) findActor("afficheur_planete")).update(Project.planeteSelectionne, skin);
			} else {
				clear();
				addActor(new AffichagePlanete(systeme, Project.planeteSelectionne, skin));
			}
		}
	}
}

package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import model.carte.stellaire.Systeme;

public class AffichageSysteme extends VerticalGroup {
	
	public AffichageSysteme(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_systeme");
		
		VerticalGroup planetes = new VerticalGroup();
		planetes.setName("planetes");
		
		//TODO Remplacer l'identifiant par un nom
		Label nomSysteme = new Label("" + systeme.getIdSysteme(), skin);
		if (systeme.getJoueur() != null) {
			nomSysteme.setText(nomSysteme.getText() + " : " + systeme.getJoueur().getName());
		} else {
			nomSysteme.setText(nomSysteme.getText() + " : libre");
		}
		nomSysteme.setAlignment(Align.center);
		
		Label typeSysteme = new Label(systeme.getTypeSysteme().toString(), skin);
		typeSysteme.setAlignment(Align.center);
		
		planetes = new AffichageListePlanetes(systeme, skin);
		
		addActor(nomSysteme);
		addActor(typeSysteme);
		addActor(planetes);
		
		align(Align.center);
		grow();
	}

	public void update(Systeme systeme) {
		((AffichageListePlanetes) findActor("afficheur_liste_planetes")).update(systeme);
	}
}

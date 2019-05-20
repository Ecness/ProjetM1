package view.galaxie.systeme;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import model.carte.stellaire.Systeme;

public class AffichageInformationsSysteme extends SplitPane {

	public AffichageInformationsSysteme(Systeme systeme, Skin skin) {
		super(null, null, false, skin);
		setName("afficheur_informations_systeme");
		
		VerticalGroup informations = new VerticalGroup();
		
		//TODO Remplacer l'identifiant par un nom
		Label nomSysteme = new Label("" + systeme.getIdSysteme(), skin);
		nomSysteme.setName("name");
		if (systeme.getJoueur() != null) {
			nomSysteme.setText(nomSysteme.getText() + " : " + systeme.getJoueur().getName());
		} else {
			nomSysteme.setText(nomSysteme.getText() + " : libre");
		}
		nomSysteme.setAlignment(Align.center);
		
		Label typeSysteme = new Label("" + systeme.getTypeSysteme(), skin);
		typeSysteme.setName("type");
		typeSysteme.setAlignment(Align.center);
		
		informations.addActor(nomSysteme);
		informations.addActor(typeSysteme);
		setFirstWidget(informations);
		
		setSecondWidget(new AffichageAnomalies(systeme, skin));
	}
	
	public void update(Systeme systeme, Skin skin) {
		if (systeme.getJoueur() != null) {
			((Label) findActor("name")).setText("" + systeme.getIdSysteme() + " : " + systeme.getJoueur().getName());
		} else {
			((Label) findActor("name")).setText("" + systeme.getIdSysteme() + " : libre");
		}
		((Label) findActor("type")).setText("" + systeme.getTypeSysteme());
		
		setSecondWidget(new AffichageAnomalies(systeme, skin));
	}
}

package view.galaxie.systeme.flotte;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import model.carte.stellaire.Systeme;

public class ContainerFlottes extends ScrollPane {
	
	public ContainerFlottes(Systeme systeme, Skin skin) {
		super(null, skin);
		setName("container_flottes");
		
		setActor(new AffichageFlottes(this, systeme, skin));
	}
}

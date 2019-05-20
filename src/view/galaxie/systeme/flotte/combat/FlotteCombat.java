package view.galaxie.systeme.flotte.combat;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;
import view.galaxie.systeme.flotte.ButtonVaisseau;

public class FlotteCombat extends ScrollPane {

	public FlotteCombat(Flotte flotte, Skin skin) {
		super(null, skin);
		
		VerticalGroup group = new VerticalGroup();
		group.setName("group");
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			group.addActor(new ButtonVaisseau(vaisseau, skin));
		}
		
		setActor(group);
	}
	
	public void update(Flotte flotte, Skin skin) {
		findActor("group").clear();
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			((VerticalGroup) findActor("group")).addActor(new ButtonVaisseau(vaisseau, skin));
		}
	}
}

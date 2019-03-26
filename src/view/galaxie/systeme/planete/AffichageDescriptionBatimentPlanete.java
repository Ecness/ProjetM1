package view.galaxie.systeme.planete;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.batiment.BatimentPlanete;

public class AffichageDescriptionBatimentPlanete extends ScrollPane {
	
	public AffichageDescriptionBatimentPlanete(BatimentPlanete batiment, Skin skin) {
		super(null, skin);
		
		VerticalGroup group = new VerticalGroup();
		group.setName("group");
		
		Label description = new Label(batiment == null ? "" : batiment.getDescription(), skin);
		description.setName("description");
		group.addActor(description);
		
		Label bonus = new Label(batiment == null ? "" : "" + batiment.getBonus(), skin);
		description.setName("bonus");
		group.addActor(bonus);
		
		setActor(group);
	}
	
	public void update(BatimentPlanete batiment) {
		VerticalGroup group = ((VerticalGroup) findActor("group"));
		
		if (batiment == null) {
			((Label) group.findActor("description")).setText("");
			((Label) group.findActor("bonus")).setText("");
		} else {
			((Label) group.findActor("description")).setText(batiment.getDescription());
			((Label) group.findActor("bonus")).setText("" + batiment.getBonus());
		}
	}
}

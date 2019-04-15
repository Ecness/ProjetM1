package view.galaxie.systeme.flotte;

import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import controller.controles.buttons.vaisseau.ButtonReturnToFlotte;
import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;

public class AffichageDetailFlotte extends Table {

	public AffichageDetailFlotte(ScrollPane container, Flotte flotte, Systeme systeme, Skin skin) {
		super(skin);
		setName("table_detail_flotte");
		
		int i = 0;
		for (Entry<Integer, Vaisseau> vaisseau : flotte.getTVaisseau().entrySet()) {
			if (i == 3) {
				row();
				i = 0;
			}
			
			add(new ButtonVaisseau(vaisseau.getValue(), skin));
			i++;
		}
		
		row();
		add(new ButtonReturnToFlotte(container, systeme, skin));
	}
	
	public void update(Flotte flotte, Skin skin) {
		Table table = ((Table) findActor("table_detail_flotte"));
		table.reset();
		
		int i = 0;
		for (Entry<Integer, Vaisseau> vaisseau : flotte.getTVaisseau().entrySet()) {
			if (i == 3) {
				table.row();
				i = 0;
			}
			
			table.add(new ButtonVaisseau(vaisseau.getValue(), skin));
			i++;
		}
	}
}

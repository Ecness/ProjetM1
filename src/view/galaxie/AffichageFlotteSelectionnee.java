package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;
import view.galaxie.systeme.flotte.ButtonVaisseau;

public class AffichageFlotteSelectionnee extends ScrollPane {

	public AffichageFlotteSelectionnee(Flotte flotte, Skin skin) {
		super(null, skin);
		setName("flotte_selectionnee");
		
		Table table = new Table(skin);
		table.setName("table_detail_flotte");
		
		int i = 0;
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			if (i == 3) {
				table.row();
				i = 0;
			}
			
			table.add(new ButtonVaisseau(vaisseau, skin));
			i++;
		}
		
		table.row();
		
		setActor(table);
	}
	
	public void update(Flotte flotte, Skin skin) {
		Table table = ((Table) findActor("table_detail_flotte"));
		table.reset();
		
		int i = 0;
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			if (i == 3) {
				table.row();
				i = 0;
			}
			
			table.add(new ButtonVaisseau(vaisseau, skin));
			i++;
		}
		
		table.row();
	}
}

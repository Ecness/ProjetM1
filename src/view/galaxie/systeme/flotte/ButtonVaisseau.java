package view.galaxie.systeme.flotte;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import model.entity.vaisseau.Vaisseau;

public class ButtonVaisseau extends TextButton {

	public ButtonVaisseau(Vaisseau vaisseau, Skin skin) {
		super(vaisseau.getNom(), skin);
		
		row();
		ProgressBar life = new ProgressBar(0, vaisseau.getSanteMax(), 1, false, skin);
		life.setValue(vaisseau.getSanteMax());
		life.setName("life");
		life.setColor(Color.GREEN);
		add(life);
		
		row();
		ProgressBar shield = new ProgressBar(0, vaisseau.getBouclierMax(), 1, false, skin);
		shield.setValue(vaisseau.getBouclierMax());
		shield.setName("shield");
		shield.setColor(Color.BLUE);
		add(shield);
		
	}

	public void update(Vaisseau vaisseau) {
		((ProgressBar) findActor("life")).setValue(vaisseau.getSante());
		((ProgressBar) findActor("shield")).setValue(vaisseau.getBouclier());
	}
}

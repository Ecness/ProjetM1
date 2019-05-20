package view.galaxie.systeme;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.carte.stellaire.Anomalie;
import model.carte.stellaire.Systeme;

public class AffichageAnomalies extends VerticalGroup {
	
	public AffichageAnomalies(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_anomalies");
		
		Label labelAnomalie = new Label("Anomalie(s) : ", skin);
		labelAnomalie.setName("label_anomalie");
		addActor(labelAnomalie);

		int i = 1;
		for (Anomalie anomalie : systeme.getTAnomalie()) {
			Label ano = new Label("" + anomalie, skin);
			ano.setName("anomalie_" + i);
			i++;
			
			addActor(ano);
		}
	}
}

package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Planete;

public class AffichagePlanete extends SplitPane {
	private TextButton batiment1;
	private TextButton batiment2;
	
	public AffichagePlanete(Planete planete, Skin skin) {
		super(null, null, false, skin);
		setName("afficheur_planete");
		//TODO A remplacer par une icône
		String text = planete.getTBatiment()[0] == null ? "Aucun bâtiment" : planete.getTBatiment()[0].getNom();
		batiment1 = new TextButton(text, skin);
		batiment1.setName("batiment1");
		batiment1.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (planete.getJoueur() != null) {
					setFirstWidget(new SelectBatiment(planete, 0, skin));
				}
			}
			
		});
		
		text = planete.getTBatiment()[1] == null ? "Aucun bâtiment" : planete.getTBatiment()[1].getNom();
		batiment2 = new TextButton(text, skin);
		batiment2.setName("batiment2");
		batiment2.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (planete.getJoueur() != null) {
					setSecondWidget(new SelectBatiment(planete, 1, skin));
				}
			}
			
		});
		
		setFirstWidget(batiment1);
		setSecondWidget(batiment2);
	}
	public void update(Planete planete) {
		clearChildren();
		
		setFirstWidget(batiment1);
		setSecondWidget(batiment2);
		
		String text = planete.getTBatiment()[0] == null ? "Aucun bâtiment" : planete.getTBatiment()[0].getNom();
		((TextButton) findActor("batiment1")).setText(text);
		
		text = planete.getTBatiment()[1] == null ? "Aucun bâtiment" : planete.getTBatiment()[1].getNom();
		((TextButton) findActor("batiment2")).setText(text);
	}
}

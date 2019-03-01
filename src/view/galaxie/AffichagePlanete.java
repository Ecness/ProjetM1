package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import view.launcher.Project;

public class AffichagePlanete {
	private SplitPane container;
	private VerticalGroup groupBatiment1;
	private VerticalGroup groupBatiment2;
	private ScrollPane selectBatiment;
	
	public AffichagePlanete() {
		groupBatiment1 = new VerticalGroup();
		TextButton batiment1 = new TextButton("", Project.skin);
		batiment1.setName("batiment1");
		batiment1.setText(Project.planeteSelectionne.getTBatiment()[0] == null ? "Aucun bâtiment" : Project.planeteSelectionne.getTBatiment()[0].getNom());
//		System.out.println(Project.planeteSelectionne.getTBatiment()[0] == null ? "Aucun bâtiment" : Project.planeteSelectionne.getTBatiment()[0].getNom());
		batiment1.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				groupBatiment1.addActor(new SelectBatiment(Project.planeteSelectionne, 0).getPanel());
			}
			
		});
		groupBatiment1.addActor(batiment1);
		
		groupBatiment2 = new VerticalGroup();
		TextButton batiment2 = new TextButton("", Project.skin);
		batiment2.setName("batiment2");
		batiment2.setText(Project.planeteSelectionne.getTBatiment()[1] == null ? "Aucun bâtiment" : Project.planeteSelectionne.getTBatiment()[1].getNom());
		batiment2.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				groupBatiment2.addActor(new SelectBatiment(Project.planeteSelectionne, 1).getPanel());
			}
			
		});
		groupBatiment2.addActor(batiment2);
		
		container = new SplitPane(groupBatiment1, groupBatiment2, false, Project.skin);
	}
	public VerticalGroup getBatiment1() {
		return groupBatiment1;
	}

	public VerticalGroup getBatiment2() {
		return groupBatiment2;
	}

	public SplitPane getContainer() {
		return container;
	}
	
	public void reDraw() {
//		container.clear();
		((TextButton) groupBatiment1.findActor("batiment1")).setText(Project.planeteSelectionne.getTBatiment()[0] == null ? "Aucun bâtiment" : Project.planeteSelectionne.getTBatiment()[0].getNom());
		((TextButton) groupBatiment2.findActor("batiment2")).setText(Project.planeteSelectionne.getTBatiment()[1] == null ? "Aucun bâtiment" : Project.planeteSelectionne.getTBatiment()[1].getNom());
		container = new SplitPane(groupBatiment1, groupBatiment2, false, Project.skin);
	}
}

package view.galaxie.systeme.planete;


import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import controller.controles.buttons.planete.ButtonAddBatimentPlanete;
import controller.controles.buttons.planete.ButtonRemoveBatimentPlanete;
import model.carte.stellaire.Planete;

public class AffichageBatimentsPlanete extends Table {

	public AffichageBatimentsPlanete(Planete planete, Skin skin) {
		super(skin);
		setName("afficheur_batimentsPlanete");

		//TODO A remplacer par une icône + nom
		//Nom du bâtiment
		Label name1 = new Label(planete.getTBatiment()[0] == null ? "Aucun bâtiment" : planete.getTBatiment()[0].getNom(), skin);
		name1.setName("name1");
		//Description du bâtiment
//		ScrollPane description1 = new AffichageDescriptionBatimentPlanete(planete.getTBatiment()[0], skin);
//		description1.setName("description1");
		//Action a effectuer
		Button action1;
		if (planete.getTBatiment()[0] == null) {
			//Si pas de bâtiment, on peut en créer un
			action1 = new ButtonAddBatimentPlanete(planete, 0, skin);
		} else {
			//Sinon, on peut le détruire
			action1 = new ButtonRemoveBatimentPlanete(planete, 0, skin);
		}
		action1.setName("action1");
		
		add(name1/*, description1*/, action1);
		
		row();

		//Nom du bâtiment
		Label name2 = new Label(planete.getTBatiment()[1] == null ? "Aucun bâtiment" : planete.getTBatiment()[1].getNom(), skin);
		name2.setName("name2");
		//Description du bâtiment
//		ScrollPane description2 = new AffichageDescriptionBatimentPlanete(planete.getTBatiment()[1], skin);
//		description2.setName("description2");
		//Action a effectuer
		Button action2;
		if (planete.getTBatiment()[1] == null) {
			//Si pas de bâtiment, on peut en créer un
			action2 = new ButtonAddBatimentPlanete(planete, 1, skin);
		} else {
			//Sinon, on peut le détruire
			action2 = new ButtonRemoveBatimentPlanete(planete, 1, skin);
		}
		action2.setName("action2");
		
		add(name2/*, description2*/, action2);
	}

	public void update(Planete planete, Skin skin) {
		if (planete.isReDrawBuild1()) {
			if (planete.getTBatiment()[0] == null) {
				((Label) findActor("name1")).setText("Aucun batiment");
//				((AffichageDescriptionBatimentPlanete) findActor("description1")).update(planete.getTBatiment()[0]);
				Button add = new ButtonAddBatimentPlanete(planete, 0, skin);
				add.setName("action1");
				getCell(findActor("action1")).setActor(add);
			} else {
				((Label) findActor("name1")).setText(planete.getTBatiment()[0].getNom());
//				((AffichageDescriptionBatimentPlanete) findActor("description1")).update(planete.getTBatiment()[0]);
				Button remove = new ButtonRemoveBatimentPlanete(planete, 0, skin);
				remove.setName("action1");
				getCell(findActor("action1")).setActor(remove);
			}
			planete.setReDrawBuild1(false);
		} else if (planete.isReDrawBuild2()) {
			if (planete.getTBatiment()[1] == null) {
				((Label) findActor("name2")).setText("Aucun batiment");
//				((AffichageDescriptionBatimentPlanete) findActor("description2")).update(planete.getTBatiment()[1]);
				Button add = new ButtonAddBatimentPlanete(planete, 1, skin);
				add.setName("action2");
				getCell(findActor("action2")).setActor(add);
			} else {
				((Label) findActor("name2")).setText(planete.getTBatiment()[1].getNom());
//				((AffichageDescriptionBatimentPlanete) findActor("description2")).update(planete.getTBatiment()[1]);
				Button remove = new ButtonRemoveBatimentPlanete(planete, 1, skin);
				remove.setName("action2");
				getCell(findActor("action2")).setActor(remove);
			}
			planete.setReDrawBuild2(false);
		}
	}
}

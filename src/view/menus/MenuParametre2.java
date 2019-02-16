package view.menus;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import model.entity.player.EnumNation;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.EnumTypeCarte;
import model.parametre.EnumVictoire;
import model.parametre.Parametre;
import view.launcher.Project;

public class MenuParametre2 {
	private SplitPane panels;
	
	private Table joueurs;
	private SelectBox<EnumNation> nation;
	
	private Table parametres;
	private List<CheckBox> TVictoire;
	
	public MenuParametre2() {
		Project.stage.clear();
		

		nation = new SelectBox<EnumNation>(Project.skin);
		nation.setItems(EnumNation.values());
		
		joueurs = new Table(Project.skin);
		joueurs.align(Align.topLeft);
//		joueurs.setPosition(panels.getOriginX(), panels.getY());
//		joueurs.setSize(panels.getWidth() / 2, panels.getHeight());
		joueurs.add("Joueur").expand();
		joueurs.add(nation).expand();
		for (int i = 1; i < Project.parametre.getNbJoueur(); i++) {
			joueurs.row();
			joueurs.add("Ordinateur").expand();
			SelectBox<EnumNation> nation = new SelectBox<EnumNation>(Project.skin);
			nation.setItems(EnumNation.values());
			joueurs.add(nation).expand();
		}
		
		TextButton retour = new TextButton("Retour", Project.skin);
		retour.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 0;
			}
		});
		joueurs.row();
		joueurs.add(retour);
		
		TVictoire = new ArrayList<CheckBox>();
		for (EnumVictoire victoire : EnumVictoire.values()) {
			TVictoire.add(new CheckBox(victoire.toString(), Project.skin));
		}
		
		parametres = new Table(Project.skin);
		parametres.align(Align.left);
		parametres.add("Type de victoire").expand().left();
		for (CheckBox victoire : TVictoire) {
			parametres.add(victoire).expand();
		}
		parametres.row();
		SelectBox<EnumTypeCarte> typeCarte = new SelectBox<>(Project.skin);
		typeCarte.setItems(EnumTypeCarte.values());
		parametres.add("Type de carte").expand().left();
		parametres.add(typeCarte).right();
		parametres.row();
		SelectBox<EnumTailleCarte> tailleCarte = new SelectBox<EnumTailleCarte>(Project.skin);
		tailleCarte.setItems(EnumTailleCarte.values());
		parametres.add("Taille de la carte").expand().left();
		parametres.add(tailleCarte).expand().right();
		parametres.row();
		SelectBox<EnumAbondanceRessource> abondRess = new SelectBox<EnumAbondanceRessource>(Project.skin);
		abondRess.setItems(EnumAbondanceRessource.values());
		parametres.add("Abondance des ressources").expand().left();
		parametres.add(abondRess).expand().right();
		parametres.row();
		SelectBox<Integer> nbJoueur = new SelectBox<Integer>(Project.skin);
		Array<Integer> temp = new Array<Integer>();
		for (int i = 2; i <= 8; i++) {
			temp.add(i);
		}
		nbJoueur.setItems(temp);
		parametres.add("Nombre de joueurs").expand().left();
		parametres.add(nbJoueur).expand().right();
		parametres.row();
		Slider nbPlanMax = new Slider(5, 10, 1, false, Project.skin);
		parametres.add("Nombre de planète maximum par système").expand().left();
		parametres.add(nbPlanMax).expand().right();
		parametres.row();
		//TODO Vérifier la pertinence
		Slider nbAnoMax = new Slider(5, 10, 1, false, Project.skin);
		parametres.add("Nombre d'anomalie maximum par système").expand().left();
		parametres.add(nbAnoMax).expand().right();
		parametres.row();
		SelectBox<EnumTailleMapCombat> tailleCarteCombat = new SelectBox<EnumTailleMapCombat>(Project.skin);
		tailleCarteCombat.setItems(EnumTailleMapCombat.values());
		parametres.add("Taille de la carte de combat").expand().left();
		parametres.add(tailleCarteCombat).expand().right();
		parametres.row();
		TextButton valider = new TextButton("Valider", Project.skin);
		valider.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.parametre = new Parametre(null, abondRess.getSelected(), typeCarte.getSelected(), tailleCarte.getSelected(),
						nbJoueur.getSelected(), (int)nbAnoMax.getValue(), (int)nbPlanMax.getValue(), tailleCarteCombat.getSelected(), null);
				Project.change = true;
				Project.menu = 2;
				Project.affichageGalaxie = true;
			}
		});
		parametres.add(valider);
		
		
		panels = new SplitPane(joueurs, parametres, false, Project.skin);
		panels.setSize(Project.camera.viewportWidth, Project.camera.viewportHeight);
		panels.setPosition(0, Project.camera.viewportHeight - panels.getHeight());
//		panels.setFillParent(true);
		
		
		Project.stage.addActor(panels);
		
		
		System.out.println(joueurs.getX() + " " + joueurs.getY());
	}
}

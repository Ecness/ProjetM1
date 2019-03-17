package controller.confirm;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import view.launcher.Project;

public class ConfirmRetourMenuPrincipal extends Confirm {

	public ConfirmRetourMenuPrincipal(Skin skin) {
		super("Confirmation menu principal",
				"Retourner au menu principal ?",
				skin);

		addActionCancel(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				getParent().remove();
			}
			
		});
		
		addActionConfirm(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.pause = false;
				Project.change = true;
				Project.menu = 0;
				Project.affichageGalaxie = false;
				getParent().remove();
			}
			
		});
	}
}

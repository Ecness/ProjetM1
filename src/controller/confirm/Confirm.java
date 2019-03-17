package controller.confirm;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import view.launcher.Project;

public abstract class Confirm extends Dialog implements ConfirmInterface {

	public Confirm(String title, String content, Skin skin) {
		super(title, skin);
		setSize(Project.width / 4, Project.height / 4);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		
		text(content);
		
		TextButton cancel = new TextButton("Annuler", skin);
		cancel.setName("cancel");
		cancel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				getParent().remove();
			}
			
		});
		
		TextButton confirm = new TextButton("Confirmer", skin);
		confirm.setName("confirm");
		confirm.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				getParent().remove();
			}
			
		});
		
		button(cancel);
		button(confirm);
		
		Project.staticStage.addActor(this);
	}
	
	@Override
	public void addActionCancel(EventListener event) {
		findActor("cancel").addListener(event);
	}
	
	@Override
	public void addActionConfirm(EventListener event) {
		findActor("confirm").addListener(event);
	}
}

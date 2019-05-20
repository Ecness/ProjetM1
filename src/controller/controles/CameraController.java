package controller.controles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

import view.launcher.Project;

public abstract class CameraController {

	public static void controle() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
			Project.dynamicStage.getCamera().translate(-300*Gdx.graphics.getDeltaTime(), 0, 0);
//			for (Systeme sys : Project.galaxie.getListeSysteme()) {
//				sys.getBouton().moveBy(-300*Gdx.graphics.getDeltaTime(), 0);
//			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			Project.dynamicStage.getCamera().translate(300*Gdx.graphics.getDeltaTime(), 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			Project.dynamicStage.getCamera().translate(0, -300*Gdx.graphics.getDeltaTime(), 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
			Project.dynamicStage.getCamera().translate(0, 300*Gdx.graphics.getDeltaTime(), 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			Project.dynamicStage.getCamera().rotate(-0.5f, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			Project.dynamicStage.getCamera().rotate(0.5f, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			((OrthographicCamera)Project.dynamicStage.getCamera()).zoom += 0.01;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.X)) {
			((OrthographicCamera)Project.dynamicStage.getCamera()).zoom -= 0.01;
		}
		
		if (Gdx.input.isTouched()) {
			Gdx.input.setCursorCatched(true);
			if (Gdx.input.getDeltaX() < 0) {
				Project.dynamicStage.getCamera().translate(-300*Gdx.graphics.getDeltaTime(), 0, 0);
			}
			if (Gdx.input.getDeltaX() > 0) {
				Project.dynamicStage.getCamera().translate(300*Gdx.graphics.getDeltaTime(), 0, 0);
			}
			if (Gdx.input.getDeltaY() < 0) {
				Project.dynamicStage.getCamera().translate(0, 300*Gdx.graphics.getDeltaTime(), 0);
			}
			if (Gdx.input.getDeltaY() > 0) {
				Project.dynamicStage.getCamera().translate(0, -300*Gdx.graphics.getDeltaTime(), 0);
			}
		} else {
			Gdx.input.setCursorCatched(false);
		}
		
		//Limites de la caméra
		if (Project.cameraBound != null) {
			if (Project.dynamicStage.getCamera().position.x > Project.cameraBound.getWidth()) {
				Project.dynamicStage.getCamera().position.x = Project.cameraBound.getWidth();
			}
			if (Project.dynamicStage.getCamera().position.x < -Project.cameraBound.getWidth()) {
				Project.dynamicStage.getCamera().position.x = -Project.cameraBound.getWidth();
			}
			if (Project.dynamicStage.getCamera().position.y > Project.cameraBound.getHeight()) {
				Project.dynamicStage.getCamera().position.y =  Project.cameraBound.getHeight();
			}
			if (Project.dynamicStage.getCamera().position.y < -Project.cameraBound.getHeight()) {
				Project.dynamicStage.getCamera().position.y =  -Project.cameraBound.getHeight();
			}
		}
	}
}

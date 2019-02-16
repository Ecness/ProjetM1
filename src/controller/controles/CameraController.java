package controller.controles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import view.launcher.Project;

public abstract class CameraController {

	public static void controle() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
			Project.camera.translate(-300*Gdx.graphics.getDeltaTime(), 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			Project.camera.translate(300*Gdx.graphics.getDeltaTime(), 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			Project.camera.translate(0, -300*Gdx.graphics.getDeltaTime(), 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
			Project.camera.translate(0, 300*Gdx.graphics.getDeltaTime(), 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			Project.camera.rotate(-0.5f, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			Project.camera.rotate(0.5f, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			((OrthographicCamera)Project.camera).zoom += 0.01;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.X)) {
			((OrthographicCamera)Project.camera).zoom -= 0.01;
		}
		
		if (Project.clicked) {
			Gdx.input.setCursorCatched(true);
			if (Gdx.input.getDeltaX() < 0) {
				Project.camera.translate(-300*Gdx.graphics.getDeltaTime(), 0, 0);
			}
			if (Gdx.input.getDeltaX() > 0) {
				Project.camera.translate(300*Gdx.graphics.getDeltaTime(), 0, 0);
			}
			if (Gdx.input.getDeltaY() < 0) {
				Project.camera.translate(0, 300*Gdx.graphics.getDeltaTime(), 0);
			}
			if (Gdx.input.getDeltaY() > 0) {
				Project.camera.translate(0, -300*Gdx.graphics.getDeltaTime(), 0);
			}
		} else {
			Gdx.input.setCursorCatched(false);
		}
	}
}

package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import view.launcher.Project;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		config.foregroundFPS = 60;
		config.width = (int) screenSize.getWidth();
		config.height = (int) screenSize.getHeight();
		config.fullscreen = true;
		new LwjglApplication(new Project(), config);
	}
}

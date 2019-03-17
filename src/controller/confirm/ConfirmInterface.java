package controller.confirm;

import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface ConfirmInterface {
	/**
	 * Action à effectuer pour l'annulation
	 * 
	 * @param event	Action à effectuer
	 */
	void addActionCancel(EventListener event);
	
	/**
	 * Action à effectuer pour la confirmation
	 * 
	 * @param event	Action à effectuer
	 */
	void addActionConfirm(EventListener event);
}

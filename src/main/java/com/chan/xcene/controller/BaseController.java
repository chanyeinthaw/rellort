package com.chan.xcene.controller;

import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;

/**
 * FXML BaseController class
 */
abstract public class BaseController implements BaseControllerInterface {
    private boolean isModal;
    private HashMap<String, Object> extras;

    protected Stage currentStage;
    protected OnControllerResultHandler onControllerResultHandler;

    protected BaseController() {
        extras = new HashMap<>();
        isModal = false;
    }

    //region Library operations

    /**
     * Set on controller result handler
     * @param onControllerResultHandler - implementation of controller result handler
     */
    public void setOnControllerResultHandler(OnControllerResultHandler onControllerResultHandler) {
        this.onControllerResultHandler = onControllerResultHandler;
    }

    /**
     * Set current stage
     * @param currentStage - Stage
     */
    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    /**
     * Get if current controller is modal
     * @return boolean value
     */
    public boolean isModal() {
        return isModal;
    }

    /**
     * Set value of isModal
     * @param modal - boolean value true if modal
     */
    public void setModal(boolean modal) {
        isModal = modal;
    }

    //endregion

    //region Controller event handlers

    /**
     * On Layout loaded event
     * called after fxml is loaded
     * @param root Parent
     */
    public void onLayoutLoaded(Parent root) {
        currentStage.setScene(
                new Scene(
                        root,
                        currentStage.getWidth(),
                        currentStage.getHeight()
                )
        );
    }

    /**
     * Show currentStage and bind WINDOW Event handlers
     */
    public void show() {
        currentStage.setOnCloseRequest(this::onClose);
        currentStage.addEventHandler(WindowEvent.WINDOW_SHOWING, this::onShowing);
        currentStage.addEventHandler(WindowEvent.WINDOW_SHOWN, this::onShown);
        currentStage.addEventHandler(WindowEvent.WINDOW_HIDING, this::onHiding);
        currentStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, this::onHidden);

        if (isModal()) currentStage.showAndWait(); else currentStage.show();
    }

    //endregion

    //region Extra data

    /**
     * Put extra data with key
     * @param key - String key
     * @param extra - Object extra data
     */
    public void setExtra(String key, Object extra) {
        extras.put(key, extra);
    }

    /**
     * Get extra data with key
     * Return new object if key doesn't exists
     * @param key - String key
     * @return - Object extra or new Object
     */
    public Object getExtra(String key) {
        return hasExtra(key) ? extras.get(key) : new Object();
    }

    /**
     * Check if extra data with key exists
     * @param key - String key
     * @return boolean
     */
    public boolean hasExtra(String key) {
        return extras.containsKey(key);
    }

    //endregion
}

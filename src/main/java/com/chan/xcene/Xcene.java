package com.chan.xcene;

import com.chan.xcene.annotations.Layout;
import com.chan.xcene.controller.BaseController;
import com.chan.xcene.controller.BaseControllerInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Xcene {
    private static String LAYOUT_ROOT = "layouts/";

    /**
     * Set layout root resource directory
     * @param layoutDir - String
     */
    public static void setLayoutRoot(String layoutDir) {
        LAYOUT_ROOT = layoutDir + "/";
    }

    private String layoutRoot;
    private Class<? extends BaseController> controllerClass;
    private BaseController controller;
    private Stage currentStage;

    private boolean isModal;

    /**
     * Create instance of Xcene in new Stage
     * @param controllerClass - Class
     */
    public Xcene(Class<? extends BaseController> controllerClass) {
        this(controllerClass, new Stage());
    }

    /**
     * Create instance of Xcene in existing Stage
     * @param controllerClass - Class
     * @param startStage - Stage
     */
    public Xcene(Class<? extends BaseController> controllerClass, Stage startStage) {
        this(controllerClass, startStage, LAYOUT_ROOT);
    }

    /**
     * Create instance of Xcene
     * @param controllerClass - Class
     * @param startStage - Stage
     * @param layoutRoot - Layout root dir
     */
    public Xcene(Class<? extends BaseController> controllerClass, Stage startStage, String layoutRoot) {
        this.controllerClass = controllerClass;
        this.currentStage = startStage;
        this.layoutRoot = layoutRoot;

        isModal = false;
        controller = null;
    }

    /**
     * Make current fxml scene as modal window/dialog window
     * @param owner - parent stage
     */
    public void makeModelWindow(Stage owner) {
        currentStage.setResizable(false);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.initOwner(owner);

        isModal = true;
    }

    /**
     * Initialize base controller
     * @return Parent root
     * @throws IOException - Layout not found exception
     */
    private Parent initializeBaseController() throws IOException {
        FXMLLoader layoutLoader = getLayoutLoader();
        Parent root = layoutLoader.load();

        if (controller == null) {

            controller = layoutLoader.getController();
            controller.setModal(isModal);
            controller.setCurrentStage(currentStage);
            controller.onLayoutLoaded(root);
        }

        return root;
    }

    /**
     * Get FXML Loader for defined layout
     * @return FXMLLoader
     */
    private FXMLLoader getLayoutLoader() {
        return new FXMLLoader(
                ResLoader.getResource(layoutRoot + controllerClass.getAnnotation(Layout.class).value() + ".fxml")
        );
    }

    /**
     * Start current fxml scene in new window
     * @return Controller instance
     * @throws IOException - fxml file not found exception
     */
    public BaseController start() throws IOException {
        initializeBaseController();

        return controller;
    }

    /**
     * Start current fxml scene in parent scene/pane (without opening new window)
     * @param pane - Parent pane
     * @return Controller instance
     * @throws IOException - fxml file not found exception
     */
    public BaseController start(Pane pane) throws IOException {
        pane.getChildren().addAll(initializeBaseController());

        return controller;
    }

    /**
     * Start current fxml scene in other layout than scene/pane (without opening new window)
     * @param parent - Parent Jerk Controller
     * @param handler - StartInHandler
     * @return Controller instance
     * @throws IOException - fxml file not found exception
     */
    public <T> BaseController start(T parent, OnStartHandler<T> handler) throws IOException {
        handler.onStart(parent, initializeBaseController());

        return controller;
    }

    /**
     * On start handler
     * @param <T>
     */
    public interface OnStartHandler<T> {
        void onStart(T parentRoot, Parent childRoot);
    }
}

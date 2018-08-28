package com.chan.xcene.controller;

import javafx.stage.WindowEvent;

public interface BaseControllerInterface {
    void onShown(WindowEvent event);

    default void onClose(WindowEvent event) {};
    default void onHidden(WindowEvent event) {};
    default void onHiding(WindowEvent event) {};
    default void onShowing(WindowEvent event) {};
}

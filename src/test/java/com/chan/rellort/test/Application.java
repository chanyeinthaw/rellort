package com.chan.rellort.test;

import com.chan.rellort.Rellort;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Rellort root =  new Rellort(TestController.class, primaryStage);

        root.start().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

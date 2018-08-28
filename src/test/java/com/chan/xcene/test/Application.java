package com.chan.xcene.test;

import com.chan.xcene.Xcene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Xcene root =  new Xcene(TestController.class, primaryStage);

        root.start().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

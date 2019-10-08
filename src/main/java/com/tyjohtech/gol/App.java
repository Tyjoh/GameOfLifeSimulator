package com.tyjohtech.gol;

import com.tyjohtech.gol.viewmodel.ApplicationViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        ApplicationViewModel applicationViewModel = ApplicationViewModel.defaults();

        MainView mainView = new MainView(applicationViewModel);
        Scene scene = new Scene(mainView, 640, 480);
        stage.setScene(scene);
        stage.show();

        mainView.draw();
    }

    public static void main(String[] args) {
        launch();
    }

}
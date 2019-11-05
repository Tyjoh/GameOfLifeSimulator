package com.tyjohtech.gol;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;
import com.tyjohtech.gol.view.SettingsPane;
import com.tyjohtech.gol.view.SimulationCanvas;
import com.tyjohtech.gol.viewmodel.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        ApplicationViewModel appViewModel = new ApplicationViewModel(ApplicationState.EDITING);
        BoardViewModel boardViewModel = new BoardViewModel();
        Board board = new BoundedBoard(46, 36);
        EditorViewModel editorViewModel = new EditorViewModel(boardViewModel, board);
        SimulationViewModel simulationViewModel = new SimulationViewModel(boardViewModel);

        appViewModel.listenToAppState(editorViewModel::onAppStateChanged);
        appViewModel.listenToAppState(simulationViewModel::onAppStateChanged);

        boardViewModel.setBoard(board);

        SimulationCanvas simulationCanvas = new SimulationCanvas(editorViewModel, boardViewModel);
        Toolbar toolbar = new Toolbar(editorViewModel, appViewModel, simulationViewModel);
        InfoBar infoBar = new InfoBar(editorViewModel);

        SettingsPane settingsPane = new SettingsPane(simulationViewModel);
        settingsPane.setPrefWidth(300);

        MainView mainView = new MainView(editorViewModel);
        mainView.setTop(toolbar);
        mainView.setCenter(simulationCanvas);
        mainView.setBottom(infoBar);
        mainView.setRight(settingsPane);

        Scene scene = new Scene(mainView, 1200, 800);
        scene.getStylesheets().add("file:/Users/tyjoh/IdeaProjects/GameOfLifeSimulator/src/main/resources/style.css");
        scene.setOnKeyPressed(event -> {
            if (event.isShiftDown() && event.getCode() == KeyCode.R) {
                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:/Users/tyjoh/IdeaProjects/GameOfLifeSimulator/src/main/resources/style.css");
                System.out.println("Reloaded style");
            }
        });
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
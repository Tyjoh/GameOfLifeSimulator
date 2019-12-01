package com.tyjohtech.gol;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.view.SimulationCanvas;
import com.tyjohtech.gol.viewmodel.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();

        ApplicationViewModel appViewModel = new ApplicationViewModel();
        BoardViewModel boardViewModel = new BoardViewModel();
        Board board = new BoundedBoard(20, 12);

        EditorViewModel editorViewModel = new EditorViewModel(boardViewModel, board);
        eventBus.listenFor(DrawModeEvent.class, editorViewModel::handle);
        eventBus.listenFor(BoardEvent.class, editorViewModel::handle);

        SimulationViewModel simulationViewModel = new SimulationViewModel(boardViewModel, appViewModel, editorViewModel);
        eventBus.listenFor(SimulatorEvent.class, simulationViewModel::handle);

        appViewModel.getApplicationState().listen(editorViewModel::onAppStateChanged);

        boardViewModel.getBoard().set(board);

        SimulationCanvas simulationCanvas = new SimulationCanvas(editorViewModel, boardViewModel, eventBus);
        Toolbar toolbar = new Toolbar(eventBus);
        InfoBar infoBar = new InfoBar(editorViewModel);

        MainView mainView = new MainView(editorViewModel);
        mainView.setTop(toolbar);
        mainView.setCenter(simulationCanvas);
        mainView.setBottom(infoBar);

        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
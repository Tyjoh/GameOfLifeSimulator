package com.tyjohtech.gol;

import com.tyjohtech.gol.model.BoundedBoard;
import com.tyjohtech.gol.model.StandardRule;
import com.tyjohtech.gol.viewmodel.application.ApplicationState;
import com.tyjohtech.gol.viewmodel.application.ApplicationViewModel;
import com.tyjohtech.gol.viewmodel.board.BoardStateViewModel;
import com.tyjohtech.gol.viewmodel.editor.DrawMode;
import com.tyjohtech.gol.viewmodel.editor.EditorViewModel;
import com.tyjohtech.gol.viewmodel.simulation.SimulationViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        ApplicationViewModel applicationViewModel = new ApplicationViewModel();
        BoardStateViewModel boardStateViewModel = new BoardStateViewModel();
        EditorViewModel editorViewModel = new EditorViewModel(boardStateViewModel, applicationViewModel);
        Simulator simulator = new Simulator(boardStateViewModel, new StandardRule());
        SimulationViewModel simulationViewModel = new SimulationViewModel(simulator, applicationViewModel);

        MainView mainView = new MainView(applicationViewModel, boardStateViewModel, editorViewModel, simulationViewModel);
        Scene scene = new Scene(mainView, 640, 480);
        stage.setScene(scene);
        stage.show();

        editorViewModel.setInitialBoard(new BoundedBoard(10, 10));
        editorViewModel.setDrawMode(DrawMode.PENCIL);
        applicationViewModel.setApplicationState(ApplicationState.EDITING);
    }

    public static void main(String[] args) {
        launch();
    }

}
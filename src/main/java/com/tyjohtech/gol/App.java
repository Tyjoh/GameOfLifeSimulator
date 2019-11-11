package com.tyjohtech.gol;

import com.tyjohtech.gol.command.CommandProcessor;
import com.tyjohtech.gol.command.RootCommandProcessor;
import com.tyjohtech.gol.command.SimulationCanvasEventHandler;
import com.tyjohtech.gol.editor.BoardEditor;
import com.tyjohtech.gol.editor.PencilTool;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.view.KeyValuePane;
import com.tyjohtech.gol.view.SimulationCanvas;
import com.tyjohtech.gol.viewmodel.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Board board = new BoundedBoard(45, 37);

        ApplicationViewModel appViewModel = new ApplicationViewModel(ApplicationState.EDITING);
        BoardViewModel boardViewModel = new BoardViewModel();
        EditorViewModel editorViewModel = new EditorViewModel(boardViewModel, board);
        SimulationViewModel simulationViewModel = new SimulationViewModel(boardViewModel);

        appViewModel.listenToAppState(editorViewModel::onAppStateChanged);
        appViewModel.listenToAppState(simulationViewModel::onAppStateChanged);

        CommandProcessor commandProcessor = new RootCommandProcessor();

        BoardEditor boardEditor = new BoardEditor();
        boardEditor.getTool().set(new PencilTool(commandProcessor));
        boardEditor.getDrawState().set(CellState.ALIVE);
        boardEditor.getBoard().set(board);
        stage.addEventHandler(KeyEvent.ANY, new ToolSelectHandler(boardEditor, commandProcessor));

        boardViewModel.getCurrentBoard().bindTo(boardEditor.getBoard());
        boardViewModel.getCursorPosition().bindTo(boardEditor.getCursor());
        boardViewModel.getSelection().bindTo(boardEditor.getSelection());

        SimulationCanvas simulationCanvas = new SimulationCanvas(boardViewModel);
        simulationCanvas.addEventHandler(MouseEvent.ANY, new SimulationCanvasEventHandler(boardEditor, boardViewModel));

        Toolbar toolbar = new Toolbar(editorViewModel, appViewModel, simulationViewModel);
        InfoBar infoBar = new InfoBar(editorViewModel);

        KeyValuePane settingsPane = new KeyValuePane();
        settingsPane.setPrefWidth(300);

        settingsPane.add("Setting 1", new Slider(1, 10, 3));
        settingsPane.add("Setting 2", new CheckBox());
        settingsPane.add("Setting 3", new TextField("4352"));

        MainView mainView = new MainView(editorViewModel);
        mainView.setTop(toolbar);
        mainView.setCenter(simulationCanvas);
        mainView.setBottom(infoBar);
        mainView.setRight(settingsPane);

        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
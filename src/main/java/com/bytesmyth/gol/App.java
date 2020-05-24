package com.bytesmyth.gol;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
//        EventBus eventBus = new EventBus();
//        StateRegistry stateRegistry = new StateRegistry();
//        CommandExecutor commandExecutor = new CommandExecutor(stateRegistry);
//
//        ApplicationStateManager appViewModel = new ApplicationStateManager();
//        BoardViewModel boardViewModel = new BoardViewModel();
//        Board board = new BoundedBoard(20, 12);
//
//        EditorState editorState = new EditorState(board);
//        stateRegistry.registerState(EditorState.class, editorState);
//
//        Editor editor = new Editor(editorState, commandExecutor);
//        eventBus.listenFor(DrawModeEvent.class, editor::handle);
//        eventBus.listenFor(BoardEvent.class, editor::handle);
//        editorState.getCursorPosition().listen(cursorPosition -> {
//            boardViewModel.getCursorPosition().set(cursorPosition);
//        });
//
//        SimulatorState simulatorState = new SimulatorState(board);
//        stateRegistry.registerState(SimulatorState.class, simulatorState);
//
//        Simulator simulator = new Simulator(appViewModel, simulatorState, commandExecutor);
//        eventBus.listenFor(SimulatorEvent.class, simulator::handle);
//        editorState.getEditorBoard().listen(editorBoard -> {
//            simulatorState.getBoard().set(editorBoard);
//            boardViewModel.getBoard().set(editorBoard);
//        });
//        simulatorState.getBoard().listen(simulationBoard -> {
//            boardViewModel.getBoard().set(simulationBoard);
//        });
//
//        appViewModel.getApplicationState().listen(editor::onAppStateChanged);
//        appViewModel.getApplicationState().listen(newState -> {
//            if (newState == ApplicationState.EDITING) {
//                simulatorState.getBoard().set(editorState.getEditorBoard().get());
//                boardViewModel.getBoard().set(editorState.getEditorBoard().get());
//            }
//        });
//
//        boardViewModel.getBoard().set(board);
//
//        SimulationCanvas simulationCanvas = new SimulationCanvas(boardViewModel, eventBus);
//        Toolbar toolbar = new Toolbar(eventBus);
//
//        InfoBarViewModel infoBarViewModel = new InfoBarViewModel();
//        editorState.getCursorPosition().listen(cursorPosition -> {
//            infoBarViewModel.getCursorPosition().set(cursorPosition);
//        });
//        editorState.getDrawMode().listen(drawMode -> {
//            infoBarViewModel.getCurrentDrawMode().set(drawMode);
//        });
//        InfoBar infoBar = new InfoBar(infoBarViewModel);
//
//        MainView mainView = new MainView(eventBus);
//        mainView.setTop(toolbar);
//        mainView.setCenter(simulationCanvas);
//        mainView.setBottom(infoBar);
//
//        Scene scene = new Scene(mainView, 1200, 800);
//        stage.setScene(scene);
//        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
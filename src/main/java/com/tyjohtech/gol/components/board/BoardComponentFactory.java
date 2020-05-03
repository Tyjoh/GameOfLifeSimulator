package com.tyjohtech.gol.components.board;

import com.tyjohtech.app.view.SimulationCanvas;
import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.components.editor.EditorState;
import com.tyjohtech.gol.model.BoundedBoard;

public class BoardComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);

        BoardViewModel boardViewModel = new BoardViewModel();
        boardViewModel.getBoard().set(boardState.getBoardProperty().get());
        boardState.getBoardProperty().listen(boardViewModel.getBoard()::set);
        editorState.getCursorPosition().listen(boardViewModel.getCursorPosition()::set);

        SimulationCanvas canvas = new SimulationCanvas(boardViewModel, context.getEventBus());
        context.getMainView().addCenter(canvas);
    }

    @Override
    public void createState(ApplicationContext context) {
        BoardState boardState = new BoardState();
        boardState.getBoardProperty().set(new BoundedBoard(context.getBoardWidth(), context.getBoardHeight()));
        context.getStateRegistry().registerState(BoardState.class, boardState);
    }

}

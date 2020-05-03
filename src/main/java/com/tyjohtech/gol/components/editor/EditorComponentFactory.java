package com.tyjohtech.gol.components.editor;

import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.components.board.ApplicationState;
import com.tyjohtech.gol.components.board.BoardState;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;

public class EditorComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Editor editor = new Editor(editorState, context.getCommandExecutor());
        context.getEventBus().listenFor(BoardEvent.class, editor::handle);
        context.getEventBus().listenFor(DrawModeEvent.class, editor::handle);

        editorState.getEditorBoard().listen(board -> boardState.getBoardProperty().set(board.copy()));

        boardState.getApplicationState().listen(editor::onAppStateChanged);
        boardState.getApplicationState().listen(state -> {
            if (state == ApplicationState.EDITING) {
                boardState.getBoardProperty().set(editorState.getEditorBoard().get().copy());
            }
        });
    }

    @Override
    public void createState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWidth(), context.getBoardHeight());
        EditorState editorState = new EditorState(board);
        context.getStateRegistry().registerState(EditorState.class, editorState);
    }

}

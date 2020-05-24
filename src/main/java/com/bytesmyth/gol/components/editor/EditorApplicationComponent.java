package com.bytesmyth.gol.components.editor;

import com.bytesmyth.gol.ApplicationComponent;
import com.bytesmyth.gol.ApplicationContext;
import com.bytesmyth.gol.components.board.BoardState;
import com.bytesmyth.gol.components.simulator.SimulatorEvent;
import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.model.BoundedBoard;

public class EditorApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Editor editor = new Editor(editorState, context.getCommandExecutor());
        context.getEventBus().listenFor(DrawModeEvent.class, editor::handle);
        context.getEventBus().listenFor(BoardEvent.class, editor::handle);
        context.getEventBus().listenFor(SimulatorEvent.class, editor::handleSimulatorEvent);

        context.getEventBus().listenFor(SimulatorEvent.class, event -> {
            if (event.getEventType() == SimulatorEvent.Type.RESET) {
                boardState.getBoard().set(editorState.getEditorBoard().get());
            }
        });

        editorState.getEditorBoard().listen(boardState.getBoard()::set);

        ToolDrawLayer toolDrawLayer = new ToolDrawLayer(editorState);
        context.getMainView().addDrawLayer(toolDrawLayer);
    }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWidth(), context.getBoardHeight());
        EditorState editorState = new EditorState(board);
        context.getStateRegistry().registerState(EditorState.class, editorState);
    }
}

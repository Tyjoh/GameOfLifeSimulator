package com.tyjohtech.gol.components.editor;

import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.components.board.BoardState;
import com.tyjohtech.gol.components.editor.drawlayer.CurrentEditDrawLayer;
import com.tyjohtech.gol.components.editor.drawlayer.ToolDrawLayer;
import com.tyjohtech.gol.components.editor.state.EditorState;
import com.tyjohtech.gol.components.editor.tool.PencilTool;
import com.tyjohtech.gol.components.editor.tool.Tool;
import com.tyjohtech.gol.components.simulator.SimulatorEvent;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;
import com.tyjohtech.gol.model.CellState;

import java.util.HashMap;
import java.util.Map;

public class EditorComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Map<String, Tool> tools = new HashMap<>();
        tools.put("pencil", new PencilTool(editorState, context.getCommandExecutor()));

        Editor editor = new Editor(editorState, tools, context.getCommandExecutor());
        context.getEventBus().listenFor(BoardEvent.class, editor::handle);
        context.getEventBus().listenFor(DrawModeEvent.class, editor::handle);
        context.getEventBus().listenFor(SimulatorEvent.class, editor::handleSimulatorEvent);

        editorState.getBoardState().listen(editorBoardState -> {
            boardState.setBoard(editorBoardState.getBoard().copy());
        });

        ToolDrawLayer toolDrawLayer = new ToolDrawLayer(editorState.getToolState());
        CurrentEditDrawLayer editDrawLayer = new CurrentEditDrawLayer(editorState.getEditState());
        context.getMainView().addDrawLayer(toolDrawLayer);
        context.getMainView().addDrawLayer(editDrawLayer);
    }

    @Override
    public void createState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWidth(), context.getBoardHeight());

        EditorState editorState = new EditorState();
        editorState.getBoardState().setBoard(board);
        editorState.getToolState().setDrawMode(CellState.ALIVE);
        editorState.getToolState().setCurrentTool("pencil");
        context.getStateRegistry().registerState(EditorState.class, editorState);
    }

}

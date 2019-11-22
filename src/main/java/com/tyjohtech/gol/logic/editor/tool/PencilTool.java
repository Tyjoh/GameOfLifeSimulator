package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.logic.editor.event.ToolActionEvent;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.command.CommandProcessor;

public class PencilTool implements EditorTool {

    private EditorState editorState;
    private CommandProcessor commandProcessor;

    private Stroke currentStroke;

    public PencilTool(EditorState editorState, CommandProcessor commandProcessor) {
        this.editorState = editorState;
        this.commandProcessor = commandProcessor;
        this.currentStroke = new Stroke();
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    public BoardRegion getToolRegion() {
        CellPosition cursorPosition = editorState.getCursor().get();
        return new BoardRegion(cursorPosition, cursorPosition);
    }

    @Override
    public void handle(ToolActionEvent actionEvent) {
        CellPosition cursorPosition = editorState.getCursor().get();
        Board board = editorState.getBoard().get();
        CellState newState = editorState.getDrawState().get();
        CellState oldState = editorState.getBoard().get().getState(cursorPosition.getX(), cursorPosition.getY());

        StrokePoint strokePoint = new StrokePoint(cursorPosition, newState, oldState);
        currentStroke.add(strokePoint);

        board.setState(cursorPosition.getX(), cursorPosition.getY(), newState);
        editorState.getBoard().pump();

        if (actionEvent.getActionType() == ToolActionEvent.Type.END) {
            commandProcessor.push(new StrokeCommand(editorState.getBoard(), currentStroke.clone()));
            currentStroke.clear();
        }
    }
}

package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public class PencilTool implements EditorTool {

    private EditorState editorState;

    public PencilTool(EditorState editorState) {
        this.editorState = editorState;
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
    public Command createCommand() {
        CellPosition cursorPosition = editorState.getCursor().get();
        return new PencilCommand(editorState.getBoard(), cursorPosition, editorState.getDrawState().get());
    }

    private static class PencilCommand implements Command {
        private Property<Board> board;
        private CellPosition position;
        private CellState prevState;
        private CellState newState;

        private PencilCommand(Property<Board> board, CellPosition position, CellState newState) {
            this.board = board;
            this.position = position;
            this.newState = newState;
        }

        @Override
        public void execute() {
            Board board = this.board.get();
            prevState = board.getState(position.getX(), position.getY());
            board.setState(position.getX(), position.getY(), newState);
            this.board.pump();
        }

        @Override
        public void undo() {
            Board board = this.board.get();
            board.setState(position.getX(), position.getY(), prevState);
            this.board.pump();
        }
    }
}

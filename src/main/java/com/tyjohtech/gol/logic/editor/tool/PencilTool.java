package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.model.board.*;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public class PencilTool implements EditorTool {

    private Property<BoardRegion> areaOfEffect = new Property<>();
    private Property<BoardMask> boardMask = new Property<>();
    private Property<Board> board;
    private Property<CellState> drawState;

    public PencilTool(Property<Board> board, Property<CellPosition> cursorPosition, Property<CellState> drawState) {
        this.board = board;
        this.drawState = drawState;

        this.boardMask.set(new BoardMask(1, 1));
        this.boardMask.get().set(0, 0, true);

        cursorPosition.listen(this::cursorPositionChanged);
    }

    private void cursorPositionChanged(CellPosition cursorPosition) {
        this.areaOfEffect.set(new BoardRegion(cursorPosition, cursorPosition));
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public Property<BoardMask> getMask() {
        return boardMask;
    }

    @Override
    public Property<BoardRegion> getAreaOfEffect() {
        return areaOfEffect;
    }

    @Override
    public Command createCommand() {
        return new PencilCommand(board, areaOfEffect.get().getTopLeft(), drawState.get());
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

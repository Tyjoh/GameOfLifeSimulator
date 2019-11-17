package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.model.board.*;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public class BrushCommand implements Command {

    private Property<Board> board;
    private BoardRegion brushRegion;
    private CellState newState;
    private BoardMask boardMask;
    private Board prevStates;

    public BrushCommand(Property<Board> board, BoardRegion brushRegion, CellState newState, BoardMask boardMask) {
        this.board = board;
        this.brushRegion = brushRegion;
        this.newState = newState;
        this.boardMask = boardMask;
    }

    @Override
    public void execute() {
        Board board = this.board.get();
        prevStates = new BoundedBoard(brushRegion.getWidth(), brushRegion.getHeight());

        brushRegion.iterate((x, y, rx, ry) -> {
            CellState prev = board.getState(x, y);
            if (boardMask.isSet(rx, ry)) {
                prevStates.setState(rx, ry, prev);
                board.setState(x, y, newState);
            } else {
                prevStates.setState(rx, ry, null);
            }
        });

        this.board.pump();
    }

    @Override
    public void undo() {
        Board board = this.board.get();
        brushRegion.iterate((x, y, rx, ry) -> {
            if (boardMask.isSet(rx, ry)) {
                CellState prev = prevStates.getState(rx, ry);
                board.setState(x, y, prev);
            }
        });
        this.board.pump();
    }
}

package com.tyjohtech.gol.editor;

import com.tyjohtech.gol.command.Command;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.property.Property;

public class BoardPencilCommand implements Command {

    private Property<Board> boardProperty;
    private CellPosition cellPosition;
    private CellState oldState;
    private CellState newState;

    public BoardPencilCommand(Property<Board> boardProperty, CellPosition cellPosition, CellState newState) {
        this.boardProperty = boardProperty;
        this.cellPosition = cellPosition;
        this.newState = newState;
    }

    @Override
    public void execute() {
        Board board = boardProperty.get();
        oldState = board.getState(cellPosition.getX(), cellPosition.getY());
        board.setState(cellPosition.getX(), cellPosition.getY(), newState);
        boardProperty.set(board);
    }

    @Override
    public void undo() {
        Board board = boardProperty.get();
        board.setState(cellPosition.getX(), cellPosition.getY(), oldState);
        boardProperty.set(board);
    }
}

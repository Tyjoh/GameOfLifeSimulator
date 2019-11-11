package com.tyjohtech.gol.editor;

import com.tyjohtech.gol.command.Command;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.property.Property;

public class BoardBrushCommand implements Command {

    private Property<Board> board;
    private CellPosition position;
    private CellState newState;
    private int diam = 5;

    private boolean[][] kernel;
    private CellState[][] prevStates;

    public BoardBrushCommand(Property<Board> board, CellPosition position, CellState newState) {
        this.board = board;
        this.position = position;
        this.newState = newState;

        this.kernel = new boolean[diam][diam];

        int r = diam / 2;

        for (int x = 0; x < diam; x++) {
            int dx = Math.abs(r - x);
            for (int y = 0; y < diam; y++) {
                int dy = Math.abs(r - y);
                int dist = dx + dy;
                if (dist <= r) {
                    kernel[x][y] = true;
                }
            }
        }
    }

    @Override
    public void execute() {
        Board board = this.board.get();

        prevStates = new CellState[diam][diam];
        int r = diam / 2;
        for (int x = 0; x < diam; x++) {
            for (int y = 0; y < diam; y++) {
                int bx = x + position.getX() - r;
                int by = y + position.getY() - r;

                prevStates[x][y] = board.getState(bx, by);
                if (kernel[x][y]) {
                    board.setState(bx, by, newState);
                }
            }
        }

        this.board.set(board);
    }

    @Override
    public void undo() {
        Board board = this.board.get();
        int r = diam / 2;
        for (int x = 0; x < diam; x++) {
            for (int y = 0; y < diam; y++) {
                int bx = x + position.getX() - r;
                int by = y + position.getY() - r;

                board.setState(bx, by, prevStates[x][y]);
            }
        }
        this.board.set(board);
    }
}

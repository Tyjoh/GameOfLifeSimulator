package com.tyjohtech.gol.components.board;

import com.tyjohtech.app.view.AbstractDrawLayer;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoardDrawLayer extends AbstractDrawLayer {

    private BoardState boardState;

    public BoardDrawLayer(BoardState boardState) {
        super(0);
        this.boardState = boardState;
        this.boardState.listen(board -> this.invalidate());
    }

    @Override
    public void render(GraphicsContext g, int startX, int startY, int endX, int endY) {
        Board board = boardState.getBoard();

        g.setFill(Color.BLACK);
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                if (board.getState(x, y) == CellState.ALIVE) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = startX; x < endX; x++) {
            g.strokeLine(x, 0, x, board.getHeight());
        }

        for (int y = startY; y < endY; y++) {
            g.strokeLine(0, y, board.getWidth(), y);
        }
    }
}

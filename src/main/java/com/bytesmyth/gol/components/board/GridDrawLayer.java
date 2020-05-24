package com.bytesmyth.gol.components.board;

import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.view.AbstractDrawLayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawLayer extends AbstractDrawLayer {

    private BoardState boardState;

    public GridDrawLayer(BoardState boardState) {
        this.boardState = boardState;
    }

    @Override
    public void draw(GraphicsContext g) {
        Board board = boardState.getBoard().get();

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= board.getWidth(); x++) {
            g.strokeLine(x, 0, x, board.getHeight());
        }

        for (int y = 0; y <= board.getHeight(); y++) {
            g.strokeLine(0, y, board.getWidth(), y);
        }
    }

    @Override
    public int getLayer() {
        return 10;
    }
}

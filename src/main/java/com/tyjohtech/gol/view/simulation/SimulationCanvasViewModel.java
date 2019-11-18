package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.property.Property;
import javafx.scene.transform.Affine;

public class SimulationCanvasViewModel {

    private Property<Affine> boardViewTransform;
    private Property<Board> board = new Property<>();
    private Property<String> activeTool = new Property<>();
    private Property<CellPosition> cursorPosition = new Property<>();

    public SimulationCanvasViewModel() {
        this.boardViewTransform = new Property<>();

        Affine a = new Affine();
        a.appendScale(200 / 10f, 200 / 10f);
        boardViewTransform.set(a);
    }

    public Property<Affine> getBoardViewTransform() {
        return boardViewTransform;
    }

    public Property<Board> getBoard() {
        return board;
    }

    public Property<String> getActiveTool() {
        return activeTool;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }
}

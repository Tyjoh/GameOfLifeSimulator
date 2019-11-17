package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.logic.editor.tool.EditorTool;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.property.Property;
import javafx.scene.transform.Affine;

public class SimulationCanvasViewModel {

    private Property<Affine> boardViewTransform;

    private Property<Board> currentBoard;
    private Property<CellPosition> cursorPosition;
    private Property<BoardRegion> selection;
    private Property<EditorTool> selectedTool = new Property<>();

    public SimulationCanvasViewModel() {
        this.currentBoard = new Property<>();
        this.cursorPosition = new Property<>();
        this.selection = new Property<>();
        this.boardViewTransform = new Property<>();

        Affine a = new Affine();
        a.appendScale(200 / 10f, 200 / 10f);
        boardViewTransform.set(a);
    }

    public Property<Board> getCurrentBoard() {
        return currentBoard;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }

    public Property<BoardRegion> getSelection() {
        return selection;
    }

    public Property<Affine> getBoardViewTransform() {
        return boardViewTransform;
    }

    public Property<EditorTool> getSelectedTool() {
        return selectedTool;
    }
}

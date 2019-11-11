package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellRegion;
import com.tyjohtech.gol.property.Property;
import javafx.scene.transform.Affine;

public class BoardViewModel {

    private Property<Affine> boardViewTransform;

    private Property<Board> currentBoard;
    private Property<CellPosition> cursorPosition;
    private Property<CellRegion> selection;

    public BoardViewModel() {
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

    public Property<CellRegion> getSelection() {
        return selection;
    }

    public Property<Affine> getBoardViewTransform() {
        return boardViewTransform;
    }
}

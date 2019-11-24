package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.util.Property;

public class EditorViewModel {

    private Property<CellState> drawMode = new Property<>(CellState.ALIVE);

    private BoardViewModel boardViewModel;
    private Board editorBoard;
    private boolean drawingEnabled = true;

    public EditorViewModel(BoardViewModel boardViewModel, Board initialBoard) {
        this.boardViewModel = boardViewModel;
        this.editorBoard = initialBoard;
    }

    public void onAppStateChanged(ApplicationState state) {
        if (state == ApplicationState.EDITING) {
            drawingEnabled = true;
            this.boardViewModel.getBoard().set(editorBoard);
        } else {
            drawingEnabled = false;
        }
    }

    public void boardPressed(int simX, int simY) {
        if (drawingEnabled) {
            this.editorBoard.setState(simX, simY, drawMode.get());
            this.boardViewModel.getBoard().set(this.editorBoard);
        }
    }

    public Property<CellState> getDrawMode() {
        return drawMode;
    }
}

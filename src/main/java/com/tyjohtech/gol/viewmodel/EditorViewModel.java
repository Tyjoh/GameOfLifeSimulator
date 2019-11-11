package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.property.SimpleChangeListener;

import java.util.LinkedList;
import java.util.List;

public class EditorViewModel {

    private CellState drawMode = CellState.ALIVE;
    private List<SimpleChangeListener<CellState>> drawModeListeners;

    private BoardViewModel boardViewModel;
    private Board editorBoard;
    private boolean drawingEnabled = true;

    public EditorViewModel(BoardViewModel boardViewModel, Board initialBoard) {
        this.boardViewModel = boardViewModel;
        this.editorBoard = initialBoard;
        this.drawModeListeners = new LinkedList<>();
    }

    public void onAppStateChanged(ApplicationState state) {
        if (state == ApplicationState.EDITING) {
            drawingEnabled = true;
            this.boardViewModel.getCurrentBoard().set(editorBoard);
        } else {
            drawingEnabled = false;
        }
    }

    public void listenToDrawMode(SimpleChangeListener<CellState> listener) {
        drawModeListeners.add(listener);
    }

    public void setDrawMode(CellState drawMode) {
        this.drawMode = drawMode;
        notifyDrawModeListeners();
    }

    private void notifyDrawModeListeners() {
        for (SimpleChangeListener<CellState> drawModeListener : drawModeListeners) {
            drawModeListener.valueChanged(drawMode);
        }
    }

    public void boardPressed(int simX, int simY) {
        if (drawingEnabled) {
            this.editorBoard.setState(simX, simY, drawMode);
            this.boardViewModel.getCurrentBoard().set(editorBoard);
        }
    }
}

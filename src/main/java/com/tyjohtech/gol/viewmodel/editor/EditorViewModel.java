package com.tyjohtech.gol.viewmodel.editor;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.viewmodel.ChangeListener;
import com.tyjohtech.gol.viewmodel.application.ApplicationState;
import com.tyjohtech.gol.viewmodel.application.ApplicationViewModel;
import com.tyjohtech.gol.viewmodel.board.BoardStateViewModel;

import java.util.LinkedList;
import java.util.List;

public class EditorViewModel {

    private DrawMode drawMode;
    private List<ChangeListener<DrawMode>> drawModeListeners;

    private Board initialBoard;

    private BoardStateViewModel boardStateViewModel;

    public EditorViewModel(BoardStateViewModel boardStateViewModel, ApplicationViewModel applicationViewModel) {
        this.drawMode = null;
        this.boardStateViewModel = boardStateViewModel;
        this.drawModeListeners = new LinkedList<>();
        applicationViewModel.listenToApplicationState(this::onApplicationStateChange);
    }

    public void setDrawMode(DrawMode drawMode) {
        if (drawMode != this.drawMode) {
            this.drawMode = drawMode;
            notifyDrawModeListeners();
        }
    }

    public void listenToDrawMode(ChangeListener<DrawMode> listener) {
        this.drawModeListeners.add(listener);
    }

    public void edit(int boardX, int boardY) {
        if (drawMode == DrawMode.PENCIL) {
            setBoardState(boardX, boardY, CellState.ALIVE);
        } else if (drawMode == DrawMode.ERASER) {
            setBoardState(boardX, boardY, CellState.DEAD);
        }
    }

    public void setInitialBoard(Board board) {
        this.initialBoard = board;
        pushBoard();
    }

    private void setBoardState(int boardX, int boardY, CellState state) {
        this.initialBoard.setState(boardX, boardY, state);
        pushBoard();
    }

    private void notifyDrawModeListeners() {
        for (ChangeListener<DrawMode> drawModeListener : this.drawModeListeners) {
            drawModeListener.valueChanged(this.drawMode);
        }
    }

    private void onApplicationStateChange(ApplicationState appState) {
        if (appState == ApplicationState.EDITING) {
            pushBoard();
        }
    }

    private void pushBoard() {
        this.boardStateViewModel.updateBoard(this.initialBoard.copy());
    }

}

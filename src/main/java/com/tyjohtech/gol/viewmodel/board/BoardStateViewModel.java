package com.tyjohtech.gol.viewmodel.board;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.viewmodel.ChangeListener;
import com.tyjohtech.gol.viewmodel.application.ApplicationState;

import java.util.LinkedList;
import java.util.List;

public class BoardStateViewModel {

    private Board board;

    private List<ChangeListener<Board>> boardChangeListeners;
    private ApplicationState applicationState;

    public BoardStateViewModel() {
        this.boardChangeListeners = new LinkedList<>();
    }

    public void listenToBoard(ChangeListener<Board> listener) {
        this.boardChangeListeners.add(listener);
    }

    public void reset() {
    }

    public Board getBoard() {
        return board;
    }

    public void updateBoard(Board board) {
        this.board = board.copy();
        notifyBoardChanged();
    }

    private void notifyBoardChanged() {
        for (ChangeListener<Board> boardChangeListener : this.boardChangeListeners) {
            boardChangeListener.valueChanged(board);
        }
    }
}

package com.tyjohtech.gol;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.model.SimulationRule;
import com.tyjohtech.gol.viewmodel.board.BoardStateViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private BoardStateViewModel boardStateViewModel;
    private SimulationRule simulationRule;

    public Simulator(BoardStateViewModel boardStateViewModel, SimulationRule simulationRule) {
        this.boardStateViewModel = boardStateViewModel;
        this.simulationRule = simulationRule;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), (e) -> step()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void step() {
        Board currentBoard = this.boardStateViewModel.getBoard();
        Board nextBoard = transition(currentBoard);
        this.boardStateViewModel.updateBoard(nextBoard);
    }

    private Board transition(Board board) {
        Board nextState = board.copy();

        for (int y = 0; y < board.getWidth(); y++) {
            for (int x = 0; x < board.getHeight(); x++) {
                CellState newState = simulationRule.getNextState(x, y, board);
                nextState.setState(x, y, newState);
            }
        }

        return nextState;
    }
}

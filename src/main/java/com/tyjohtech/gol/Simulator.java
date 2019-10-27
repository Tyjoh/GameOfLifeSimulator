package com.tyjohtech.gol;

import com.tyjohtech.gol.viewmodel.BoardViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private BoardViewModel boardViewModel;
    private Simulation simulation;

    public Simulator(BoardViewModel boardViewModel, Simulation simulation) {
        this.boardViewModel = boardViewModel;
        this.simulation = simulation;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> this.doStep()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void doStep() {
        this.simulation.step();
        this.boardViewModel.setBoard(this.simulation.getBoard());
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }
}

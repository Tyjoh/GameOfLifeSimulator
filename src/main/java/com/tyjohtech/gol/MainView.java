package com.tyjohtech.gol;

import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.view.SimulationCanvas;
import com.tyjohtech.gol.viewmodel.ApplicationViewModel;
import com.tyjohtech.gol.viewmodel.BoardViewModel;
import com.tyjohtech.gol.viewmodel.EditorViewModel;
import com.tyjohtech.gol.viewmodel.SimulationViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

    private InfoBar infoBar;
    private Canvas canvas;

    private EditorViewModel editorViewModel;

    public MainView(ApplicationViewModel appViewModel, BoardViewModel boardViewModel, EditorViewModel editorViewModel, SimulationViewModel simulationViewModel) {
        this.editorViewModel = editorViewModel;

        this.setOnKeyPressed(this::onKeyPressed);

        SimulationCanvas simulationCanvas = new SimulationCanvas(editorViewModel, boardViewModel);
        VBox.setVgrow(simulationCanvas, Priority.ALWAYS);

        Toolbar toolbar = new Toolbar(editorViewModel, appViewModel, simulationViewModel);

        this.infoBar = new InfoBar(editorViewModel);
        this.infoBar.setCursorPosition(0, 0);

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(toolbar, simulationCanvas, spacer, infoBar);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.editorViewModel.setDrawMode(CellState.ALIVE);
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.editorViewModel.setDrawMode(CellState.DEAD);
        }
    }
}

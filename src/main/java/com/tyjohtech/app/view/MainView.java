package com.tyjohtech.app.view;

import com.tyjohtech.app.event.EventBus;
import com.tyjohtech.gol.components.editor.DrawModeEvent;
import com.tyjohtech.gol.model.CellState;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane implements View {

    private Toolbar toolbar;

    private SimulationCanvas canvas;
    private EventBus eventBus;

    public MainView(EventBus eventBus) {
        this.eventBus = eventBus;
        this.setOnKeyPressed(this::onKeyPressed);
    }

    @Override
    public void addCenter(SimulationCanvas simulationCanvas) {
        canvas = simulationCanvas;
        this.setCenter(this.canvas);
    }

    @Override
    public void setInfoBar(Node node) {
        this.setBottom(node);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            eventBus.emit(new DrawModeEvent(CellState.ALIVE));
        } else if (keyEvent.getCode() == KeyCode.E) {
            eventBus.emit(new DrawModeEvent(CellState.DEAD));
        }
    }
}

package com.tyjohtech.app.view;

import com.tyjohtech.app.event.EventBus;
import com.tyjohtech.gol.components.editor.DrawModeEvent;
import com.tyjohtech.gol.components.infobar.InfoBar;
import com.tyjohtech.gol.model.CellState;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane implements View {

    private SimulationCanvas canvas;

    private EventBus eventBus;

    public MainView(EventBus eventBus) {
        this.eventBus = eventBus;
        this.setOnKeyPressed(this::onKeyPressed);
    }

    @Override
    public void addDrawLayer(DrawLayer layer) {
        canvas.addLayer(layer);
    }

    @Override
    public void setInfoBar(InfoBar infoBar) {
        this.setBottom(infoBar);
    }

    @Override
    public void setCanvas(SimulationCanvas canvas) {
        this.canvas = canvas;
        this.setCenter(canvas);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            eventBus.emit(new DrawModeEvent(CellState.ALIVE));
        } else if (keyEvent.getCode() == KeyCode.E) {
            eventBus.emit(new DrawModeEvent(CellState.DEAD));
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        this.setTop(toolbar);
    }
}

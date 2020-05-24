package com.bytesmyth.gol.view;

import com.bytesmyth.app.event.EventBus;
import com.bytesmyth.gol.components.editor.DrawModeEvent;
import com.bytesmyth.gol.model.CellState;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

    private EventBus eventBus;

    private SimulationCanvas canvas;

    public MainView(EventBus eventBus) {
        this.eventBus = eventBus;

        canvas = new SimulationCanvas(eventBus);
        this.setCenter(canvas);

        Toolbar toolbar = new Toolbar(eventBus);
        this.setTop(toolbar);

        this.setOnKeyPressed(this::onKeyPressed);
    }

    public void addDrawLayer(DrawLayer drawLayer) {
        canvas.addDrawLayer(drawLayer);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.eventBus.emit(new DrawModeEvent(CellState.ALIVE));
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.eventBus.emit(new DrawModeEvent(CellState.DEAD));
        }
    }
}

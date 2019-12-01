package com.tyjohtech.gol.view;

import com.tyjohtech.gol.logic.DrawModeEvent;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.util.event.EventBus;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

    private EventBus eventBus;

    public MainView(EventBus eventBus) {
        this.eventBus = eventBus;
        this.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.eventBus.emit(new DrawModeEvent(CellState.ALIVE));
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.eventBus.emit(new DrawModeEvent(CellState.DEAD));
        }
    }
}

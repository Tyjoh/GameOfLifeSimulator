package com.tyjohtech.gol;

import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.viewmodel.EditorViewModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

    private EditorViewModel editorViewModel;

    public MainView(EditorViewModel editorViewModel) {
        this.editorViewModel = editorViewModel;
        this.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.editorViewModel.setDrawMode(CellState.ALIVE);
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.editorViewModel.setDrawMode(CellState.DEAD);
        }
    }
}

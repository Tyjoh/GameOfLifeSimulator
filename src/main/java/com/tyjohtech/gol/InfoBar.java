package com.tyjohtech.gol;

import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.viewmodel.EditorViewModel;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {

    private static String drawModeFormat = "Draw Mode: %s";
    private static String cursorPosFormat = "Cursor: (%d, %d)";

    private Label cursor;
    private Label editingTool;

    public InfoBar(EditorViewModel editorViewModel) {
        editorViewModel.listenToDrawMode(this::setDrawMode);
        this.getStyleClass().addAll("pane");
        this.editingTool = new Label();
        this.editingTool.getStyleClass().add("font-primary");
        this.cursor = new Label();
        this.cursor.getStyleClass().add("font-primary");

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.setCursorPosition(0, 0);

        this.getChildren().addAll(this.editingTool, spacer, this.cursor);

    }

    private void setDrawMode(CellState drawMode) {
        String drawModeString;
        if (drawMode == CellState.ALIVE) {
            drawModeString = "Drawing";
        } else {
            drawModeString = "Erasing";
        }

        this.editingTool.setText(String.format(drawModeFormat, drawModeString));
    }

    public void setCursorPosition(int x, int y) {
        this.cursor.setText(String.format(cursorPosFormat, x, y));
    }

}

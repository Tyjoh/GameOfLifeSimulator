package com.tyjohtech.gol;

import com.tyjohtech.gol.viewmodel.application.ApplicationViewModel;
import com.tyjohtech.gol.viewmodel.application.CursorPosition;
import com.tyjohtech.gol.viewmodel.editor.DrawMode;
import com.tyjohtech.gol.viewmodel.editor.EditorViewModel;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {

    private static String drawModeFormat = "Draw Mode: %s";
    private static String cursorPosFormat = "Cursor: (%d, %d)";

    private Label cursor;
    private Label editingTool;

    public InfoBar(ApplicationViewModel applicationViewModel, EditorViewModel editorViewModel) {
        applicationViewModel.listenToCursorPosition(this::updateCursorPosition);
        editorViewModel.listenToDrawMode(this::updateDrawMode);

        this.editingTool = new Label();
        this.cursor = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(this.editingTool, spacer, this.cursor);

    }

    private void updateDrawMode(DrawMode drawMode) {
        String drawModeString;
        if (drawMode == DrawMode.PENCIL) {
            drawModeString = "Drawing";
        } else if (drawMode == DrawMode.ERASER) {
            drawModeString = "Erasing";
        } else {
            drawModeString = "Unknown draw mode";
        }

        this.editingTool.setText(String.format(drawModeFormat, drawModeString));
    }

    private void updateCursorPosition(CursorPosition cursorPosition) {
        this.cursor.setText(String.format(cursorPosFormat, cursorPosition.getX(), cursorPosition.getY()));
    }

}

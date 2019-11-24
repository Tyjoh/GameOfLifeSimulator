package com.tyjohtech.gol.view.infobar;

import com.tyjohtech.gol.model.board.CellPosition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {

    private static String drawModeFormat = "Draw Mode: %s";
    private static String cursorPosFormat = "Cursor: (%d, %d)";

    private Label cursor;
    private Label editingTool;
    private Label drawState;
    private Label applicationState;

    private InfoBarViewModel infoBarViewModel;

    public InfoBar(InfoBarViewModel infoBarViewModel) {
        this.infoBarViewModel = infoBarViewModel;

        this.editingTool = new Label();
        this.drawState = new Label();
        this.cursor = new Label();
        this.applicationState = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.setCursorPosition(0, 0);
        this.getChildren().addAll(this.applicationState, spacer, this.cursor, this.editingTool, this.drawState);

        this.infoBarViewModel.getApplicationState().listen(this::updateAppState);
        this.infoBarViewModel.getCursorPosition().listen(this::updateCursorPosition);
        this.infoBarViewModel.getCurrentDrawState().listen(this::updateDrawState);
        this.infoBarViewModel.getCurrentTool().listen(this::updateActiveTool);
    }

    private void updateActiveTool(String tool) {
        this.editingTool.setText(String.format("\tTool: %s", tool));
    }

    private void updateDrawState(String drawMode) {
        this.drawState.setText(String.format("\tDraw mode: %s", drawMode));
    }

    private void updateCursorPosition(CellPosition position) {
        this.cursor.setText(String.format(cursorPosFormat, position.getX(), position.getY()));
    }

    private void updateAppState(String state) {
        this.applicationState.setText(String.format("App State: %s", state));
    }

    public void setCursorPosition(int x, int y) {
        this.cursor.setText(String.format(cursorPosFormat, x, y));
    }

}

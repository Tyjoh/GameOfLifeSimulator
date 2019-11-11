package com.tyjohtech.gol.command;

import com.tyjohtech.gol.editor.BoardEditor;
import com.tyjohtech.gol.editor.BoardEvent;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.viewmodel.BoardViewModel;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class SimulationCanvasEventHandler implements EventHandler<MouseEvent> {

    private BoardEditor editor;
    private BoardViewModel boardViewModel;

    public SimulationCanvasEventHandler(BoardEditor editor, BoardViewModel boardViewModel) {
        this.editor = editor;
        this.boardViewModel = boardViewModel;
    }

    private CellPosition toCellPosition(MouseEvent event) {
        Affine affine = boardViewModel.getBoardViewTransform().get();
        try {
            Point2D point2D = affine.inverseTransform(event.getX(), event.getY());
            return CellPosition.of((int) point2D.getX(), (int) point2D.getY());
        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException("This should not occur");
        }
    }

    private BoardEvent lastBoardEvent;

    @Override
    public void handle(MouseEvent event) {
        CellPosition cellPosition = toCellPosition(event);

        BoardEvent boardEvent;
        if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
            boardEvent = BoardEvent.hover(cellPosition);
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            boardEvent = BoardEvent.drag(cellPosition);
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            boardEvent = BoardEvent.release(cellPosition);
        } else {
            return;
        }

        if (!boardEvent.equals(lastBoardEvent)) {
            lastBoardEvent = boardEvent;
            editor.handle(boardEvent);
        }
    }
}

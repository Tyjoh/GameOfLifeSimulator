package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.logic.editor.BoardEvent;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.event.EventBus;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class SimulationCanvasEventHandler implements EventHandler<MouseEvent> {

    private SimulationCanvasViewModel simulationCanvasViewModel;
    private EventBus eventBus;

    public SimulationCanvasEventHandler(EventBus eventBus, SimulationCanvasViewModel simulationCanvasViewModel) {
        this.eventBus = eventBus;
        this.simulationCanvasViewModel = simulationCanvasViewModel;
    }

    private CellPosition toCellPosition(MouseEvent event) {
        Affine affine = simulationCanvasViewModel.getBoardViewTransform().get();
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
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED || event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            boardEvent = BoardEvent.drag(cellPosition);
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            boardEvent = BoardEvent.release(cellPosition);
        } else {
            return;
        }

        if (!boardEvent.equals(lastBoardEvent)) {
            lastBoardEvent = boardEvent;
            eventBus.emit(boardEvent);
        }
    }
}

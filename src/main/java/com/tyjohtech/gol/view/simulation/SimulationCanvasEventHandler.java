package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.ToolInvokeEvent;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.event.Event;
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

    Event lastEvent;
    CellPosition lastCursor;

    @Override
    public void handle(MouseEvent mouseEvent) {
        CellPosition cursorPosition = toCellPosition(mouseEvent);
        if (!cursorPosition.equals(lastCursor)) {
            lastCursor = cursorPosition;
            eventBus.emit(new CursorEvent(cursorPosition));
        }

        Event event;
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED ||
                mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            event = new ToolInvokeEvent(simulationCanvasViewModel.getActiveTool().get(), cursorPosition);
        } else {
            return;
        }

        if (!event.equals(lastEvent)) {
            lastEvent = event;
            eventBus.emit(event);
        }
    }
}

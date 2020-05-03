package com.tyjohtech.app.view;

import com.tyjohtech.app.event.EventBus;
import com.tyjohtech.gol.components.board.BoardViewModel;
import com.tyjohtech.gol.components.editor.BoardEvent;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class SimulationCanvas extends Pane {

    private Canvas canvas;

    private Affine affine;
    private BoardViewModel boardViewModel;

    private EventBus eventBus;

    public SimulationCanvas(BoardViewModel boardViewModel, EventBus eventBus) {
        this.boardViewModel = boardViewModel;
        this.eventBus = eventBus;
        boardViewModel.getBoard().listen(this::draw);
        boardViewModel.getCursorPosition().listen(cellPosition -> draw(boardViewModel.getBoard().get()));

        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleCursorMoved);

        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());

        this.getChildren().add(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);
    }

    private void handleCursorMoved(MouseEvent event) {
        CellPosition cursorPosition = this.getSimulationCoordinates(event);
        BoardEvent boardEvent = new BoardEvent(BoardEvent.Type.CURSOR_MOVED, cursorPosition);
        eventBus.emit(boardEvent);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        draw(boardViewModel.getBoard().get());
    }

    private void handleDraw(MouseEvent event) {
        CellPosition cursorPosition = this.getSimulationCoordinates(event);
        BoardEvent boardEvent = new BoardEvent(BoardEvent.Type.PRESSED, cursorPosition);
        eventBus.emit(boardEvent);
    }

    private CellPosition getSimulationCoordinates(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            return new CellPosition((int) simCoord.getX(), (int) simCoord.getY());
        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException("Non invertible transform");
        }
    }

    private void draw(Board board) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        this.drawSimulation(board);

        if (boardViewModel.getCursorPosition().isPresent()) {
            CellPosition cursor = boardViewModel.getCursorPosition().get();
            g.setFill(new Color(0.3, 0.3, 0.3, 0.5));
            g.fillRect(cursor.getX(), cursor.getY(), 1, 1);
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= board.getWidth(); x++) {
            g.strokeLine(x, 0, x, board.getHeight());
        }

        for (int y = 0; y <= board.getHeight(); y++) {
            g.strokeLine(0, y, board.getWidth(), y);
        }

    }

    private void drawSimulation(Board simulationToDraw) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        for (int x = 0; x < simulationToDraw.getWidth(); x++) {
            for (int y = 0; y < simulationToDraw.getHeight(); y++) {
                if (simulationToDraw.getState(x, y) == CellState.ALIVE) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }
}

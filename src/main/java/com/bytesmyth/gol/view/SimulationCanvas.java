package com.bytesmyth.gol.view;

import com.bytesmyth.app.event.EventBus;
import com.bytesmyth.gol.logic.editor.BoardEvent;
import com.bytesmyth.gol.model.CellPosition;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SimulationCanvas extends Pane {

    private Canvas canvas;

    private Affine affine;

    private EventBus eventBus;

    private List<DrawLayer> drawLayers = new LinkedList<>();

    public SimulationCanvas(EventBus eventBus) {
        this.eventBus = eventBus;

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

    public void addDrawLayer(DrawLayer drawLayer) {
        drawLayers.add(drawLayer);
        drawLayers.sort(Comparator.comparingInt(DrawLayer::getLayer));
        drawLayer.addInvalidationListener(this::draw);
    }

    private void handleCursorMoved(MouseEvent event) {
        CellPosition cursorPosition = this.getSimulationCoordinates(event);
        BoardEvent boardEvent = new BoardEvent(BoardEvent.Type.CURSOR_MOVED, cursorPosition);
        eventBus.emit(boardEvent);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        draw();
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

    private void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        for (DrawLayer drawLayer : drawLayers) {
            drawLayer.draw(g);
        }

    }
}

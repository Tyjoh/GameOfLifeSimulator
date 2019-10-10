package com.tyjohtech.gol;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.viewmodel.application.ApplicationState;
import com.tyjohtech.gol.viewmodel.application.ApplicationViewModel;
import com.tyjohtech.gol.viewmodel.board.BoardStateViewModel;
import com.tyjohtech.gol.viewmodel.editor.DrawMode;
import com.tyjohtech.gol.viewmodel.editor.EditorViewModel;
import com.tyjohtech.gol.viewmodel.simulation.SimulationViewModel;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {

    private Canvas canvas;
    private Affine affine;

    private ApplicationViewModel applicationViewModel;
    private EditorViewModel editorViewModel;

    public MainView(ApplicationViewModel applicationViewModel, BoardStateViewModel boardStateViewModel, EditorViewModel editorViewModel, SimulationViewModel simulationViewModel) {
        this.applicationViewModel = applicationViewModel;
        this.editorViewModel = editorViewModel;

        boardStateViewModel.listenToBoard(this::draw);

        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleMoved);

        this.setOnKeyPressed(this::onKeyPressed);

        Toolbar toolbar = new Toolbar(editorViewModel, simulationViewModel);

        InfoBar infoBar = new InfoBar(applicationViewModel, editorViewModel);

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(toolbar, this.canvas, spacer, infoBar);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);
    }

    private void handleMoved(MouseEvent mouseEvent) {
        Point2D simCoord = this.getSimulationCoordinates(mouseEvent);
        this.applicationViewModel.setCursorPosition((int) simCoord.getX(), (int) simCoord.getY());
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.editorViewModel.setDrawMode(DrawMode.PENCIL);
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.editorViewModel.setDrawMode(DrawMode.ERASER);
        }
    }

    private void handleDraw(MouseEvent event) {
        if (this.applicationViewModel.getApplicationState() != ApplicationState.SIMULATING) {
            Point2D simCoord = this.getSimulationCoordinates(event);

            int simX = (int) simCoord.getX();
            int simY = (int) simCoord.getY();

            System.out.println(simX + ", " + simY);

            this.editorViewModel.edit(simX, simY);
        }
    }

    private Point2D getSimulationCoordinates(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            return this.affine.inverseTransform(mouseX, mouseY);
        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException("Non invertible transform");
        }
    }

    public void draw(Board newBoard) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        drawSimulation(newBoard);

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= newBoard.getWidth(); x++) {
            g.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y <= newBoard.getHeight(); y++) {
            g.strokeLine(0, y, 10, y);
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

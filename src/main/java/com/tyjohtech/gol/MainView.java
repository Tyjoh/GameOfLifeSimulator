package com.tyjohtech.gol;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.model.StandardRule;
import com.tyjohtech.gol.viewmodel.ApplicationState;
import com.tyjohtech.gol.viewmodel.ApplicationViewModel;
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

//    public static final int EDITING = 0;
//    public static final int SIMULATING = 1;

    private InfoBar infoBar;
    private Canvas canvas;

    private Affine affine;

    private Simulation simulation;
    private Board initialBoard;

    private ApplicationViewModel applicationViewModel;

    public MainView(ApplicationViewModel applicationViewModel) {
        this.applicationViewModel = applicationViewModel;
        this.applicationViewModel.onApplicationStateChange(this::handleAppStateChange);

        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleMoved);

        this.setOnKeyPressed(this::onKeyPressed);

        Toolbar toolbar = new Toolbar(this);

        this.infoBar = new InfoBar(applicationViewModel);
        this.infoBar.setCursorPosition(0, 0);

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(toolbar, this.canvas, spacer, infoBar);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.initialBoard = new BoundedBoard(10, 10);
    }

    private void handleMoved(MouseEvent mouseEvent) {
        Point2D simCoord = this.getSimulationCoordinates(mouseEvent);
        this.applicationViewModel.setCursorPosition((int) simCoord.getX(), (int) simCoord.getY());
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.applicationViewModel.setDrawMode(CellState.ALIVE);
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.applicationViewModel.setDrawMode(CellState.DEAD);
        }
    }

    private void handleDraw(MouseEvent event) {
        if (this.applicationViewModel.getApplicationState() != ApplicationState.SIMULATING) {
            Point2D simCoord = this.getSimulationCoordinates(event);

            int simX = (int) simCoord.getX();
            int simY = (int) simCoord.getY();

            System.out.println(simX + ", " + simY);

            this.initialBoard.setState(simX, simY, applicationViewModel.getDrawMode());
            draw();
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

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        if (this.applicationViewModel.getApplicationState() == ApplicationState.EDITING) {
            drawSimulation(this.initialBoard);
        } else {
            drawSimulation(this.simulation.getBoard());
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.initialBoard.getWidth(); x++) {
            g.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y <= this.initialBoard.getHeight(); y++) {
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

    public Simulation getSimulation() {
        return this.simulation;
    }

    private void handleAppStateChange(ApplicationState oldState, ApplicationState newState) {
        if (newState == ApplicationState.SIMULATING) {
            this.simulation = new Simulation(this.initialBoard, new StandardRule());
        }
    }
}

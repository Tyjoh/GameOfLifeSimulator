package com.tyjohtech;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {

    private Canvas canvas;

    private Affine affine;

    private Simulation simulation;

    private int drawMode = Simulation.ALIVE;

    public MainView() {
        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);

        this.setOnKeyPressed(this::onKeyPressed);

        Toolbar toolbar = new Toolbar(this);

        this.getChildren().addAll(toolbar, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.simulation = new Simulation(10, 10);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.drawMode = Simulation.ALIVE;
            System.out.println("Draw mode");
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.drawMode = Simulation.DEAD;
            System.out.println("Erase mode");
        }
    }

    private void handleDraw(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);

            int simX = (int) simCoord.getX();
            int simY = (int) simCoord.getY();

            System.out.println(simX + ", " + simY);

            this.simulation.setState(simX, simY, drawMode);
            draw();
        } catch (NonInvertibleTransformException e) {
            System.out.println("Could not invert transform");
        }
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.simulation.width; x++) {
            for (int y = 0; y < this.simulation.height; y++) {
                if (this.simulation.getState(x, y) == Simulation.ALIVE) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.simulation.width; x++) {
            g.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y <= this.simulation.height; y++) {
            g.strokeLine(0, y, 10, y);
        }

    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void setDrawMode(int newDrawMode) {
        this.drawMode = newDrawMode;
    }
}

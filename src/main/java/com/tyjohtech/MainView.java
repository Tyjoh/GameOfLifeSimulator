package com.tyjohtech;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class MainView extends VBox {

    private Button stepButton;
    private Canvas canvas;

    private Affine affine;

    private Simulation simulation;

    public MainView() {
        this.stepButton = new Button("step");
        this.stepButton.setOnAction(actionEvent -> {
            simulation.step();
            draw();
        });

        this.canvas = new Canvas(400, 400);

        this.getChildren().addAll(this.stepButton, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.simulation = new Simulation(10, 10);

        simulation.setAlive(2, 2);
        simulation.setAlive(3, 2);
        simulation.setAlive(4, 2);

        simulation.setAlive(5, 5);
        simulation.setAlive(5, 6);
        simulation.setAlive(6, 5);
        simulation.setAlive(6, 6);
        simulation.setAlive(4, 5);
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.simulation.width; x++) {
            for (int y = 0; y < this.simulation.height; y++) {
                if (this.simulation.getState(x, y) == 1) {
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

}

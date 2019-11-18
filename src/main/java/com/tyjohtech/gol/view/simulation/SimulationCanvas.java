package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.view.simulation.tool.EditorToolRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Map;

public class SimulationCanvas extends Pane {

    private Canvas canvas;
    private Map<String, EditorToolRenderer> toolRenderers;
    private SimulationCanvasViewModel viewModel;

    public SimulationCanvas(Map<String, EditorToolRenderer> toolRenderers, SimulationCanvasViewModel viewModel) {
        this.toolRenderers = toolRenderers;
        this.viewModel = viewModel;

        this.viewModel.getBoardViewTransform().listen(value -> draw());
        this.viewModel.getCursorPosition().listen(value -> draw());
        this.viewModel.getActiveTool().listen(value -> draw());
        this.viewModel.getBoard().listen(value -> draw());

        this.canvas = new Canvas(400, 400);

        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());

        this.getChildren().add(this.canvas);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        draw();
    }

    private void draw() {
        Board board = viewModel.getBoard().get();

        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(viewModel.getBoardViewTransform().get());

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        this.drawSimulation(board);

        String activeTool = this.viewModel.getActiveTool().get();
        EditorToolRenderer toolRenderer = this.toolRenderers.get(activeTool);
        toolRenderer.render(g);

        if (viewModel.getCursorPosition().isPresent()) {
            this.drawCursor(viewModel.getCursorPosition().get());
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
        g.setFill(new Color(0.08, 0.08, 0.08, 1));
        for (int x = 0; x < simulationToDraw.getWidth(); x++) {
            for (int y = 0; y < simulationToDraw.getHeight(); y++) {
                if (simulationToDraw.getState(x, y) == CellState.ALIVE) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }

    private void drawSelection(BoardRegion region) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(new Color(0.1, 0.1, 0.1, 0.1));
        for (int x = region.getTopLeft().getX(); x <= region.getBottomRight().getX(); x++) {
            for (int y = region.getTopLeft().getY(); y <= region.getBottomRight().getY(); y++) {
                g.fillRect(x, y, 1, 1);
            }
        }
    }

    private void drawCursor(CellPosition position) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(new Color(0.3, 0.3, 0.3, 0.4));
        g.fillRect(position.getX(), position.getY(), 1, 1);
    }
}

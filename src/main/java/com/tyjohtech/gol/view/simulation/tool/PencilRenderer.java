package com.tyjohtech.gol.view.simulation.tool;

import com.tyjohtech.gol.logic.editor.tool.PencilTool;
import com.tyjohtech.gol.model.board.BoardRegion;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PencilRenderer implements EditorToolRenderer {

    private PencilTool tool;

    public PencilRenderer(PencilTool tool) {
        this.tool = tool;
    }

    @Override
    public void render(GraphicsContext g) {
        BoardRegion region = tool.getToolRegion();

        g.setFill(new Color(0.1, 0.1, 0.1, 0.1));
        region.iterate((x, y, rx, ry) -> g.fillRect(x, y, 1, 1));
    }
}

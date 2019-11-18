package com.tyjohtech.gol.view.simulation.tool;

import com.tyjohtech.gol.logic.editor.tool.BrushTool;
import com.tyjohtech.gol.model.board.BoardMask;
import com.tyjohtech.gol.model.board.BoardRegion;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BrushRenderer implements EditorToolRenderer {

    private BrushTool brushTool;

    public BrushRenderer(BrushTool brushTool) {
        this.brushTool = brushTool;
    }

    @Override
    public void render(GraphicsContext g) {
        if (!brushTool.isVisible()) {
            return;
        }

        BoardRegion region = brushTool.getToolRegion();
        BoardMask mask = brushTool.getToolMask();

        g.setFill(new Color(0.3, 0.3, 0.3, 0.4));
        region.iterate((x, y, rx, ry) -> {
            if (mask.isSet(rx, ry)) {
                g.fillRect(x, y, 1, 1);
            }
        });
    }
}

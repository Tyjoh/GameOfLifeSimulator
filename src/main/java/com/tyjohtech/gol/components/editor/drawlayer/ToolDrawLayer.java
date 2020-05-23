package com.tyjohtech.gol.components.editor.drawlayer;

import com.tyjohtech.app.view.AbstractDrawLayer;
import com.tyjohtech.gol.components.editor.state.ToolState;
import com.tyjohtech.gol.model.CellPosition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ToolDrawLayer extends AbstractDrawLayer {

    private ToolState toolState;

    public ToolDrawLayer(ToolState toolState) {
        super(10);
        this.toolState = toolState;
        toolState.listen(l -> this.invalidate());
    }

    @Override
    public void render(GraphicsContext g, int startX, int startY, int endX, int endY) {
        CellPosition cursor = toolState.getCursorPosition();
        if (cursor != null) {
            g.setFill(new Color(0.3, 0.3, 0.3, 0.5));
            g.fillRect(cursor.getX(), cursor.getY(), 1, 1);
        }
    }
}

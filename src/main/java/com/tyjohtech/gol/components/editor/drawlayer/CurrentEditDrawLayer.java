package com.tyjohtech.gol.components.editor.drawlayer;

import com.tyjohtech.app.view.AbstractDrawLayer;
import com.tyjohtech.gol.components.editor.state.Change;
import com.tyjohtech.gol.components.editor.state.Edit;
import com.tyjohtech.gol.components.editor.state.EditState;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CurrentEditDrawLayer extends AbstractDrawLayer {

    private EditState editState;

    public CurrentEditDrawLayer(EditState editState) {
        super(20);
        this.editState = editState;
        this.editState.listen(l -> this.invalidate());
    }

    @Override
    public void render(GraphicsContext g, int startX, int startY, int endX, int endY) {
        if (!editState.isInProgress()) {
            return;
        }

        Edit edit = editState.getEdit();

        for (Change change : edit) {
            CellPosition cursor = change.getPosition();
            if (change.getNewState() == CellState.ALIVE) {
                g.setFill(new Color(0, 0, 0, 0.8));
            } else {
                g.setFill(new Color(1, 1, 1, 0.8));
            }
            g.fillRect(cursor.getX(), cursor.getY(), 1, 1);
        }
    }
}

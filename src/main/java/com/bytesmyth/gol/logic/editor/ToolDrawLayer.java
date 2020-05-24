package com.bytesmyth.gol.logic.editor;

import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.view.AbstractDrawLayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ToolDrawLayer extends AbstractDrawLayer {

    private EditorState editorState;

    public ToolDrawLayer(EditorState editorState) {
        this.editorState = editorState;
        this.editorState.getCursorPosition().listen(cp -> this.invalidate());
    }

    @Override
    public void draw(GraphicsContext g) {
        CellPosition cursor = editorState.getCursorPosition().get();

        if (cursor != null) {
            g.setFill(new Color(0.3, 0.3, 0.3, 0.5));
            g.fillRect(cursor.getX(), cursor.getY(), 1, 1);
        }
    }

    @Override
    public int getLayer() {
        return 9;
    }
}

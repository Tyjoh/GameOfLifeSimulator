package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.BrushCommand;
import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.model.board.BoardMask;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public class BrushTool implements EditorTool {

    private Property<Integer> brushSize = new Property<>();
    private EditorState editorState;

    public BrushTool(EditorState editorState) {
        this.editorState = editorState;
        this.brushSize.set(5);
    }

    public void handle(BrushConfigEvent brushConfigEvent) {
        this.brushSize.set(brushConfigEvent.getBrushSize());
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    public BoardMask getToolMask() {
        int diam = brushSize.get();
        int r = diam / 2;
        BoardMask mask = new BoardMask(diam, diam);

        for (int x = 0; x <= diam; x++) {
            int dx = Math.abs(r - x);
            for (int y = 0; y <= diam; y++) {
                int dy = Math.abs(r - y);
                double dist = Math.sqrt((dx * dx) + (dy * dy));
                if (dist <= r + 0.25) {
                    mask.set(x, y, true);
                }
            }
        }
        return mask;
    }

    public BoardRegion getToolRegion() {
        CellPosition cursorPosition = editorState.getCursor().get();
        int diam = brushSize.get();
        int radius = diam / 2;

        CellPosition topLeft = CellPosition.of(cursorPosition.getX() - radius, cursorPosition.getY() - radius);
        CellPosition bottomRight = CellPosition.of(topLeft.getX() + diam, topLeft.getY() + diam);

        return new BoardRegion(topLeft, bottomRight);
    }

    public boolean isVisible() {
        return editorState.getCursor().isPresent();
    }

    public Command createCommand() {
        return new BrushCommand(
                editorState.getBoard(),
                getToolRegion(),
                editorState.getDrawState().get(),
                getToolMask()
        );
    }
}

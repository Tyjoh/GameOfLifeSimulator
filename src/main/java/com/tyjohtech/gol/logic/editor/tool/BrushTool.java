package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.BrushCommand;
import com.tyjohtech.gol.model.board.*;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public class BrushTool implements EditorTool {

    private Property<BoardMask> boardMask = new Property<>();
    private Property<Integer> brushSize = new Property<>();
    private Property<BoardRegion> areaOfEffect = new Property<>();
    private Property<Board> board;
    private Property<CellPosition> cursorPosition;
    private Property<CellState> drawState;

    public BrushTool(Property<Board> board, Property<CellPosition> cursorPosition, Property<CellState> drawState) {
        this.board = board;
        this.cursorPosition = cursorPosition;
        this.drawState = drawState;

        cursorPosition.listen(this::updateCursorPosition);
        this.brushSize.set(5);
        updateMask();
    }

    public void handle(BrushConfigEvent brushConfigEvent) {
        this.brushSize.set(brushConfigEvent.getBrushSize());
        updateMask();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Property<BoardMask> getMask() {
        return boardMask;
    }

    @Override
    public Property<BoardRegion> getAreaOfEffect() {
        return areaOfEffect;
    }

    private void updateMask() {
        BoardMask mask = new BoardMask(5, 5);
        int diam = brushSize.get();
        int r = diam / 2;

        for (int x = 0; x < diam; x++) {
            int dx = Math.abs(r - x);
            for (int y = 0; y < diam; y++) {
                int dy = Math.abs(r - y);
                int dist = dx + dy;
                if (dist <= r) {
                    mask.set(x, y, true);
                }
            }
        }

        this.boardMask.set(mask);
        if (cursorPosition.isPresent()) {
            updateCursorPosition(this.cursorPosition.get());
        }
    }

    private void updateCursorPosition(CellPosition cursorPosition) {
        int diam = brushSize.get();
        int radius = diam / 2;
        CellPosition topLeft = CellPosition.of(cursorPosition.getX() - radius, cursorPosition.getY() - radius);
        CellPosition bottomRight = CellPosition.of(cursorPosition.getX() + radius, cursorPosition.getY() + radius);
        areaOfEffect.set(new BoardRegion(topLeft, bottomRight));
    }

    @Override
    public Command createCommand() {
        return new BrushCommand(board, areaOfEffect.get(), drawState.get(), boardMask.get());
    }
}

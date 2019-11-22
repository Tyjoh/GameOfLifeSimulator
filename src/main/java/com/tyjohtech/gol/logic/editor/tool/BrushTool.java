package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.logic.editor.event.ToolActionEvent;
import com.tyjohtech.gol.model.board.*;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.property.Property;

public class BrushTool implements EditorTool {

    private EditorState editorState;
    private CommandProcessor commandProcessor;
    private Property<Integer> brushSize = new Property<>();
    private BoardMask boardMask;
    private Stroke currentStroke;

    public BrushTool(EditorState editorState, CommandProcessor commandProcessor) {
        this.editorState = editorState;
        this.commandProcessor = commandProcessor;
        this.brushSize.listen(this::updateMask);
        this.brushSize.set(5);

        this.currentStroke = new Stroke();
    }

    public void handle(BrushConfigEvent brushConfigEvent) {
        this.brushSize.set(brushConfigEvent.getBrushSize());
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void handle(ToolActionEvent actionEvent) {
        BoardRegion region = getToolRegion();
        CellState drawState = editorState.getDrawState().get();
        Board board = editorState.getBoard().get();

        region.iterate((x, y, rx, ry) -> {
            if (boardMask.isSet(rx, ry)) {
                CellState prevState = board.getState(x, y);
                StrokePoint strokePoint = new StrokePoint(CellPosition.of(x, y), drawState, prevState);
                currentStroke.add(strokePoint);
                board.setState(x, y, drawState);
            }
        });

        if (actionEvent.getActionType() == ToolActionEvent.Type.END) {
            commandProcessor.push(new StrokeCommand(editorState.getBoard(), currentStroke.clone()));
            currentStroke.clear();
        }

        editorState.getBoard().pump();
    }

    public BoardMask getToolMask() {
        return this.boardMask;
    }

    public BoardRegion getToolRegion() {
        CellPosition cursorPosition = editorState.getCursor().get();
        int diam = brushSize.get();
        int radius = diam / 2;

        CellPosition topLeft = CellPosition.of(cursorPosition.getX() - radius, cursorPosition.getY() - radius);
        CellPosition bottomRight = CellPosition.of(topLeft.getX() + diam, topLeft.getY() + diam);

        return new BoardRegion(topLeft, bottomRight);
    }

    private void updateMask(Integer diam) {
        int r = diam / 2;
        this.boardMask = new BoardMask(diam, diam);

        for (int x = 0; x <= diam; x++) {
            int dx = Math.abs(r - x);
            for (int y = 0; y <= diam; y++) {
                int dy = Math.abs(r - y);
                double dist = Math.sqrt((dx * dx) + (dy * dy));
                if (dist <= r + 0.25) {
                    this.boardMask.set(x, y, true);
                }
            }
        }

    }

    public boolean isVisible() {
        return editorState.getCursor().isPresent();
    }
}

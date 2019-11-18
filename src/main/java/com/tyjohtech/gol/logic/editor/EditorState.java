package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.model.board.*;
import com.tyjohtech.gol.util.property.Property;

public class EditorState extends Property<EditorState> {

    private Property<CellPosition> cursor = new Property<>();
    private Property<BoardRegion> selection = new Property<>();
    private Property<Board> board = new Property<>();
    private Property<CellState> drawState = new Property<>();
    private Property<BoardRegion> toolAreaOfEffect = new Property<>();
    private Property<BoardMask> toolMask = new Property<>();
    private Property<String> selectedTool = new Property<>();

    public EditorState() {
        this.set(this);
        cursor.listen(value -> pump());
        selection.listen(value -> pump());
        board.listen(value -> pump());
        drawState.listen(value -> pump());
        toolAreaOfEffect.listen(value -> pump());
        toolMask.listen(value -> pump());
        selectedTool.listen(value -> pump());
    }

    public Property<CellPosition> getCursor() {
        return cursor;
    }

    public Property<BoardRegion> getSelection() {
        return selection;
    }

    public Property<Board> getBoard() {
        return board;
    }

    public Property<CellState> getDrawState() {
        return drawState;
    }

    public Property<BoardRegion> getToolAreaOfEffect() {
        return toolAreaOfEffect;
    }

    public Property<BoardMask> getToolMask() {
        return toolMask;
    }

    public Property<String> getSelectedTool() {
        return selectedTool;
    }
}

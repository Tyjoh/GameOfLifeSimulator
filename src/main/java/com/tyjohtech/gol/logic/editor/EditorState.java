package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.property.Property;

public class EditorState extends Property<EditorState> {

    private Property<CellPosition> cursor = new Property<>();
    private Property<BoardRegion> selection = new Property<>();
    private Property<Board> board = new Property<>();
    private Property<CellState> drawState = new Property<>();
    private Property<String> selectedTool = new Property<>();

    public EditorState() {
        this.set(this);
        cursor.listen(value -> pump());
        selection.listen(value -> pump());
        board.listen(value -> pump());
        drawState.listen(value -> pump());
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

    public Property<String> getSelectedTool() {
        return selectedTool;
    }

}

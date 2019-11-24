package com.tyjohtech.gol.view.infobar;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.logic.state.AppStateRepository;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.property.Property;

public class InfoBarViewModel {

    private Property<CellPosition> cursorPosition = new Property<>();
    private Property<String> currentTool = new Property<>();
    private Property<String> currentDrawState = new Property<>();
    private Property<String> applicationState = new Property<>();

    void bindAppState(AppStateRepository appStateRepository) {
        appStateRepository.getApplicationState().listen(state -> {
            this.applicationState.set(state.name());
        });
    }

    void bindEditor(EditorState editorState) {
        this.cursorPosition.bindTo(editorState.getCursor());
        this.currentTool.bindTo(editorState.getSelectedTool());
        editorState.getDrawState().listen(state -> {
            if (state == CellState.ALIVE) {
                this.currentDrawState.set("Draw");
            } else if (state == CellState.DEAD) {
                this.currentDrawState.set("Erase");
            }
        });
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }

    public Property<String> getCurrentTool() {
        return currentTool;
    }

    public Property<String> getCurrentDrawState() {
        return currentDrawState;
    }

    public Property<String> getApplicationState() {
        return applicationState;
    }
}

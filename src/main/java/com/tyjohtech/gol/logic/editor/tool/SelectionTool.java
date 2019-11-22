package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.logic.editor.event.ToolActionEvent;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.property.Property;

public class SelectionTool implements EditorTool {

    private EditorState editorState;
    private CommandProcessor commandProcessor;

    private CellPosition startPosition;

    public SelectionTool(EditorState editorState, CommandProcessor commandProcessor) {
        this.editorState = editorState;
        this.commandProcessor = commandProcessor;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void handle(ToolActionEvent actionEvent) {
        if (actionEvent.getActionType() == ToolActionEvent.Type.BEGIN) {
            startPosition = editorState.getCursor().get();
        }

        BoardRegion currentSelection = new BoardRegion(startPosition, editorState.getCursor().get());
        editorState.getSelection().set(currentSelection);

        if (actionEvent.getActionType() == ToolActionEvent.Type.END) {
            startPosition = null;
            commandProcessor.execute(new SelectionCommand(editorState.getSelection(), currentSelection));
        }
    }

    private static class SelectionCommand implements Command {

        private Property<BoardRegion> selectionProperty;
        private BoardRegion selection;

        private SelectionCommand(Property<BoardRegion> selectionProperty, BoardRegion selection) {
            this.selectionProperty = selectionProperty;
            this.selection = selection;
        }

        @Override
        public void execute() {
            selectionProperty.set(selection);
        }

        @Override
        public void undo() {
            selectionProperty.set(null);
        }
    }
}

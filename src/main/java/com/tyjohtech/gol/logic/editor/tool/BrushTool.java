package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.BoardBrushCommand;
import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.logic.editor.BoardEvent;
import com.tyjohtech.gol.logic.editor.BoardEventType;
import com.tyjohtech.gol.util.command.CommandProcessor;

public class BrushTool implements EditorTool {

    private CommandProcessor commandProcessor;

    public BrushTool(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void handle(BoardEvent event, BoardEditor editor) {
        if (event.getBoardEventType() == BoardEventType.DRAG) {
            BoardBrushCommand command = new BoardBrushCommand(editor.getBoard(), event.getPosition(), editor.getDrawState().get());
            commandProcessor.execute(command);
        }
    }
}

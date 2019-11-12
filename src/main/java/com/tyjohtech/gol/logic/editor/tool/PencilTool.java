package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.logic.editor.BoardEvent;
import com.tyjohtech.gol.logic.editor.BoardEventType;
import com.tyjohtech.gol.logic.editor.BoardPencilCommand;
import com.tyjohtech.gol.util.command.CommandProcessor;

public class PencilTool implements EditorTool {

    private CommandProcessor commandProcessor;

    public PencilTool(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void handle(BoardEvent event, BoardEditor editor) {
        BoardEventType eventType = event.getBoardEventType();
        if (eventType == BoardEventType.DRAG) {
            BoardPencilCommand command = new BoardPencilCommand(editor.getBoard(), event.getPosition(), editor.getDrawState().get());
            commandProcessor.execute(command);
        }
    }
}

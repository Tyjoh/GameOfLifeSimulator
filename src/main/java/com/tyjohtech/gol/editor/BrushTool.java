package com.tyjohtech.gol.editor;

import com.tyjohtech.gol.command.CommandProcessor;

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

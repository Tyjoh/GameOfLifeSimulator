package com.tyjohtech.gol;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.util.command.CommandProcessor;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ToolSelectHandler implements EventHandler<KeyEvent> {

    private BoardEditor editor;
    private CommandProcessor commandProcessor;
    private boolean commandDown = false;

    public ToolSelectHandler(BoardEditor editor, CommandProcessor commandProcessor) {
        this.editor = editor;
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void handle(KeyEvent event) {
        boolean command = event.getCode() == KeyCode.COMMAND;
        if (event.getEventType() == KeyEvent.KEY_PRESSED && command) {
            commandDown = true;
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED && command) {
            commandDown = false;
        }

        if (event.getEventType() != KeyEvent.KEY_RELEASED) {
            return;
        }

//        if (event.getCode() == KeyCode.P) {
//            editor.getTool().set(new PencilTool(commandProcessor));
//            System.out.println("Pencil selected");
//        } else if (event.getCode() == KeyCode.B) {
//            editor.getTool().set(new BrushTool(commandProcessor));
//            System.out.println("Brush selected");
//        } else if (event.getCode() == KeyCode.Z && commandDown) {
//            commandProcessor.undo();
//        }
    }
}

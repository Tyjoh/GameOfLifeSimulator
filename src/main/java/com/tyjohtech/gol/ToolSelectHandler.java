package com.tyjohtech.gol;

import com.tyjohtech.gol.logic.editor.event.ToolSelectEvent;
import com.tyjohtech.gol.logic.editor.tool.BrushConfigEvent;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ToolSelectHandler implements EventHandler<KeyEvent> {

    private CommandProcessor commandProcessor;
    private boolean commandDown = false;
    private EventBus eventBus;

    private int brushSize = 5;

    public ToolSelectHandler(EventBus eventBus, CommandProcessor commandProcessor) {
        this.eventBus = eventBus;
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

        if (event.getCode() == KeyCode.P) {
            eventBus.emit(new ToolSelectEvent("PencilTool"));
            System.out.println("Pencil selected");
        } else if (event.getCode() == KeyCode.B) {
            eventBus.emit(new ToolSelectEvent("BrushTool"));
            System.out.println("Brush selected");
        } else if (event.getCode() == KeyCode.OPEN_BRACKET) {
            brushSize -= 2;
            eventBus.emit(new BrushConfigEvent(brushSize));
        } else if (event.getCode() == KeyCode.CLOSE_BRACKET) {
            brushSize += 2;
            eventBus.emit(new BrushConfigEvent(brushSize));
        } else if (event.getCode() == KeyCode.Z && commandDown) {
            commandProcessor.undo();
        }
    }
}

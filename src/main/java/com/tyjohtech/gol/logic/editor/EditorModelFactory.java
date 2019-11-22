package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.DrawStateEvent;
import com.tyjohtech.gol.logic.editor.event.ToolActionEvent;
import com.tyjohtech.gol.logic.editor.event.ToolSelectEvent;
import com.tyjohtech.gol.logic.editor.tool.EditorToolRegistry;
import com.tyjohtech.gol.logic.state.AppState;
import com.tyjohtech.gol.logic.state.AppStateCondition;
import com.tyjohtech.gol.logic.state.AppStateRepository;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.model.board.BoundedBoard;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.ConditionalEventBusProxy;
import com.tyjohtech.gol.util.event.EventBus;

import java.util.Set;

public class EditorModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelProvider propertyBus, EventBus rootEventBus, CommandProcessor commandProcessor) {
        EventBus eventBus = createProxyBus(propertyBus, rootEventBus);

        EditorToolRegistry toolRegistry = new EditorToolRegistry();
        propertyBus.publish(EditorToolRegistry.class, toolRegistry);

        EditorState editorState = new EditorState();
        propertyBus.publish(EditorState.class, editorState);

        EditorEventHandler eventHandler = new EditorEventHandler(toolRegistry, editorState);
        propertyBus.publish(EditorEventHandler.class, eventHandler);

        rootEventBus.listenFor(CursorEvent.class, eventHandler::handle);
        rootEventBus.listenFor(DrawStateEvent.class, eventHandler::handle);
        eventBus.listenFor(ToolActionEvent.class, eventHandler::handle);
        rootEventBus.listenFor(ToolSelectEvent.class, eventHandler::handle);

        editorState.getBoard().set(new BoundedBoard(45, 37));
        editorState.getDrawState().set(CellState.ALIVE);
    }

    private EventBus createProxyBus(ModelProvider propertyBus, EventBus parent) {
        Set<AppState> enableStates = Set.of(AppState.EDITING);
        AppStateRepository appStateRepo = propertyBus.get(AppStateRepository.class);
        AppStateCondition condition = new AppStateCondition(appStateRepo, enableStates);
        return new ConditionalEventBusProxy(parent, condition);
    }
}

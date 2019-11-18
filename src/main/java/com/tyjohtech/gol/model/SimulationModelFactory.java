package com.tyjohtech.gol.model;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelProvider;

public class SimulationModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelProvider modelProvider, EventBus eventBus, CommandProcessor commandProcessor) {
        Simulator simulator = new Simulator();
        modelProvider.publish(Simulator.class, simulator);

        eventBus.listenFor(SimulationEvent.class, simulator::handle);

        EditorState editorBoard = modelProvider.get(EditorState.class);
        simulator.getInitialBoard().bindTo(editorBoard.getBoard());

    }
}

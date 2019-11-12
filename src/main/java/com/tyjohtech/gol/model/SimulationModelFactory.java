package com.tyjohtech.gol.model;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;
import com.tyjohtech.gol.util.property.Property;

public class SimulationModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelPropertyBus propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        Simulator simulator = new Simulator();
        propertyBus.publish("board", Simulator.class, simulator.getCurrentBoard());

        eventBus.listenFor(SimulationEvent.class, simulator::handle);

        Property<Board> editorBoard = propertyBus.get(BoardEditor.class, "board");
        simulator.getInitialBoard().bindTo(editorBoard);

    }
}

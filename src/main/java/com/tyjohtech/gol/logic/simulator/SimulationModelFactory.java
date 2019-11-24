package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.logic.simulator.config.SimulationConfig;
import com.tyjohtech.gol.logic.simulator.config.SimulationConfigEvent;
import com.tyjohtech.gol.logic.simulator.config.SimulationConfigEventHandler;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;

public class SimulationModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelProvider modelProvider, EventBus eventBus, CommandProcessor commandProcessor) {
        SimulationConfig simulationConfig = new SimulationConfig();
        modelProvider.publish(SimulationConfig.class, simulationConfig);

        SimulationConfigEventHandler configEventHandler = new SimulationConfigEventHandler(simulationConfig, commandProcessor);
        eventBus.listenFor(SimulationConfigEvent.class, configEventHandler::handle);

        EditorState editorBoard = modelProvider.get(EditorState.class);

        Simulator simulator = new Simulator(editorBoard.getBoard(), simulationConfig);
        modelProvider.publish(Simulator.class, simulator);
        simulator.getInitialBoard().bindTo(editorBoard.getBoard());

        SimulatorEventHandler simulatorEventHandler = new SimulatorEventHandler(simulator, eventBus);
        eventBus.listenFor(SimulatorEvent.class, simulatorEventHandler::handle);


    }
}

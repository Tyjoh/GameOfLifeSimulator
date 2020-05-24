package com.bytesmyth.gol.components.simulator;

import com.bytesmyth.gol.ApplicationComponent;
import com.bytesmyth.gol.ApplicationContext;
import com.bytesmyth.gol.components.board.BoardState;
import com.bytesmyth.gol.components.editor.EditorState;
import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.model.BoundedBoard;

public class SimulatorApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        SimulatorState simulatorState = context.getStateRegistry().getState(SimulatorState.class);
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Simulator simulator = new Simulator(simulatorState, context.getCommandExecutor());
        context.getEventBus().listenFor(SimulatorEvent.class, simulator::handle);

        editorState.getEditorBoard().listen(simulatorState.getBoard()::set);

        simulatorState.getBoard().listen(simulationBoard -> {
            if (simulatorState.getSimulating().get()) {
                boardState.getBoard().set(simulationBoard);
            }
        });
    }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWidth(), context.getBoardHeight());
        SimulatorState simulatorState = new SimulatorState(board);
        context.getStateRegistry().registerState(SimulatorState.class, simulatorState);
    }
}

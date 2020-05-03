package com.tyjohtech.gol.components.simulator;

import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.components.board.ApplicationState;
import com.tyjohtech.gol.components.board.BoardState;
import com.tyjohtech.gol.components.editor.EditorState;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.BoundedBoard;

public class SimulatorComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        SimulatorState simulatorState = context.getStateRegistry().getState(SimulatorState.class);

        Simulator simulator = new Simulator(boardState, simulatorState, context.getCommandExecutor());
        context.getEventBus().listenFor(SimulatorEvent.class, simulator::handle);

        simulatorState.getBoard().listen(boardState.getBoardProperty()::set);
        editorState.getEditorBoard().listen(simulatorState.getBoard()::set);

        boardState.getApplicationState().listen(state -> {
            if (state == ApplicationState.SIMULATING) {
                simulatorState.getBoard().set(boardState.getBoardProperty().get());
            }
        });
    }

    @Override
    public void createState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWidth(), context.getBoardHeight());
        SimulatorState simulatorState = new SimulatorState(board);
        context.getStateRegistry().registerState(SimulatorState.class, simulatorState);
    }

}

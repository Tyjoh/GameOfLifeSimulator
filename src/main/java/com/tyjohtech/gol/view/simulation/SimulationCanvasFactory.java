package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelProvider;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class SimulationCanvasFactory implements ViewFactory {

    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.CENTER;
    }

    @Override
    public Node buildView(ModelProvider propertyProvider, EventBus eventBus) {
        SimulationCanvasViewModel viewModel = new SimulationCanvasViewModel();

        BoardEditor boardEditor = propertyProvider.get(BoardEditor.class);

        viewModel.getCurrentBoard().bindTo(boardEditor.getBoard());
        viewModel.getCursorPosition().bindTo(boardEditor.getCursor());
        viewModel.getSelection().bindTo(boardEditor.getSelection());
        viewModel.getSelectedTool().bindTo(boardEditor.getSelectedTool());

        SimulationCanvas simulationCanvas = new SimulationCanvas(viewModel);
        simulationCanvas.addEventHandler(MouseEvent.ANY, new SimulationCanvasEventHandler(eventBus, viewModel));
        return simulationCanvas;
    }
}

package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellRegion;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;
import com.tyjohtech.gol.util.property.Property;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import javafx.scene.Node;

public class SimulationCanvasFactory implements ViewFactory {

    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.CENTER;
    }

    @Override
    public Node buildView(ModelPropertyBus propertyProvider, EventBus eventBus) {
        SimulationCanvasViewModel viewModel = new SimulationCanvasViewModel();
        SimulationCanvas canvas = new SimulationCanvas(viewModel);

        Property<Board> editorBoard = propertyProvider.get(BoardEditor.class, "board");
        Property<CellPosition> editorCursor = propertyProvider.get(BoardEditor.class, "cursor");
        Property<CellRegion> editorSelection = propertyProvider.get(BoardEditor.class, "selection");

        viewModel.getCurrentBoard().bindTo(editorBoard);
        viewModel.getCursorPosition().bindTo(editorCursor);
        viewModel.getSelection().bindTo(editorSelection);

        return canvas;
    }
}

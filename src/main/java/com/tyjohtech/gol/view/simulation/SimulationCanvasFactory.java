package com.tyjohtech.gol.view.simulation;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.logic.editor.tool.BrushTool;
import com.tyjohtech.gol.logic.editor.tool.PencilTool;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import com.tyjohtech.gol.view.simulation.tool.BrushRenderer;
import com.tyjohtech.gol.view.simulation.tool.EditorToolRenderer;
import com.tyjohtech.gol.view.simulation.tool.PencilRenderer;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class SimulationCanvasFactory implements ViewFactory {

    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.CENTER;
    }

    @Override
    public Node buildView(ModelProvider propertyProvider, EventBus eventBus) {
        EditorState editorState = propertyProvider.get(EditorState.class);

        Map<String, EditorToolRenderer> toolRenderers = new HashMap<>();

        PencilTool pencilTool = propertyProvider.get(PencilTool.class);
        toolRenderers.put(pencilTool.name(), new PencilRenderer(pencilTool));

        BrushTool brushTool = propertyProvider.get(BrushTool.class);
        toolRenderers.put(brushTool.name(), new BrushRenderer(brushTool));

        SimulationCanvasViewModel viewModel = new SimulationCanvasViewModel();
        viewModel.getBoard().bindTo(editorState.getBoard());
        viewModel.getCursorPosition().bindTo(editorState.getCursor());
        viewModel.getActiveTool().bindTo(editorState.getSelectedTool());

        SimulationCanvas simulationCanvas = new SimulationCanvas(toolRenderers, viewModel);
        simulationCanvas.addEventHandler(MouseEvent.ANY, new SimulationCanvasEventHandler(eventBus, viewModel));
        return simulationCanvas;
    }
}

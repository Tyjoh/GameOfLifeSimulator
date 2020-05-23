package com.tyjohtech.gol.components.infobar;

import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.components.editor.state.EditorState;
import com.tyjohtech.gol.components.editor.state.ToolState;

public class InfoBarComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        ToolState toolState = context.getStateRegistry().getState(EditorState.class).getToolState();

        InfoBarViewModel infoBarViewModel = new InfoBarViewModel();
        toolState.listen(ts -> {
            infoBarViewModel.getCursorPosition().set(toolState.getCursorPosition());
            infoBarViewModel.getCurrentDrawMode().set(toolState.getDrawMode());
        });

        InfoBar infoBar = new InfoBar(infoBarViewModel);
        context.getMainView().setInfoBar(infoBar);
    }

    @Override
    public void createState(ApplicationContext context) {
    }

}

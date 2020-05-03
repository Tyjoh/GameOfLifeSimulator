package com.tyjohtech.gol.components.infobar;

import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.components.editor.EditorState;

public class InfoBarComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);

        InfoBarViewModel infoBarViewModel = new InfoBarViewModel();
        editorState.getCursorPosition().listen(infoBarViewModel.getCursorPosition()::set);
        editorState.getDrawMode().listen(infoBarViewModel.getCurrentDrawMode()::set);

        InfoBar infoBar = new InfoBar(infoBarViewModel);
        context.getMainView().setInfoBar(infoBar);
    }

    @Override
    public void createState(ApplicationContext context) {
    }

}

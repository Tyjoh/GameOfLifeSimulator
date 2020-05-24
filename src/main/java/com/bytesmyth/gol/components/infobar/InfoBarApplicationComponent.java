package com.bytesmyth.gol.components.infobar;

import com.bytesmyth.gol.ApplicationComponent;
import com.bytesmyth.gol.ApplicationContext;
import com.bytesmyth.gol.components.editor.EditorState;

public class InfoBarApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);

        InfoBarViewModel viewModel = new InfoBarViewModel();
        editorState.getCursorPosition().listen(viewModel.getCursorPosition()::set);
        editorState.getDrawMode().listen(viewModel.getCurrentDrawMode()::set);

        InfoBar infoBar = new InfoBar(viewModel);
        context.getMainView().setBottom(infoBar);
    }

    @Override
    public void initState(ApplicationContext context) {

    }
}

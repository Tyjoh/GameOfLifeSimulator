package com.bytesmyth.gol.components.editor;

import com.bytesmyth.app.command.Command;

public interface EditorCommand extends Command<EditorState> {
    @Override
    void execute(EditorState editorState);

    @Override
    default Class<EditorState> getStateClass() {
        return EditorState.class;
    }
}

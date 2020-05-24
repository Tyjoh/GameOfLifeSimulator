package com.bytesmyth.gol.logic.editor;

import com.bytesmyth.gol.command.Command;
import com.bytesmyth.gol.state.EditorState;

public interface EditorCommand extends Command<EditorState> {
    @Override
    void execute(EditorState editorState);

    @Override
    default Class<EditorState> getStateClass() {
        return EditorState.class;
    }
}

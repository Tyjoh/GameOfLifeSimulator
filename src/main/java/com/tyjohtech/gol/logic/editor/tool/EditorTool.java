package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.model.board.BoardMask;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public interface EditorTool {

    String name();

    Property<BoardMask> getMask();

    Property<BoardRegion> getAreaOfEffect();

    Command createCommand();

}

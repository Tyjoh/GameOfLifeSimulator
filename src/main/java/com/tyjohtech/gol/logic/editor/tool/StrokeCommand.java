package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.property.Property;

public class StrokeCommand implements Command {
    private Property<Board> board;
    private Stroke stroke;

    public StrokeCommand(Property<Board> board, Stroke stroke) {
        this.board = board;
        this.stroke = stroke;
    }

    @Override
    public void execute() {
        this.stroke.forEach(this::applyStrokePoint);
        this.board.pump();
    }

    @Override
    public void undo() {
        this.stroke.forEach(this::undoStrokePoint);
        this.board.pump();
    }

    private void applyStrokePoint(StrokePoint point) {
        board.get().setState(point.getPosition().getX(), point.getPosition().getY(), point.getNewState());
    }

    private void undoStrokePoint(StrokePoint point) {
        board.get().setState(point.getPosition().getX(), point.getPosition().getY(), point.getPrevState());
    }
}

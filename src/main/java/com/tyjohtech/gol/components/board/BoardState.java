package com.tyjohtech.gol.components.board;

import com.tyjohtech.app.property.Property;
import com.tyjohtech.gol.model.Board;

public class BoardState {

    private Property<Board> boardProperty = new Property<>();

    private Property<ApplicationState> applicationStateProperty = new Property<>(ApplicationState.EDITING);

    public Property<Board> getBoardProperty() {
        return boardProperty;
    }

    public Property<ApplicationState> getApplicationState() {
        return applicationStateProperty;
    }
}

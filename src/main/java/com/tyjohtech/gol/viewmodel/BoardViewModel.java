package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.util.Property;

public class BoardViewModel {

    private Property<Board> board = new Property<>();

    public Property<Board> getBoard() {
        return board;
    }

}

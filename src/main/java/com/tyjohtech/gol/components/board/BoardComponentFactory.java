package com.tyjohtech.gol.components.board;

import com.tyjohtech.gol.ApplicationContext;
import com.tyjohtech.gol.ComponentFactory;
import com.tyjohtech.gol.model.BoundedBoard;

public class BoardComponentFactory implements ComponentFactory {

    @Override
    public void createView(ApplicationContext context) {
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        BoardDrawLayer boardDrawLayer = new BoardDrawLayer(boardState);
        context.getMainView().addDrawLayer(boardDrawLayer);
    }

    @Override
    public void createState(ApplicationContext context) {
        BoardState boardState = new BoardState();
        boardState.setBoard(new BoundedBoard(context.getBoardWidth(), context.getBoardHeight()));
        context.getStateRegistry().registerState(BoardState.class, boardState);
    }

}

package com.bytesmyth.gol.logic.board;

import com.bytesmyth.gol.ApplicationComponent;
import com.bytesmyth.gol.ApplicationContext;
import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.model.BoundedBoard;

public class BoardApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        BoardState state = context.getStateRegistry().getState(BoardState.class);

        BoardDrawLayer boardDrawLayer = new BoardDrawLayer(state);
        GridDrawLayer gridDrawLayer = new GridDrawLayer(state);

        context.getMainView().addDrawLayer(boardDrawLayer);
        context.getMainView().addDrawLayer(gridDrawLayer);
    }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWidth(), context.getBoardHeight());
        BoardState boardState = new BoardState(board);
        context.getStateRegistry().registerState(BoardState.class, boardState);
    }
}

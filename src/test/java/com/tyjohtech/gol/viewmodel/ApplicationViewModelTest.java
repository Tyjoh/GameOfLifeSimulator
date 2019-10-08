package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.CellState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ApplicationViewModelTest {

    private ApplicationViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.viewModel = ApplicationViewModel.defaults();
    }

    @Test
    void confirmDefaultState() {
        assertEquals(ApplicationState.EDITING, this.viewModel.getApplicationState());
        assertEquals(CellState.ALIVE, this.viewModel.getDrawMode());
        assertEquals(new CursorPosition(0, 0), this.viewModel.getCursorPosition());
    }

    @Test
    void applicationState_updateValue() {
        this.viewModel.onApplicationStateChange(((oldValue, newValue) -> {
            assertEquals(ApplicationState.EDITING, oldValue);
            assertEquals(ApplicationState.SIMULATING, newValue);
        }));

        this.viewModel.setApplicationState(ApplicationState.SIMULATING);
    }

    @Test
    void applicationState_updateToSameValue() {
        this.viewModel.onApplicationStateChange(((oldValue, newValue) -> {
            fail();
        }));

        this.viewModel.setApplicationState(ApplicationState.EDITING);
    }

    @Test
    void drawMode_updateValue() {
        this.viewModel.listenToDrawMode(((oldValue, newValue) -> {
            assertEquals(CellState.ALIVE, oldValue);
            assertEquals(CellState.DEAD, newValue);
        }));

        this.viewModel.setDrawMode(CellState.DEAD);
    }

    @Test
    void drawMode_updateToSameValue() {
        this.viewModel.listenToDrawMode(((oldValue, newValue) -> {
            fail();
        }));

        this.viewModel.setDrawMode(CellState.ALIVE);
    }

    @Test
    void cursorPosition_updateValue() {
        this.viewModel.listenToCursorPosition(((oldValue, newValue) -> {
            assertEquals(new CursorPosition(0, 0), oldValue);
            assertEquals(new CursorPosition(9, 10), newValue);
        }));

        this.viewModel.setCursorPosition(9, 10);
    }

    @Test
    void cursorPosition_updateToSameValue() {
        this.viewModel.listenToCursorPosition(((oldValue, newValue) -> {
            fail();
        }));

        this.viewModel.setCursorPosition(0, 0);
    }

}
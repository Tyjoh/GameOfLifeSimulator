package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.viewmodel.application.ApplicationState;
import com.tyjohtech.gol.viewmodel.application.ApplicationViewModel;
import com.tyjohtech.gol.viewmodel.application.CursorPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ApplicationViewModelTest {

    private ApplicationViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.viewModel = new ApplicationViewModel();
    }

    @Test
    void confirmDefaultState() {
        assertEquals(ApplicationState.STOPPED, this.viewModel.getApplicationState());
        assertEquals(new CursorPosition(0, 0), this.viewModel.getCursorPosition());
    }

    @Test
    void applicationState_updateValue() {
        this.viewModel.listenToApplicationState(((newValue) -> {
            assertEquals(ApplicationState.SIMULATING, newValue);
        }));

        this.viewModel.setApplicationState(ApplicationState.SIMULATING);
    }

    @Test
    void applicationState_updateToSameValue() {
        this.viewModel.listenToApplicationState(((newValue) -> {
            fail();
        }));

        this.viewModel.setApplicationState(ApplicationState.EDITING);
    }

    @Test
    void cursorPosition_updateValue() {
        this.viewModel.listenToCursorPosition(((newValue) -> {
            assertEquals(new CursorPosition(9, 10), newValue);
        }));

        this.viewModel.setCursorPosition(9, 10);
    }

    @Test
    void cursorPosition_updateToSameValue() {
        this.viewModel.listenToCursorPosition(((newValue) -> {
            fail();
        }));

        this.viewModel.setCursorPosition(0, 0);
    }

}
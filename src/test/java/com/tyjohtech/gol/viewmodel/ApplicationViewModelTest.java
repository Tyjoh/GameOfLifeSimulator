package com.tyjohtech.gol.viewmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationViewModelTest {

    private ApplicationViewModel applicationViewModel;

    @BeforeEach
    void setUp() {
        applicationViewModel = new ApplicationViewModel(ApplicationState.EDITING);
    }

    @Test
    void setApplicationState_setToNewState() {
        TestAppStateListener listener = new TestAppStateListener();
        applicationViewModel.listenToAppState(listener);

        applicationViewModel.setCurrentState(ApplicationState.SIMULATING);

        assertTrue(listener.appStateUpdated);
        assertEquals(ApplicationState.SIMULATING, listener.updatedAppState);
    }

    @Test
    void setApplicationState_setToSameState() {
        TestAppStateListener listener = new TestAppStateListener();
        applicationViewModel.listenToAppState(listener);

        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        assertFalse(listener.appStateUpdated);
        assertNull(listener.updatedAppState);

    }

    private static class TestAppStateListener implements SimpleChangeListener<ApplicationState> {
        private boolean appStateUpdated = false;
        private ApplicationState updatedAppState = null;

        @Override
        public void valueChanged(ApplicationState newAppState) {
            appStateUpdated = true;
            updatedAppState = newAppState;
        }
    }
}
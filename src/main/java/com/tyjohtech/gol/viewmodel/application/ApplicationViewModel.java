package com.tyjohtech.gol.viewmodel.application;

import com.tyjohtech.gol.viewmodel.ChangeListener;

import java.util.Collection;
import java.util.LinkedList;

public class ApplicationViewModel {

    private ApplicationState applicationState;
    private Collection<ChangeListener<ApplicationState>> appStateListeners;

    private CursorPosition cursorPosition;
    private Collection<ChangeListener<CursorPosition>> cursorPositionListeners;

    public ApplicationViewModel() {
        this.applicationState = ApplicationState.STOPPED;
        this.appStateListeners = new LinkedList<>();

        this.cursorPosition = new CursorPosition(0, 0);
        this.cursorPositionListeners = new LinkedList<>();
    }

    public void setApplicationState(ApplicationState newState) {
        if (!newState.equals(this.applicationState)) {
            notify(appStateListeners, newState);
            this.applicationState = newState;
        }
    }

    public void setCursorPosition(int x, int y) {
        CursorPosition newCursorPosition = new CursorPosition(x, y);
        if (!newCursorPosition.equals(this.cursorPosition)) {
            notify(cursorPositionListeners, newCursorPosition);
            this.cursorPosition = newCursorPosition;
        }
    }

    public void listenToApplicationState(ChangeListener<ApplicationState> listener) {
        appStateListeners.add(listener);
    }

    public void listenToCursorPosition(ChangeListener<CursorPosition> listener) {
        cursorPositionListeners.add(listener);
    }

    private <T> void notify(Collection<ChangeListener<T>> listeners, T newVal) {
        for (ChangeListener<T> listener : listeners) {
            listener.valueChanged(newVal);
        }
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public CursorPosition getCursorPosition() {
        return cursorPosition;
    }
}

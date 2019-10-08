package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.CellState;

import java.util.Collection;
import java.util.LinkedList;

public class ApplicationViewModel {

    private ApplicationState applicationState;
    private Collection<SimpleChangeListener<ApplicationState>> appStateListeners;

    private CellState drawMode;
    private Collection<SimpleChangeListener<CellState>> drawModeChangeListeners;

    private CursorPosition cursorPosition;
    private Collection<SimpleChangeListener<CursorPosition>> cursorPositionListeners;

    private ApplicationViewModel(ApplicationState appState, CellState drawMode, CursorPosition cursorPosition) {
        this.applicationState = appState;
        this.appStateListeners = new LinkedList<>();

        this.drawMode = drawMode;
        this.drawModeChangeListeners = new LinkedList<>();

        this.cursorPosition = cursorPosition;
        this.cursorPositionListeners = new LinkedList<>();
    }

    public static ApplicationViewModel defaults() {
        return new ApplicationViewModel(ApplicationState.EDITING, CellState.ALIVE, new CursorPosition(0, 0));
    }

    public void setApplicationState(ApplicationState newState) {
        if (!newState.equals(this.applicationState)) {
            notify(appStateListeners, applicationState, newState);
            this.applicationState = newState;
        }
    }

    public void setDrawMode(CellState newDrawMode) {
        if (newDrawMode != this.drawMode) {
            notify(drawModeChangeListeners, this.drawMode, newDrawMode);
            this.drawMode = newDrawMode;
        }
    }

    public void setCursorPosition(int x, int y) {
        CursorPosition newCursorPosition = new CursorPosition(x, y);
        if (!newCursorPosition.equals(this.cursorPosition)) {
            notify(cursorPositionListeners, this.cursorPosition, newCursorPosition);
            this.cursorPosition = newCursorPosition;
        }
    }

    public void onApplicationStateChange(SimpleChangeListener<ApplicationState> listener) {
        appStateListeners.add(listener);
    }

    public void listenToDrawMode(SimpleChangeListener<CellState> listener) {
        drawModeChangeListeners.add(listener);
    }

    public void listenToCursorPosition(SimpleChangeListener<CursorPosition> listener) {
        cursorPositionListeners.add(listener);
    }

    private <T> void notify(Collection<SimpleChangeListener<T>> listeners, T oldVal, T newVal) {
        for (SimpleChangeListener<T> listener : listeners) {
            listener.valueChanged(oldVal, newVal);
        }
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public CellState getDrawMode() {
        return drawMode;
    }

    public CursorPosition getCursorPosition() {
        return cursorPosition;
    }
}

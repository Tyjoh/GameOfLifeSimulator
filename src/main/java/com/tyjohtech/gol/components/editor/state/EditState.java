package com.tyjohtech.gol.components.editor.state;

import com.tyjohtech.app.property.AbstractObservable;

public class EditState extends AbstractObservable<EditState> {

    private Edit edit;
    private boolean inProgress = false;

    public Edit getEdit() {
        return edit;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void addChange(Change change) {
        this.edit.add(change);
        this.notifyListeners(this);
    }

    public void beginEdit() {
        if (inProgress)
            throw new IllegalStateException("Edit already in progress, call endEdit() before starting a new one");

        this.inProgress = true;
        this.edit = new Edit();
        this.notifyListeners(this);
    }

    public void endEdit() {
        if (!inProgress) throw new IllegalStateException("beginEdit() must be called first");

        this.inProgress = false;
        this.edit = null;
        this.notifyListeners(this);
    }
}
